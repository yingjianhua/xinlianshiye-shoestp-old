package irille.Filter.svr;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ItpSessionmsg extends AbstractInterceptor {

	private static final long serialVersionUID = -7822445355454027571L;

	private static ThreadLocal<SessionMsg> sessionmsg = new ThreadLocal<>();

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		try {
			Map<String, Object> session = actionInvocation.getInvocationContext().getSession();

			SessionMsg sessionmsg = (SessionMsg)session.get(SessionMsg.session_key);
			if(sessionmsg == null) {
				sessionmsg = SessionMsg.build();
				session.put(SessionMsg.session_key, sessionmsg);
			}
			SessionMsg.update(sessionmsg, actionInvocation);
			setSessionmsg(sessionmsg);
			return actionInvocation.invoke();
		} catch (Exception e) {
			throw e;
		} finally {
			removeSessionmsg();
		}
	}

	public static SessionMsg getSessionmsg() {
		return sessionmsg.get();
	}

	public static void setSessionmsg(SessionMsg sessionmsg) {
		ItpSessionmsg.sessionmsg.set(sessionmsg);
	}

	public static void removeSessionmsg() {
		sessionmsg.remove();
	}
}
