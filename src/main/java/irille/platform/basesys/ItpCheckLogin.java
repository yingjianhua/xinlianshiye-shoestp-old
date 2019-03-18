package irille.platform.basesys;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;

import irille.action.ActionBase;
import irille.action.sys.SysUserAction;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;
import org.apache.struts2.ServletActionContext;

public class ItpCheckLogin extends irille.pub.svr.ItpCheckLogin {
  public ItpCheckLogin() {
    super();
  }

  @Override
  public boolean ignoreAction(String actionname) {
    return super.ignoreAction(actionname);
  }

  public String intercept(ActionInvocation actionInvocation) throws Exception {
    // 确认Session中是否存在LOGIN
    Map session = actionInvocation.getInvocationContext().getSession();
    LoginUserMsg umsg = (LoginUserMsg) session.get(ActionBase.LOGIN);
    if (umsg != null) {
      // 这里缓存的线程级对象会在请求结束后在DBITP中释放
      Env.INST.initTran(umsg, actionInvocation.getProxy().getActionName());
      // 存在的情况下进行后续操作。
      return actionInvocation.invoke();
    } else {
      // 对LoginAction不做该项拦截
      Object action = actionInvocation.getAction();
      if (action instanceof SysUserAction)
        if (actionInvocation.getProxy().getActionName().equals("basesys_basesysUser_baselogin")
            || actionInvocation.getProxy().getActionName().equals("sys_SysUser_loginTest"))
          return actionInvocation.invoke();
      if (ignoreAction(actionInvocation.getProxy().getActionName()))
        return actionInvocation.invoke();
      // 否则终止后续操作
      ServletActionContext.getResponse().setHeader("sessionstatus", "timeout");
      return ActionBase.LOGIN;
    }
  }
}
