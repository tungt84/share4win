package org.tloss.share4win.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tloss.share4win.UserService;
import org.tloss.share4win.Utils;

/**
 * Servlet implementation class ActivePhoneServlet
 */
public class ActivePhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(ActivePhoneServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivePhoneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void SMS_VNHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String serviceNumber = request.getParameter("serviceNumber");
		String subCode = request.getParameter("subCode");
		String code = request.getParameter("code");
		String mobile = request.getParameter("mobile");
		String info = request.getParameter("info");
		String info2 = info.replaceAll("\\s+", " ").trim();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if ("8085".equals(serviceNumber) && "S4W".equalsIgnoreCase(subCode) && "DV".equalsIgnoreCase(code)
				&& info2.length() > 7) {
			UserService service = new UserService();
			String active;
			try {
				active = service.genPhoneActiveCode(info2.substring(7));
				if (active != null) {
					out.print("0 | Ma kich hoat: " + active);
				} else {
					out.print("0 | User " + info2.substring(7) + " khong ton tai");
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}

		} else {
			// sai cu phap
			out.print("0 | Sai cu phap.Cu phat dung: DV S4W [username]");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		Utils.setPageTitle(request, "Kích hoạt số điện thoại");
		if ("/service/active/phone".equals(request.getServletPath())) {
			SMS_VNHandle(request, response);
		} else {
			String mail = request.getParameter("email");
			request.setAttribute("email", mail);
			request.getRequestDispatcher("/WEB-INF/active-phone.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletR equest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utils.setPageTitle(request, "Kích hoạt số điện thoại");
		if ("/service/active/phone".equals(request.getServletPath())) {
			SMS_VNHandle(request, response);
		} else {
			// call by user
			String mail = request.getParameter("email");
			String code = request.getParameter("code");
			UserService service = new UserService();
			try {
				service.activePhone(mail, code);
				request.getRequestDispatcher("/WEB-INF/active-phone.jsp").forward(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}

}
