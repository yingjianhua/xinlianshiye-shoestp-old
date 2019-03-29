package com.xinlianshiye.shoestp.listener;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.google.inject.Inject;
import com.xinlianshiye.shoestp.common.service.UsrMainService;
import com.xinlianshiye.shoestp.common.service.impl.UsrMainServiceImpl;

import irille.Filter.svr.SessionMsg;
import irille.pub.svr.DbPool;
import irille.shop.usr.UsrMain;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionListener implements HttpSessionListener {

  {
    usrMainService = new UsrMainServiceImpl();
  }

  @Inject UsrMainService usrMainService;

  @Override
  public void sessionCreated(HttpSessionEvent se) {}

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    HttpSession session = se.getSession();
    SessionMsg sessionMsg = (SessionMsg) session.getAttribute(SessionMsg.session_key);
    if (sessionMsg != null && sessionMsg.haveUser()) {
      UsrMain usrMain = sessionMsg.getUsrMain();
      log.info("用户{}登出或登录失效", usrMain.getPkey());
      usrMainService.signOut(usrMain);
      try {
        DbPool.getInstance().getConn().commit();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
