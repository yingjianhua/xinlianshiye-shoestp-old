package irille.Filter.svr;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import irille.action.ActionBase;
import irille.shop.usr.Usr.OStatus;
import irille.shop.usr.UsrSupplier;
import org.apache.struts2.ServletActionContext;

public class ItpCheckSupplierLogin extends AbstractInterceptor {

  private static final long serialVersionUID = -4537735137491364389L;

  private static final String[] ignore_list = {
    "usr_UsrSupplier_login", "usr_UsrSupplier_logon", "sys_SysAccessory_uploadImage"
  };

  public String intercept(ActionInvocation actionInvocation) throws Exception {

    final String actionName = actionInvocation.getProxy().getActionName();

    UsrSupplier supplier = ItpSessionmsg.getSessionmsg().getSupplier();

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
}
