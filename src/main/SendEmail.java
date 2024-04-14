package main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail
{
	private static String to = "kylerzinbox@gmail.com", from = "no-reply@kanye.wiki";
	private static String username = "no-reply@kanye.wiki", password = "n543p_z$W-#x", host = "smtp.kanye.wiki";

	public static void sendEmail(String url, String name) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); 
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "26");

		// Get the Session object
		Session session = Session.getInstance(props,
		new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try 
		{
			// Create a default MimeMessage object
			Message message = new MimeMessage(session);
		 
			message.setFrom(new InternetAddress(from));
		 
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("BNguyen2004@hotmail.com"));
		 
			// Set Subject
			message.setSubject(name + " (IN STOCK)");
		 
			// Put the content of your message
			message.setText(url);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} 
		catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}
	}
}
