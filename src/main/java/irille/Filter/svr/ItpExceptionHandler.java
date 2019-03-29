package irille.Filter.svr;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import irille.action.BaseAction;
import irille.homeAction.HomeAction;
import irille.pub.Exp;
import irille.pub.exception.WebException;
import irille.pub.exception.WebMessageException;
import irille.pub.util.AppConfig;
import irille.sellerAction.SellerAction;
import irille.shop.lg.LgAccessDAO;

public class ItpExceptionHandler extends AbstractInterceptor {
  private static final Logger log = LoggerFactory.getLogger(ItpExceptionHandler.class);

  private static final long serialVersionUID = 1L;

  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    Long date1 = System.currentTimeMillis();
    boolean isSuccess = true;
    Exception e2 = null;
    try {
      return invocation.invoke();
    } catch (Exception e) {
      isSuccess = false;
      e2 = e;
      String msg;
      if (e instanceof Exp) {
        // 如果是Exp类型的异常,都是通过LOG.err方式抛出来的,异常已经打印过了,不需要重复打印
        msg = ((Exp) e).getLastMessage();
      } else if (e instanceof WebMessageException) {
        msg = e.getMessage();
        //日志等级设置未warn, 方便用户投诉时 能快速定位异常消息
        log.warn(msg);
      } else {
        // 未处理的异常
        msg = "service error";
        log.error("未处理异常", e);
      }
      ActionProxy proxy = invocation.getProxy();
      Method method = proxy.getAction().getClass().getMethod(proxy.getMethod());
      if (invocation.getAction() instanceof HomeAction) {
        HomeAction<?> action = (HomeAction<?>) invocation.getAction();
        if (method.getReturnType().equals(String.class)) {
          HttpSession session = ServletActionContext.getRequest().getSession();
          irille.Filter.svr.SessionMsg sessionmsg =
              (irille.Filter.svr.SessionMsg)
                  session.getAttribute(irille.Filter.svr.SessionMsg.session_key);
          if (sessionmsg.getIsMobile()) action.setResult("/mobile/404.jsp", false);
          else action.setResult("/home/404.jsp", false);
          return HomeAction.TRENDS;
        } else if (method.getReturnType().equals(void.class)) {
          if (e instanceof WebMessageException) {
            WebException we = (WebException) e;
            BaseAction.writeErr(we.getCode().getCode(), msg);
          } else {
            BaseAction.writeErr(msg);
          }
        }
      } else if (invocation.getAction() instanceof SellerAction) {
        if (method.getReturnType().equals(void.class)) {
          BaseAction.writeErr(msg);
        }
      } else {
        if (method.getReturnType().equals(void.class)) {
          BaseAction.writeErr(msg);
        }
      }
    } finally {
      Long date2 = System.currentTimeMillis();
      log.info(
          "后台处理【"
              + getJumpUrl(ServletActionContext.getRequest())
              + "】共消耗【"
              + (date2 - date1)
              + "】毫秒");
      //
      // System.err.println("后台处理【"+getJumpUrl(ServletActionContext.getRequest())+"】共消耗【"+(date2-date1)+"】毫秒");
      try {
        HttpSession session = ServletActionContext.getRequest().getSession();
        irille.Filter.svr.SessionMsg sessionmsg =
            (irille.Filter.svr.SessionMsg)
                session.getAttribute(irille.Filter.svr.SessionMsg.session_key);
        if (sessionmsg != null)
          LgAccessDAO.add(
              sessionmsg.getUser(),
              ServletActionContext.getRequest(),
              date2 - date1,
              isSuccess,
              e2);
        AppConfig.db_connection_commit();
      } catch (Exception e) {
        AppConfig.db_connection_rollback();
        e.printStackTrace();
      } finally {
        AppConfig.db_connection_close();
      }
    }
    return null;
  }

  private String getJumpUrl(HttpServletRequest request) {
    String params = getParameters(request);
    if ("".equals(params)) {
      return getServletUrl(request);
    } else {
      return getServletUrl(request) + "?" + params;
    }
  }

  private String getParameters(HttpServletRequest request) {
    String params = "";
    Enumeration<?> names = request.getParameterNames();
    int i = 0;
    while (names.hasMoreElements()) {
      Object element = names.nextElement();
      if (i != 0) params += "&";
      params += (element + "=" + request.getParameter(element.toString()));
      i++;
    }
    return params;
  }

  private String getServletUrl(HttpServletRequest request) {
    return request.getRequestURI();
  }
}
