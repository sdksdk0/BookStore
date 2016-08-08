package cn.tf.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tf.domain.Function;
import cn.tf.domain.Role;
import cn.tf.domain.User;
import cn.tf.service.PrivilegeService;
import cn.tf.service.impl.PrivilegeServiceImpl;
import cn.tf.utils.Constant;


@WebFilter(filterName="PrivilegeFilter" ,urlPatterns="/manage/*")
public class PrivilegeFilter implements Filter {
	private PrivilegeService ps = new PrivilegeServiceImpl();
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request;
		HttpServletResponse response;
		try {
			request = (HttpServletRequest) req;
			response = (HttpServletResponse) res;
		} catch (ClassCastException e) {
			throw new ServletException(e);
		}
		
		User user = (User) request.getSession().getAttribute(Constant.USER_LOGIN_FLAG);
		//没有登录，则重定向到登录页面
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/passport/login.jsp");
			return;
		}
		//登录了
		Set<Function> functions = new HashSet<Function>();
			
		List<Role> roles = ps.findUserRoles(user);
			
		for(Role role:roles){
			List<Function> funs = ps.findRoleFunctions(role);
			functions.addAll(funs);
		}
		
		//获取用户访问的uri
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		String fullUri = uri+(queryString==null?"":("?"+queryString));					
			
		//查看uri在不在访问的范围内
		boolean hasPermission = false;
		for(Function fun:functions){
			if(fullUri.startsWith(fun.getUri())){
				hasPermission = true;
				break;
			}
		}
			
		if(!hasPermission){
			response.getWriter().write("权限拒绝");
			return;
		}
		//放行	
		chain.doFilter(request, response);
	}

	public void destroy() {

	}

}
