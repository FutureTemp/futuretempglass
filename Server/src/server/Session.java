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

	/**
	 * Sets the account and ip associated with this Session
	 * @param account
	 * @param ex
	 */
	public void init(Account account, HttpExchange ex)
	{
		setIp(ex.getRemoteAddress().getAddress().getHostAddress());
		setAccount(account);
	}
	
	/**
	 * Checks if the address in the HttpExchange matches the
	 * IP in the Session
	 * @param ex
	 * @return
	 */
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

	/**
	 * Returns whether an IP address is the localhost address
	 * (for both IPv4 and IPv6)
	 * @param address
	 * @return
	 */
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
