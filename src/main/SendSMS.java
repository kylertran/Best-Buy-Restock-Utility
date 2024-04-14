package main;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSMS
{
	public static final String ACCOUNT_SID = "AC2bb4d0ee8895ba24654b4d808dab23cb";
	public static final String AUTH_TOKEN = "9cd18e8308b1db2623106682198b3a74";
	
	public static void Main(String[] args)
	
	{
		sendSMS("", "Test");
	}
	
	public static void sendSMS(String url, String name)
	{
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		Message message = Message.creator(
				new PhoneNumber("+12063029270"),
				new PhoneNumber("+14048464759"),
				name + " (IN STOCK) " + url
		).create();
		
		/*Message message2 = Message.creator(
				new PhoneNumber("+12063904801"),
				new PhoneNumber("+14048464759"),
				name + " (IN STOCK) " + url
		).create();*/
		
		System.out.println(message.getSid());
	}
}
