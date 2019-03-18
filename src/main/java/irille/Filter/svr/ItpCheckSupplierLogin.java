package irille.Filter.svr;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import irille.action.ActionBase;
import irille.shop.usr.Usr.OStatus;
import irille.shop.usr.UsrSupplier;
import irille.shop.usr.UsrSupplierRoleDAO;
import irille.shop.usr.UsrUserDAO;
import irille.view.usr.UserView;

public class ItpCheckSupplierLogin extends AbstractInterceptor {

  private static final long serialVersionUID = -4537735137491364389L;

  private String autoLogin = "";

  private static final String[] ignore_list = {
    "usr_UsrSupplier_login", "usr_UsrSupplier_logon", "sys_SysAccessory_uploadImage"
  };

  public String intercept(ActionInvocation actionInvocation) throws Exception {

    final String actionName = actionInvocation.getProxy().getActionName();

    UsrSupplier supplier = ItpSessionmsg.getSessionmsg().getSupplier();

    if (supplier == null && !"".equals(autoLogin)) {
      UserView user = UsrUserDAO.findByLoginName(autoLogin);
      ItpSessionmsg.getSessionmsg().setUser(user);
      supplier = user.getSupplier();
    }

    if (ServletActionContext.getRequest().getMethod().equals("GET") && actionName.equals("")) {
      if (supplier != null && supplier.gtStatus() == OStatus.APPR) {
        return "index";
      } else {
        return "login";
      }
    }

    if (ignoreAction(actionName)) {
      return actionInvocation.invoke();
    }

    if (supplier == null || supplier.gtStatus() == OStatus.INIT) {
      ServletActionContext.getResponse().setHeader("sessionstatus", "timeout");
      return ActionBase.LOGIN;
    }

    //		if(actionName.equals("pageIndex")) {
    //			System.out.println("redirect:index");
    //			return "index";
    //		}

    if (!UsrSupplierRoleDAO.ALL_ACT.contains(actionName)) {
      // 非法请求
      HttpServletResponse response = ServletActionContext.getResponse();
      JSONObject json = new JSONObject().put("success", false).put("msg", "非法请求");
      response.getWriter().print(json.toString());
      return null;
    }
    if (!UsrSupplierRoleDAO.getRoleAllAct().get(supplier.getRole()).contains(actionName)) {
      // 没有权限
      HttpServletResponse response = ServletActionContext.getResponse();
      JSONObject json = new JSONObject().put("success", false).put("msg", "没有权限");
      response.getWriter().print(json.toString());
      return null;
    }
    return actionInvocation.invoke();
  }

  private boolean ignoreAction(String actionname) {
    for (String ignore : ignore_list) {
      if (ignore.equals(actionname)) {
        return true;
      }
    }
    return false;
  }

  public String getAutoLogin() {
    return autoLogin;
  }

  public void setAutoLogin(String autoLogin) {
    this.autoLogin = autoLogin;
  }
}
