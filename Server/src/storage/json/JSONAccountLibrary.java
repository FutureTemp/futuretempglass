package storage.json;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import server.objects.Account;
import storage.AccountLibrary;
import utils.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONAccountLibrary extends AccountLibrary{

	private static final String ACCOUNTS_PATH = "accounts/accounts.txt";

	private List<Account> accounts = new ArrayList<Account>();

	private HashMap<String, Account> accountsMap = new HashMap<String, Account>();

	public JSONAccountLibrary()
	{
		init();
	}

	private void init()
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			accounts = mapper.readValue(new File(ACCOUNTS_PATH),
					new TypeReference<List<Account>>(){});
		}
		catch(Exception e)
		{
		}
		for(Account account: accounts)
		{
			accountsMap.put(account.getUsername(), account);
		}
	}

	private void save()
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try
		{
			mapper.writeValue(new File(ACCOUNTS_PATH), accounts);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean addAcount(Account account)
	{
		if(accountsMap.get(account.getUsername()) != null)
		{
			return false;
		}
		accountsMap.put(account.getUsername(), account);
		accounts.add(account);
		save();
		return true;
	}

	@Override
	public boolean updateAccount(Account account)
	{
		Account old = accountsMap.get(account.getUsername());
		if(old == null)
		{
			return false;
		}
		accountsMap.put(account.getUsername(), account);
		accounts.remove(old);
		accounts.add(account);
		save();
		return true;
	}

	@Override
	public boolean deleteAccount(String username)
	{
		Account account = accountsMap.remove(username);
		if(account == null)
		{
			return false;
		}
		accounts.remove(account);
		save();
		return true;
	}

	@Override
	public Account getAccount(String username)
	{
		if(StringUtils.isEmpty(username))
		{
			return null;
		}
		return accountsMap.get(username);
	}

	@Override
	public List<Account> getAccounts()
	{
		return accounts;
	}
}
