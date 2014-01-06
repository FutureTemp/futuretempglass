package utils;

import java.util.HashMap;

import server.objects.Account;
import storage.AccountLibrary;
import storage.json.JSONAccountLibrary;
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

}