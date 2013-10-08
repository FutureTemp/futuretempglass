package core;

import items.Item;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Client{

	public static String serverIp = "192.168.1.14";

	public static void sendString(String string, InetAddress address, int port)
	{
		byte[] bytes = string.getBytes();
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(1110);
			ds.send(new DatagramPacket(bytes, bytes.length, address, port));
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
	}

	public Client()
	{
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(1110);
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
		while (true)
		{
			try
			{
				byte[] buffer = new byte[1024];
				DatagramPacket p = new DatagramPacket(buffer, buffer.length);
				ds.receive(p);
				Client.execute(p);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
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
			ds = new DatagramSocket(1110);
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

	public static void execute(DatagramPacket packet)
	{
		String command = new String(packet.getData(), 0, packet.getLength());

		if("get items".equalsIgnoreCase(command))
		{
			StringBuilder builder = new StringBuilder();
			List<Item> items = Application.getItemLibrary().getItems();
			for(Item item: items)
			{
				builder.append(item.getItemName());
				builder.append('|');
			}
			sendString(builder.toString(), packet.getAddress(),
					packet.getPort());
		}
	}

	public static void main(String[] args)
	{
		new Client();
	}

	public static String sendMessageToServer(String string)
	{
		try
		{
			sendString(string, InetAddress.getByName(serverIp), 1110);
		}
		catch(UnknownHostException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}