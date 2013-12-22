package server;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;

public class Session{

	private InetSocketAddress address;
	
	public Session(HttpExchange ex)
	{
		setAddress(ex.getRemoteAddress());
	}

	/**
	 * @return the address
	 */
	public InetSocketAddress getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(InetSocketAddress address)
	{
		this.address = address;
	}
}
