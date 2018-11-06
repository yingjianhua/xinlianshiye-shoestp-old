package irille.pub.svr;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ItpDbTransaction extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		
		String rtn = null;
		String path = actionInvocation.getProxy().getActionName();
		
		try {
			Env.INST.initTran(null, null);
			rtn = actionInvocation.invoke();
			String[] ps = path.split("\\_");
			if (ps[ps.length - 1].equals("list") == false) // 查询不处理事务提交
				DbPool.getInstance().getConn().commit();
		} catch (Exception e) {
			DbPool.getInstance().getConn().rollback();
			throw e;
		} finally {
			Env.INST.removeTran();
			DbPool.getInstance().getConn().commit(); //提交对日志的更新
			DbPool.getInstance().removeConn();
			
		}
		return rtn;
	}
}
