package irille.pub.svr;


import irille.shop.usr.Usr.OStatus;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/15
 * Time: 15:56
 */
public class PermissionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        SessionMsg sessionmsg = (SessionMsg) session.getAttribute(SessionMsg.session_key);
        if (request.getServletPath().substring(0, 19).equalsIgnoreCase("/seller/admin/index")) {
        	if(!request.getServletPath().equalsIgnoreCase("/seller/admin/index/login.html") && (sessionmsg == null || !sessionmsg.isSupplier()||sessionmsg.getSupplier().gtStatus()==OStatus.INIT)) {
	            //request.getRequestDispatcher("/seller").forward(servletRequest, servletResponse);
	            response.sendRedirect("/seller/admin/index/login.html");
	            return;
        	}
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
