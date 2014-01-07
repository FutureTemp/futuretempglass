package server;

import server.objects.Account;

import com.sun.net.httpserver.HttpExchange;

public class Session{

	private static final String LOCALHOST_IPV4 = "127.0.0.1";
	
	private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

	private String ip;
	
	private Account account;

	public Session()
	{
		
	}
	
	public Session(Account account, HttpExchange ex)
	{
		init(account, ex);
	}

	public void init(Account account, HttpExchange ex)
	{
		setIp(ex.getRemoteAddress().getAddress().getHostAddress());
		setAccount(account);
	}
	
	public boolean isMatchingAddress(HttpExchange ex)
	{
		if(isLocal(ex.getRemoteAddress().getAddress().getHostAddress())
				&& isLocal(getIp()))
		{
			return true;
		}
		if(!getIp().equals(ex.getRemoteAddress().getAddress().getHostAddress()))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the ip
	 */
	public String getIp()
	{
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	private static boolean isLocal(String address)
	{
		return LOCALHOST_IPV4.equals(address) || LOCALHOST_IPV6.equals(address);
	}

	/**
	 * @return the account
	 */
	public Account getAccount()
	{
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account)
	{
		this.account = account;
	}

}
