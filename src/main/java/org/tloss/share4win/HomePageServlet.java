package org.tloss.share4win;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomePageServlet
 */
public class HomePageServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1831480983674572730L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			new DealService().getActiveDeal();
		} catch (Exception e) {
			throw new ServletException(e);
		}
		request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
	}

}
