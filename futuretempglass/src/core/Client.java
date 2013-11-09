package core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import storage.client.ClientItemLibrary;
import storage.client.ClientProductionStepsLibrary;
import ui.views.EditOrderWindow;
import ui.views.ServerIpWindow;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class Client{

	public static String serverIp = null;

	public static int TIMEOUT = 20000;

	public static int PORT = 1110;

	public static void sendString(String string, InetAddress address, int port)
	{
		byte[] bytes = string.getBytes();
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(PORT);
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

	public static Object receiveMessage()
	{
		DatagramSocket ds = null;
		ByteInputStream bis = null;
		ObjectInputStream ois = null;
		try
		{
			ds = new DatagramSocket(PORT);
			ds.setSoTimeout(TIMEOUT);
			byte[] buffer = new byte[32768];
			DatagramPacket p = new DatagramPacket(buffer, buffer.length);
			ds.receive(p);

			bis = new ByteInputStream(p.getData(), p.getLength());
			ois = new ObjectInputStream(bis);

			return ois.readObject();
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
			if(ois != null)
			{
				try
				{
					ois.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			if(bis != null)
			{
				try
				{
					bis.close();
				}
				catch(IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static Object sendMessageToServer(String string)
	{
		try
		{
			sendString(string, InetAddress.getByName(serverIp), PORT);
			return receiveMessage();
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Application.itemLibrary = new ClientItemLibrary();
		Application.productionStepsLibrary = new ClientProductionStepsLibrary();

		new ServerIpWindow();
	}
}