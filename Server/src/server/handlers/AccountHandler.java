package server.handlers;

import java.io.IOException;
import java.util.List;

import server.Server;
import server.objects.Account;
import utils.AccountUtils;
import utils.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

public class AccountHandler extends ServerHandler{

	public AccountHandler(Server server)
	{
		super(server);
	}

	private static String context = "/users";

	public static String getContext()
	{
		return context;
	}

	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		if("true".equalsIgnoreCase(getParameters(ex).get("current")))
		{
			currentUser(ex);
			return;
		}
		if(!StringUtils.isEmpty(getParameters(ex).get("username")))
		{

			String username = getParameters(ex).get("username");
			if(getSession(ex).getAccount().getUsername().equals(username)
					|| isAdmin(ex))
			{
				oneUser(username, ex);
			}
			return;
		}
		if(!isAdmin(ex))
		{
			ex.sendResponseHeaders(403, 0);
			return;
		}
		allUsers(ex);
		return;
	}

	@Override
	protected void onPost(HttpExchange ex) throws Exception
	{
		super.onPost(ex);
		ObjectMapper mapper = new ObjectMapper();
		String requestData = getRequestData(ex);
		Account account = mapper.readValue(requestData, Account.class);
		boolean isAdmin = isAdmin(ex);
		if(!isAdmin
				&& !getSession(ex).getAccount().getUsername()
						.equals(account.getUsername()))
		{
			ex.sendResponseHeaders(403, 0);
			return;
		}
		if(!isAdmin)
		{
			account.setAdmin(false);
		}
		try
		{
			if(isAdmin)
			{
				AccountUtils.addAccount(account);
			}
			else
			{
				throw new Exception("Non-admins cannot add accounts");
			}
		}
		catch(Exception e)
		{
			AccountUtils.updateAccount(account);
		}
		sendHeader(ex);
		sendResponse(account, ex);
	}

	/**
	 * Sends a response containing only one user specified by the username
	 * string given
	 * 
	 * @param username
	 * @param ex
	 * @throws IOException
	 */
	private void oneUser(String username, HttpExchange ex) throws IOException
	{
		Account account = AccountUtils.getAccount(username);
		sendHeader(ex);
		sendResponse(account, ex);
	}

	/**
	 * Uses the HttpExchange object to send a response containing all the users
	 * 
	 * @param ex
	 * @throws IOException
	 */
	private void allUsers(HttpExchange ex) throws IOException
	{
		List<Account> accounts = AccountUtils.getAccounts();
		sendHeader(ex);
		sendResponse(accounts, ex);
	}

	/**
	 * Sends a response containing the current user in the corresponding Session
	 * 
	 * @param ex
	 * @throws IOException
	 */
	private void currentUser(HttpExchange ex) throws IOException
	{
		Account account = getSession(ex).getAccount();
		sendHeader(ex);
		sendResponse(account, ex);
		finish(ex);
	}
}
