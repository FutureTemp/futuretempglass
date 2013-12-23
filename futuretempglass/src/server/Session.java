package server;

import com.sun.net.httpserver.HttpExchange;

public class Session{

	private String ip;
	
	public Session(HttpExchange ex)
	{
		setIp(ex.getRemoteAddress().getAddress().getHostAddress());
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

}
