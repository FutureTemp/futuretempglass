package server.handlers;

import java.io.IOException;
import java.security.MessageDigest;

import server.Server;
import server.Session;
import utils.AccountUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class LoginHandler extends ServerHandler{

	private static String context = "/login";

	public static String getContext()
	{
		return context;
	}

	@Override
	public void handle(HttpExchange ex) throws IOException
	{
		ex.sendResponseHeaders(200, 0);
		if(authenticate(ex))
		{
			Session session = new Session(ex);
			Server.getActiveSessions().add(session);
			ex.getResponseBody().write("Login Successful".getBytes());
		}
		else
		{
			ex.getResponseBody().write("Login Failed".getBytes());
		}
		finish(ex);
	}

	protected boolean authenticate(HttpExchange ex)
	{
		try
		{
			String hash = ex.getRequestHeaders().get("Authentication").get(0);
			if(AccountUtils.authenticate(hash))
			{
				return true;
			}
		}
		catch(Exception e)
		{

		}
		return false;
	}

	public static void main(String[] args) throws Exception
	{
		MessageDigest m = MessageDigest.getInstance("SHA-256");
		m.update("Hello World".getBytes());
		byte[] bytes = m.digest();
		String hex = HexBin.encode(bytes);
		System.out.println(hex);
	}
}
