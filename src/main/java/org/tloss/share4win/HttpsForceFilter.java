package org.tloss.share4win;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpsForceFilter implements Filter{
	private String server;
	private String port;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest =(HttpServletRequest)request;
		String schema;
		schema =  servletRequest.getHeader("x-forwarded-proto");
		if("http".equalsIgnoreCase(schema)){
			HttpServletResponse servletResponse = (HttpServletResponse) response;
			servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			servletResponse.setHeader("Location", "https://"+server+("443".equals(port)?"":(":"+port)));
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		server =  config.getInitParameter("https-server");
		port =  config.getInitParameter("https-port");
	}
	
}
