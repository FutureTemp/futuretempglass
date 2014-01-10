package storage;

import java.util.List;

import server.objects.Account;

public abstract class AccountLibrary{
	
	/**
	 * Adds another account into storage, returns false if
	 * adding the account fails. Reasons can include I/O problems
	 * or if the account already exists.
	 * @param account
	 * @return true if account was added correctly, false otherwise
	 */
	public abstract boolean addAcount(Account account);
	
	/**
	 * Returns the Account object associated with the given username.
	 * @param username
	 * @return the Account, null if there is no account with the given username
	 */
	public abstract Account getAccount(String username);
	
	/**
	 * Removes the account associated with the given username.
	 * Returns true if it was successful and false otherwise. 
	 * Reasons may include an I/O problem or if an account with
	 * the specified username does not exist.
	 * @param username
	 * @return true if successful, false otherwise
	 */
	public abstract boolean deleteAccount(String username);
	
	/**
	 * Get the list of all the Accounts. Returns null
	 * if it was unsuccessful.
	 * @return all Accounts
	 */
	public abstract List<Account> getAccounts();
}
