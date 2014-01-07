package server.handlers;

import java.io.IOException;
import java.util.List;

import server.objects.Account;
import utils.AccountUtils;
import utils.StringUtils;

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
		if(!getSession(ex).getAccount().isAdmin())
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
	
	private void oneUser(HttpExchange ex) throws IOException
	{
		String username = getParameters(ex).get("username");
		Account account = AccountUtils.getAccount(username);
		sendHeader(ex);
		sendResponse(account, ex);
	}
	
	private void allUsers(HttpExchange ex) throws IOException
	{
		List<Account> accounts = AccountUtils.getAccounts();
		sendHeader(ex);
		sendResponse(accounts, ex);
	}
}
