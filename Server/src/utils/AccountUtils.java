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

	/**
	 * Gets the account associated with the specified token and checks
	 * to see if the password is correct for that account. If the password
	 * is correct the Account is returned, null is returned if authentication fails
	 * @param token
	 * @param password
	 * @return Account or null if failed authentication
	 */
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

	/**
	 * Gets a token used for login authentication, and stores the username
	 * that is associated with the token being returned
	 * @param username
	 * @return String token, null if username is invalid
	 */
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

	/**
	 * Gets the account with the username provided
	 * @param username
	 * @return Account, null if username does not match any accounts
	 */
	public static Account getAccount(String username)
	{
		return Application.getAccountLibrary().getAccount(username);
	}

	/**
	 * Gets the list of all the accounts in storage
	 * @return Lis of Accounts
	 */
	public static List<Account> getAccounts()
	{
		return Application.getAccountLibrary().getAccounts();
	}

	/**
	 * Adds the account provided into storage
	 * @param account
	 * @throws Exception
	 */
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