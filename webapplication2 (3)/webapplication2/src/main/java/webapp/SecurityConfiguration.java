package webapp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class SecurityConfiguration implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest serverReq, ServletResponse serverRes, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) serverReq;
        forbidGoingBack((HttpServletResponse) serverRes);
        Student student = (Student) request.getSession().getAttribute("student");

        if (student == null){
            request.getRequestDispatcher("/login").forward(serverReq,serverRes);
        }
        else {
            filterChain.doFilter(serverReq, serverRes);
        }
    }

    private void forbidGoingBack(HttpServletResponse serverRes) {
        HttpServletResponse response = serverRes;
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
    }

    @Override
    public void destroy() {
        this.filterConfig=null;
    }
}
