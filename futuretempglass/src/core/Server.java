package core;

import items.Item;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import storage.server.ServerItemLibrary;


class Server{

	private static boolean stop = false;
	
	public static void sendString(String string, InetAddress address, int port)
	{
		byte[] bytes = string.getBytes();
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(1111);
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

	public Server()
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
		while (!stop)
		{
			try
			{
				ds = new DatagramSocket(1110);
				byte[] buffer = new byte[1024];
				DatagramPacket p = new DatagramPacket(buffer, buffer.length);
				ds.receive(p);
				Server.execute(p);
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

	public static void execute(DatagramPacket packet)
	{
		String command = new String(packet.getData(), 0, packet.getLength());

		System.out.println(command);
		
		if("get item names".equalsIgnoreCase(command))
		{
			StringBuilder builder = new StringBuilder();
			List<Item> items = Application.getItemLibrary().getItems();
			for(Item item: items)
			{
				builder.append(item.getItemName());
				builder.append(",");
			}
			builder.deleteCharAt(builder.length() - 1);
			sendString(builder.toString(), packet.getAddress(),
					packet.getPort());
		}
	}

	public static void main(String[] args)
	{
		Application.itemLibrary = new ServerItemLibrary();
		
		new Server();
	}
}