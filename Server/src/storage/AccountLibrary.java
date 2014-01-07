package storage;

import java.util.List;

import server.objects.Account;

public abstract class AccountLibrary{
	
	public abstract boolean addAcount(Account account);
	
	public abstract Account getAccount(String username);
	
	public abstract boolean deleteAccount(String username);
	
	public abstract List<Account> getAccounts();
}
