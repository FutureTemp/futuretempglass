package core;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client{

	public static String serverIp = "192.168.1.14";

	public static int TIMEOUT = 20000;

	public static int OUT_PORT = 1110;

	public static int IN_PORT = 1111;

	public static void sendString(String string, InetAddress address, int port)
	{
		byte[] bytes = string.getBytes();
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(OUT_PORT);
			ds.send(new DatagramPacket(bytes, bytes.length, address, port));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ds != null)
			{
				ds.close();
			}
		}
	}

	public static String receiveMessage()
	{
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(IN_PORT);
			ds.setSoTimeout(TIMEOUT);
			byte[] buffer = new byte[32768];
			DatagramPacket p = new DatagramPacket(buffer, buffer.length);
			ds.receive(p);
			return new String(buffer, 0, buffer.length);
		}
		catch(Exception e)
		{

		}
		finally
		{
			if(ds != null)
			{
				ds.close();
			}
		}
		return null;
	}

	public static String sendMessageToServer(String string)
	{
		try
		{
			sendString(string, InetAddress.getByName(serverIp), OUT_PORT);
			return receiveMessage();
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}