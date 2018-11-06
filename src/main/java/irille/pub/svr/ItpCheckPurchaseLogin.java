package irille.pub.svr;

import irille.homeAction.HomeAction;
import irille.shop.usr.UsrPurchase;
import irille.shop.usr.UsrUserDAO;
import irille.view.usr.UserView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ItpCheckPurchaseLogin extends AbstractInterceptor {

	private static final long serialVersionUID = -5308775862619271060L;

	private String autoLogin = "";
	
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		UsrPurchase purchase = ItpSessionmsg.getSessionmsg().getPurchase();
		if(purchase == null && !"".equals(autoLogin)) {
			UserView user = UsrUserDAO.findByLoginName(autoLogin);
			ItpSessionmsg.getSessionmsg().setUser(user);
			purchase = user.getPurchase();
		}
		//System.out.println(getJumpUrl(ServletActionContext.getRequest()));
		HttpServletResponse response = ServletActionContext.getResponse();
		if(actionInvocation.getAction() instanceof HomeAction) {
			HomeAction<?> action = (HomeAction<?>)actionInvocation.getAction();
			action.initEnv();
		}
		
		ActionProxy proxy = actionInvocation.getProxy();
		Method method = proxy.getAction().getClass().getMethod(proxy.getMethod());
		if(method.getReturnType().equals(String.class)) {
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-store, no-cache, must-reval…te, post-check=0, pre-check=0");
		}
		if(method.getAnnotation(NeedLogin.class)!=null) {
			Class<?> returnType = method.getReturnType();
			if(returnType.equals(void.class) && HomeAction.getPurchase()==null) {
				HomeAction.writeTimeout();
				return null;
			} else if(returnType.equals(String.class) && HomeAction.getPurchase()==null){
				HomeAction<?> action = (HomeAction<?>)actionInvocation.getAction();
				action.setJumpUrl(getJumpUrl(ServletActionContext.getRequest()));
				return HomeAction.LOGIN;
			}
		}
		
		return actionInvocation.invoke();
	}
	
	/**
	 * 在需要登录后才能使用的方法上加上该注释</p>
	 * 在调用方法前会进行校验</p>
	 * 若该方法返回类型为String,即页面转发的action,会自动跳转到登录页面,登录完成后返回该页面</p>
	 * 若该方法的返回类型为void,即数据请求的action,会自动条用writeTimeout方法,返回{ret:-1}</p>
	 * 
	 * @author yingjianhua
	 *
	 */
	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface NeedLogin {

	}
	
	public static void s(Object s) {
		System.out.println(s);
	}
	
	public String getAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}
	public String getJumpUrl(HttpServletRequest request) {
		String params = getParameters(request);
		if("".equals(params)) {
			return getServletUrl(request);
		} else {
			return getServletUrl(request)+"?"+params;
		}
	}
	public String getParameters(HttpServletRequest request) {
		String params = "";
		Enumeration<?> names = request.getParameterNames();
		int i=0;
		while(names.hasMoreElements()) {
			Object element  = names.nextElement();
			if(i!=0)
				params += "&";
			params += (element+"="+request.getParameter(element.toString()));
			i++;
		}
		return params;
	}
	public String getServletUrl(HttpServletRequest request) {
		return request.getRequestURI();
	}
}
