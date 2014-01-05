package server.handlers;

import java.io.IOException;
import java.util.List;

import server.Server;
import server.Session;
import server.objects.Account;
import utils.AccountUtils;

import com.sun.net.httpserver.HttpExchange;

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
			sendResponse("Must Logout First", ex);
			finish(ex);
			return;
		}
		Account account = authenticateLoginAndgetAccount(ex);

		if(account != null)
		{
			Session session = getActiveSession(account.getUsername());
			if(session == null)
			{
				session = new Session();
			}
			session.init(account, ex);
			Server.getActiveSessions().add(session);
			ex.getResponseBody().write("Login Successful".getBytes());
		}
		else
		{
			ex.getResponseBody().write("Login Failed".getBytes());
		}
		finish(ex);
	}

	protected Account authenticateLoginAndgetAccount(HttpExchange ex)
	{
		try
		{
			List<String> list = ex.getRequestHeaders().get("Authentication");
			String hash = "";
			if(list != null)
			{
				hash = list.get(0);
			}
			else
			{
				hash = getParameters(ex).get("Authentication");
			}
			Account account = AccountUtils.authenticate(hash);
			return account;
		}
		catch(Exception e)
		{

		}
		return null;
	}

}
