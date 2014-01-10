package server.handlers;

import java.io.IOException;
import java.util.HashMap;
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

	/**
	 * Gets the token that was given from the HttpExchange object,
	 * finds the account associated with it, and checks to make sure the
	 * password matches. If it does it returns the Account object of that 
	 * account, otherwise it returns null.
	 * @param ex
	 * @return account, or null if fails authentication
	 */
	protected Account authenticateLoginAndgetAccount(HttpExchange ex)
	{
		try
		{
			List<String> list = ex.getRequestHeaders().get("token");
			String token = "";
			String password = "";
			if(list != null)
			{
				token = list.get(0);
				password = ex.getRequestHeaders().getFirst("password");
			}
			else
			{
				HashMap<String, String> parameters = getParameters(ex);
				token = parameters.get("token");
				password = parameters.get("password");
			}
			Account account = AccountUtils.authenticate(token, password);
			return account;
		}
		catch(Exception e)
		{

		}
		return null;
	}
	
}
