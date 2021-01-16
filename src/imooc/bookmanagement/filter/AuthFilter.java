package imooc.bookmanagement.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("*")
public class AuthFilter implements Filter {
    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		boolean passFlag = false;
		String uri = httpRequest.getRequestURI();
		if(uri.indexOf("login.css") != -1 ) passFlag = true; // 不能把css也过滤了，不然登录界面没有css
		if(uri.indexOf("login.do") != -1) passFlag = true; // 登录控制器的入口不能过滤了不然就卡住了
		if(httpRequest.getSession().getAttribute("username") != null) {
			chain.doFilter(request, response);
		} else if(passFlag) {
			chain.doFilter(request, response);
		} else {
			httpRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
