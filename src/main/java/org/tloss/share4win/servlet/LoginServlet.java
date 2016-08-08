package org.tloss.share4win.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tloss.share4win.UserService;
import org.tloss.share4win.Utils;
import org.tloss.share4win.model.Users;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utils.setPageTitle(request, "Đăng nhập");
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("email");
		String password = request.getParameter("password");
		Utils.setPageTitle(request, "Đăng nhập");
		try {
			Users users = new UserService().login(mail, password);
			if (users != null) {
				HttpSession session = request.getSession(true);
				if (session != null)
					session.setAttribute("USER_LOGIN", users);
				if ("/service/user/login".equals(request.getServletPath())) {
					request.getRequestDispatcher("/WEB-INF/service/login.jsp").forward(request, response);
				} else {
					response.sendRedirect(response.encodeRedirectURL("/"));
				}
			} else {
				if ("/service/user/login".equals(request.getServletPath())) {
					request.getRequestDispatcher("/WEB-INF/service/login.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			new ServletException(e);
		}

	}

}
