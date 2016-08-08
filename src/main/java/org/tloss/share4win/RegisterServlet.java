package org.tloss.share4win;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utils.setPageTitle(request, "Đăng ký");
		request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail  = request.getParameter("mail");
		String phone = request.getParameter("phone");
		UserService service =  new UserService();
		Utils.setPageTitle(request, "Đăng ký");
		try {
			String hash = service.registerUser(mail, phone);
			Message message =MailMessageFactory.getMessage();
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail));
			message.setSubject("Verify account");
			message.setText("Link to active :" + hash);
			Transport.send(message);
			request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e);
		}	
		
	}

}
