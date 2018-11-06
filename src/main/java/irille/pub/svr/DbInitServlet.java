package irille.pub.svr;

import irille.shop.lg.LgAccess;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DbInitServlet extends HttpServlet {
	
	public void init() throws ServletException {
		System.out.println(new Date().toLocaleString() + " : 日志数据库初始化");
		try {
			initLgAccess();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initLgAccess() throws Exception {
		Env.INST.getDB().db(LgAccess.class);
	}
}
