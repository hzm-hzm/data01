package xin.hlao.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//管理员页面拦截器
public class AdminLoginFilter implements Filter {

    public AdminLoginFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain chain) 
					throws IOException, ServletException {
		
		servletrequest.setCharacterEncoding("utf-8");
		servletresponse.setContentType("text/html;charset=utf-8");
		
		HttpServletRequest request=(HttpServletRequest) servletrequest;
		HttpServletResponse response=(HttpServletResponse) servletresponse;
		
		HttpSession session=request.getSession();

		if(session.getAttribute("admin")==null) {
			request.setAttribute("d", "你还无登陆！");
			request.getRequestDispatcher("/adminjsps/login.jsp").forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
		

		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
