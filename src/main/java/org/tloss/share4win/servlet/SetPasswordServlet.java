package org.tloss.share4win.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tloss.share4win.UserService;
import org.tloss.share4win.Utils;

/**
 * Servlet implementation class SetPasswordServlet
 */
public class SetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("email");
		String hash = request.getParameter("hash");
		UserService service = new UserService();
		Utils.setPageTitle(request, "Thiết lập mật khẩu");
		try {
			if (service.checkHash(mail, hash)) {
				request.setAttribute("email", mail);
				request.setAttribute("hash", hash);
			} else {
				// TODO thong bao hash va email ko hon le
			}
			request.getRequestDispatcher("/WEB-INF/setpassword.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail = request.getParameter("email");
		String hash = request.getParameter("hash");
		String password =  request.getParameter("password");
		//TODO check valid input
		UserService service =  new UserService();
		try {
			service.setUserPassword(mail, hash, password, true);
			//TODO thong bao khi thanh cong
			request.getRequestDispatcher("/WEB-INF/setpassword.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}

}
