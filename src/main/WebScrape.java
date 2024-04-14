package main;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrape
{
	protected static boolean outOfStock = true;
	protected static ArrayList<String> urls = new ArrayList<String>();
	protected static ArrayList<String> names = new ArrayList<String>();
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static void main(String[] args)
	{
		urls.add("https://www.bestbuy.com/site/nvidia-geforce-rtx-3070-8gb-gddr6-pci-express-4-0-graphics-card-dark-platinum-and-black/6429442.p?skuId=6429442");
		urls.add("https://www.bestbuy.com/site/nvidia-geforce-rtx-3060-ti-8gb-gddr6-pci-express-4-0-graphics-card-steel-and-black/6439402.p?skuId=6439402");
		names.add("NVIDIA RTX 3070 FE");
		names.add("NVIDIA RTX 3060 Ti FE");
		
		while(outOfStock)
		{
			System.out.println(ANSI_YELLOW + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa").format(Calendar.getInstance().getTime()) + ANSI_RESET);
			System.out.println("--------------------------------");
			for(int u = 0; u < urls.size(); u++)
			{
				try
				{
					String url = urls.get(u);
					String name = names.get(u);
					
					Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
					Elements temp = doc.select("div.fulfillment-add-to-cart-button");
					
					int i = 0;
					for(Element element : temp)
					{
						i++;
						if(element.getElementsByTag("button").first().text().equals("Sold Out"))
						{
							System.out.println(ANSI_RED + name + " (" + element.getElementsByTag("button").first().text() + ")" + ANSI_RESET);
							
						}
						else
						{
							System.out.println(ANSI_GREEN + name + " (" + element.getElementsByTag("button").first().text() + ")" + ANSI_RESET);;
							Toolkit.getDefaultToolkit().beep();
							if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			        		    try
								{
									Desktop.getDesktop().browse(new URI(url));
								} catch (IOException | URISyntaxException e1)
								{
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
			        		}
							SendSMS.sendSMS(url, name);
							//SendEmail.sendEmail(url, name);
							outOfStock = false;
						}
					}
				}
				catch (IOException e)
				{e.printStackTrace();}
			}
			try
			{	
				System.out.println("--------------------------------");
				System.out.println();
				Thread.sleep(150000);	
			} catch (InterruptedException e)
			{e.printStackTrace();}
		}	
	}
}
