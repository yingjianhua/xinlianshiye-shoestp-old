package irille.Filter.svr;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xinlianshiye.shoestp.common.errcode.MessageBuild;

import irille.Filter.svr.ItpCheckPurchaseLogin.NeedLogin.UserType;
import irille.homeAction.HomeAction;
import irille.pub.exception.ReturnCode;
import irille.pub.util.AppConfig;
import irille.view.usr.UserView;

import org.apache.struts2.ServletActionContext;

public class ItpCheckPurchaseLogin extends AbstractInterceptor {

  private static final long serialVersionUID = -5308775862619271060L;

  public String intercept(ActionInvocation actionInvocation) throws Exception {
    HttpServletResponse response = ServletActionContext.getResponse();
    if (actionInvocation.getAction() instanceof HomeAction) {
      HomeAction<?> action = (HomeAction<?>) actionInvocation.getAction();
      action.initEnv();
    }
    ActionProxy proxy = actionInvocation.getProxy();
    Method method = proxy.getAction().getClass().getMethod(proxy.getMethod());
    if (method.getReturnType().equals(String.class)) {
      response.setHeader("pragma", "no-cache");
      response.setHeader(
          "cache-control", "no-store, no-cache, must-reval…te, post-check=0, pre-check=0");
    }
    NeedLogin annotation = method.getAnnotation(NeedLogin.class);
    //annotation 不为空需要进行登录和角色判断
    if (annotation != null) {
      boolean entryable = false;
      UserView user = HomeAction.getUser();
      if (user != null) {
        if (annotation.userType() == UserType.ALL) {
          entryable = true;
        } else if (annotation.userType() == UserType.SUPPLIER && user.isSupplier()) {
          entryable = true;
        } else if (annotation.userType() == UserType.PURCHASE && user.isPurchase()) {
          entryable = true;
        }
      }

      Class<?> returnType = method.getReturnType();
      //返回值类型是String 是页面请求, 当没有登录或没有权限时 重定向到登录页面
      if (returnType.equals(String.class) && !entryable) {
        HomeAction<?> action = (HomeAction<?>) actionInvocation.getAction();
        StringJoiner stringJoiner = new StringJoiner("&");
        getParams(ServletActionContext.getRequest())
            .forEach(
                (s, strings) -> {
                  if (s.equalsIgnoreCase("jumpUrl")) return;
                  for (String string : strings) {
                    String t = s + "=" + string;
                    stringJoiner.add(t);
                  }
                });
        String host = ServletActionContext.getRequest().getHeader("Referer");
        if (host == null || host.length() < 1) {
          host = AppConfig.domain;
        }
        if (stringJoiner.length() > 0) {
          action.setJumpUrl(
              ServletActionContext.getRequest().getServletPath() + "?" + stringJoiner.toString());
        }
        return HomeAction.LOGIN;
      }

      //返回值类型是void, 是数据请求, 当没有登录或没有权限时 返回错误数据
      if (returnType.equals(void.class) && !entryable) {
        if (user == null) {
          HomeAction.writeTimeout();
        } else {
          HomeAction.write(
              MessageBuild.buildMessage(ReturnCode.service_unauthorized, HomeAction.curLanguage()));
        }
        return null;
      }
    }

    return actionInvocation.invoke();
  }

  /**
   * 在需要登录后才能使用的方法上加上该注释 在调用方法前会进行校验 若该方法返回类型为String,即页面转发的action,会自动跳转到登录页面,登录完成后返回该页面
   * 若该方法的返回类型为void,即数据请求的action,会自动条用writeTimeout方法,返回{ret:-1}
   *
   * @author yingjianhua
   */
  @Target({ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface NeedLogin {
//    // TODO 这里改用用户组会更好用
//    boolean supplier() default false;

    /** 对用户类型进行校验 */
    UserType userType() default UserType.ALL;

    public enum UserType {
      SUPPLIER, // 必须为供应商类型用户
      PURCHASE, // 必须为采购商类型用户
      ALL // 不进行用户类型判断
    }
  }

  public static void s(Object s) {
    System.out.println(s);
  }

  public String getJumpUrl(HttpServletRequest request) {
    String params = getParameters(request);
    if ("".equals(params)) {
      return getServletUrl(request);
    } else {
      return getServletUrl(request) + "?" + params;
    }
  }

  public Map<String, String[]> getParams(HttpServletRequest request) {
    return request.getParameterMap();
  }

  public String getParameters(HttpServletRequest request) {
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

  public String getServletUrl(HttpServletRequest request) {
    return request.getRequestURI();
  }
}
