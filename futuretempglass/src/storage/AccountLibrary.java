package storage;

import server.objects.Account;

public abstract class AccountLibrary{
	
	public abstract boolean addAcount(Account account);
	
	public abstract Account getAccount(String username);
	
	public abstract boolean deleteAccount(String username);
	
}
