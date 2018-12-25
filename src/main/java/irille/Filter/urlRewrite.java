package irille.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA. User: lijie@shoestp.cn Date: 2018/10/7 Time: 21:56
 */
public class urlRewrite implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getServletPath().startsWith("/country/") && isMobile(request)) {
            request.getRequestDispatcher(request.getServletPath().replaceFirst("/country/", "/m/country/")).forward(request, response);
            return;
        }
        if (request.getServletPath().startsWith("/activity/html/romania/") && isMobile(request) && request.getServletPath().indexOf("mc") == -1) {
            String path = request.getServletPath().substring("/activity/html/romania/".length(), request.getServletPath().indexOf("/", "/activity/html/romania/".length()));
            System.out.println(request.getServletPath().replaceFirst(path, path + "/mc"));
            response.sendRedirect(request.getServletPath().replaceFirst(path, path + "/mc"));
//            request.getRequestDispatcher().forward(request, response);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public static boolean isMobile(HttpServletRequest request) {
        String[] mobile_device_array = new String[]{"android", "windows phone", "mobile", "iphone"};
        String agent = request.getHeader("User-Agent").toLowerCase();
        for (String s : mobile_device_array) {
            if (agent.indexOf(s) != -1) {
                return true;
            }
        }
        return false;
    }

}
