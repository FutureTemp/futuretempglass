package server.handlers;

import java.io.IOException;
import java.util.List;

import server.objects.Account;
import utils.AccountUtils;
import utils.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

public class AccountHandler extends ServerHandler{

	private static String context = "/users";
	
	public static String getContext()
	{
		return context;
	}
	
	@Override
	protected void onGet(HttpExchange ex) throws Exception
	{
		super.onGet(ex);
		if(!isAdmin(ex))
		{
			ex.sendResponseHeaders(403, 0);
			return;
		}
		if(!StringUtils.isEmpty(getParameters(ex).get("username")))
		{
			oneUser(ex);
			return;
		}
		else
		{
			allUsers(ex);
			return;
		}
	}
	
	@Override
	protected void onPost(HttpExchange ex) throws Exception
	{
		super.onPost(ex);
		if(!isAdmin(ex))
		{
			ex.sendResponseHeaders(403, 0);
			return;
		}
		ObjectMapper mapper = new ObjectMapper();
		Account account = mapper.readValue(getRequestData(ex), Account.class);
		AccountUtils.addAccount(account);
		sendHeader(ex);
		sendResponse("Added", ex);
	}
	
	/**
	 * Sends a response containing only one user using
	 * the HttpExchange object to get the username of that user
	 * @param ex
	 * @throws IOException
	 */
	private void oneUser(HttpExchange ex) throws IOException
	{
		String username = getParameters(ex).get("username");
		Account account = AccountUtils.getAccount(username);
		sendHeader(ex);
		sendResponse(account, ex);
	}
	
	/**
	 * Uses the HttpExchange object to send a response containing
	 * all the users
	 * @param ex
	 * @throws IOException
	 */
	private void allUsers(HttpExchange ex) throws IOException
	{
		List<Account> accounts = AccountUtils.getAccounts();
		sendHeader(ex);
		sendResponse(accounts, ex);
	}
}
