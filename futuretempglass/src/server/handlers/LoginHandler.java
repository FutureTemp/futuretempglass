package server.handlers;

import java.io.IOException;

import server.Server;
import server.Session;
import utils.AccountUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginHandler implements HttpHandler{

	private static String context = "/login";
	
	public static String getContext()
	{
		return context;
	}

	@Override
	public void handle(HttpExchange ex) throws IOException
	{
		if(authenticate(ex))
		{
			Session session = new Session(ex);
			Server.getActiveSessions().add(session);
		}
	}
	
	protected boolean authenticate(HttpExchange ex)
	{
		String hash = ex.getRequestHeaders().get("Authentication").get(0);
		if(AccountUtils.authenticate(hash))
		{
			return true;
		}
		ex.close();
		return false;
	}
}
