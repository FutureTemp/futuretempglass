package server;

import com.sun.net.httpserver.HttpExchange;

public class Session{

	private String ip;
	private String hostName;
	
	public Session(HttpExchange ex)
	{
		setIp(ex.getRemoteAddress().getAddress().getHostAddress());
	}

	public boolean isMatchingSession(HttpExchange ex)
	{
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
	 * @param ip the ip to set
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName()
	{
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}
}
