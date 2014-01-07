package utils;

import java.util.HashMap;
import java.util.List;

import server.objects.Account;
import core.Application;

public class AccountUtils{

	/**
	 * Maps expected authentication tokens to the username associated to it
	 */
	private static HashMap<String, String> usernames = new HashMap<String, String>();

	public static Account authenticate(String token, String password)
	{
		String username = usernames.get(token);
		if(username == null)
		{
			return null;
		}
		Account account = Application.getAccountLibrary().getAccount(username);
		if(account == null)
		{
			return null;
		}
		if(account.authenticate(password))
		{
			usernames.remove(token);
			return account;
		}
		return null;
	}

	public static String getToken(String username)
	{
		if(Application.getAccountLibrary().getAccount(username) == null)
		{
			return null;
		}
		String token = StringUtils.getRandomStringOfLettersAndNumbers(10)
				.toUpperCase();
		usernames.put(token, username);
		return token;
	}

	public static Account getAccount(String username)
	{
		return Application.getAccountLibrary().getAccount(username);
	}

	public static List<Account> getAccounts()
	{
		return Application.getAccountLibrary().getAccounts();
	}

	public static void addAccount(Account account) throws Exception
	{
		if(Application.getAccountLibrary().getAccount(account.getUsername()) == null)
		{
			if(!Application.getAccountLibrary().addAcount(account))
			{
				throw new Exception("Failed to add account");
			}
		}
		else
		{
			throw new Exception("Username already taken");
		}
	}

}