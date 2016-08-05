package cn.tf.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="CharseEncodingFilter" ,urlPatterns="/*")
public class CharseEncodingFilter implements Filter {

	
	
	private FilterConfig filterConfig;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  request;
		HttpServletResponse response;
		try {
			request=(HttpServletRequest) req;
			response=(HttpServletResponse) resp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String encoding=filterConfig.getInitParameter("encoding");
		if(encoding==null)
			encoding="UTF-8";
		
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset="+encoding);
		request.setCharacterEncoding(encoding);
		
		GetHttpServletRequest getrequest = new GetHttpServletRequest(request);
		chain.doFilter(getrequest, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
	}
}

class GetHttpServletRequest extends HttpServletRequestWrapper{
	public GetHttpServletRequest(HttpServletRequest request){
		super(request);
	}

	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value==null)
			return value;
		//获取请求方式
		String method = super.getMethod();
		if("get".equalsIgnoreCase(method)){
			try {
				value = new String(value.getBytes("ISO-8859-1"),super.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
}









