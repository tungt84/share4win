package org.tloss.share4win;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailMessageFactory {
	private static Session session;
	private static String username;

	public static void init(Properties props, final String username, final String password) throws Exception {
		if (session == null) {
			MailMessageFactory.username = username;
			session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		}
	}

	public static Message getMessage() throws AddressException, MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		return message;
	}
}
