package org.tloss.share4win;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tloss.share4win.model.PageInfo;
import org.tloss.share4win.model.Users;

public class HttpsForceFilter implements Filter {
	private String server;
	private String port;
	private String[] excludeUrl;
	private String[] protectedUrl;
	private String adminUser;

	@Override
	public void destroy() {

	}

	protected boolean checkPermission(HttpServletRequest request, Users user) {
		if (user != null && user.getEmail().equalsIgnoreCase(adminUser)) {
			return true;
		} else {
			if (!request.getServletPath().startsWith("/admin/")) {
				if (user != null) {
					return true;
				}
				if (protectedUrl != null && protectedUrl.length > 0) {
					for (int i = 0; i < protectedUrl.length; i++) {
						if (request.getServletPath().matches(protectedUrl[i])) {
							return false;
						}
					}
					return true;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession(false);
		if (session != null && session.getAttribute("USER_LOGIN") != null) {
			servletRequest.setAttribute("user_logined", true);
		} else {
			servletRequest.setAttribute("user_logined", false);
		}
		Users user  = null;
		if(session!=null){
			user = (Users) session.getAttribute("USER_LOGIN");
		}
		if(!checkPermission(servletRequest, user)){
			servletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
			return ;
		}
		PageInfo info = new PageInfo();
		info.setTitle("Share4Win");
		servletRequest.setAttribute("PAGEINFO", info);

		String schema;
		schema = servletRequest.getHeader("x-forwarded-proto");
		String path = "" + (servletRequest.getServletPath() != null ? servletRequest.getServletPath() : "")
				+ (servletRequest.getPathInfo() != null ? servletRequest.getPathInfo() : "");
		if ("http".equalsIgnoreCase(schema) && !isExclude(path)) {
			
			servletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			servletResponse.setHeader("Location", "https://" + server + ("443".equals(port) ? "" : (":" + port)));
		} else {
			chain.doFilter(request, response);
		}
	}

	protected boolean isExclude(String path) {
		if (excludeUrl != null) {
			for (int i = 0; i < excludeUrl.length; i++) {
				if (path.startsWith(excludeUrl[i]))
					return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		server = config.getInitParameter("https-server");
		port = config.getInitParameter("https-port");
		String tmp = config.getInitParameter("exclude-url");
		if (tmp != null) {
			excludeUrl = tmp.split(",");
		}
		tmp = config.getInitParameter("authen-protected-url");
		if (tmp != null) {
			protectedUrl = tmp.split(",");
		}
		adminUser = config.getInitParameter("authen-admin");
		String driver = config.getInitParameter("jdbc-driver");
		String url = config.getInitParameter("jdbc-url");
		String user = config.getInitParameter("jdbc-user");
		String password = config.getInitParameter("jdbc-password");
		try {
			DatasourceFactory.init(driver, url, user, password);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		user = config.getInitParameter("mail-user");
		password = config.getInitParameter("mail-password");
		Properties properties = new Properties();
		try {
			properties.load(config.getServletContext().getResourceAsStream("/WEB-INF/mail.properties"));
			MailMessageFactory.init(properties, user, password);
		} catch (IOException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
