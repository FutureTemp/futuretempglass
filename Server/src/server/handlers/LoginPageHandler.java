package server.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

public class LoginPageHandler extends PageHandler{

	private static final String context = "/loginPage";
	
	public static String getContext()
	{
		return context;
	}
	
	@Override
	public void handle(HttpExchange ex) throws IOException
	{
		if("GET".equals(ex.getRequestMethod()))
		{
			try
			{
				sendHeader(ex);
				onGet(ex);
			}
			catch(Exception e)
			{
				sendResponse(e.getMessage(), ex);
			}
			finally
			{
				finish(ex);
			}
		}
	}
	
	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		sendPage("pages/login.html", ex);
	}
	
}
