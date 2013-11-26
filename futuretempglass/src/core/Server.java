package core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import ui.views.Window;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

class Server extends Window implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int OUT_PORT = 1111;

	private static int IN_PORT = 1110;

	private static boolean stop = false;

	private static final int TIMEOUT = 10;

	private JButton stopServerButton;

	private JTextArea textArea;

	public static String getLocalIp()
	{
		String ip = null;
		try
		{
			Enumeration<NetworkInterface> interfaces = NetworkInterface
					.getNetworkInterfaces();
			while (interfaces.hasMoreElements())
			{
				NetworkInterface iface = interfaces.nextElement();
				// filters out 127.0.0.1 and inactive interfaces
				if(iface.isLoopback() || !iface.isUp())
					continue;

				Enumeration<InetAddress> addresses = iface.getInetAddresses();
				while (addresses.hasMoreElements())
				{
					InetAddress addr = addresses.nextElement();
					ip = addr.getHostAddress();
					System.out.println(iface.getDisplayName() + " " + ip);
				}
			}
		}
		catch(SocketException e)
		{
			throw new RuntimeException(e);
		}
		return ip;
	}

	public static void sendString(String string, InetAddress address, int port)
	{
		byte[] bytes = string.getBytes();
		sendBytes(bytes, address, port);
	}

	public static void sendBytes(byte[] bytes, InetAddress address, int port)
	{
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(OUT_PORT);
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
		super(null);
		setTitle(getLocalIp());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		textArea = new JTextArea();
		getContentPane().add(textArea, "2, 2, fill, fill");

		stopServerButton = new JButton("Stop Server");
		stopServerButton.addMouseListener(this);
		getContentPane().add(stopServerButton, "2, 4");
		pack();
		setVisible(true);

		DatagramSocket ds = null;
		while (!stop)
		{
			try
			{
				ds = new DatagramSocket(IN_PORT);
				ds.setSoTimeout(TIMEOUT);
				byte[] buffer = new byte[1024];
				DatagramPacket p = new DatagramPacket(buffer, buffer.length);
				ds.receive(p);
				execute(p);
			}
			catch(SocketTimeoutException e)
			{
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
		dispose();
	}

	public void execute(DatagramPacket packet) throws ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException
	{
		String[] command = new String(packet.getData(), 0, packet.getLength())
				.split(",");

		textArea.insert(command + "\n", 0);

		Object objectToSend = null;

		Class<?> clazz = Class.forName(command[0]);

		Method[] methods = clazz.getMethods();

		for(Method method: methods)
		{
			if(method.getName().equals(command[1]))
			{
				switch (method.getParameterTypes().length)
				{
				case 5:
					objectToSend = method.invoke(command[2], command[3],
							command[4], command[5], command[6]);
					break;
				case 4:
					objectToSend = method.invoke(command[2], command[3],
							command[4], command[5]);
					break;
				case 3:
					objectToSend = method.invoke(command[2], command[3],
							command[4]);
					break;
				case 2:
					objectToSend = method.invoke(command[2], command[3]);
					break;
				case 1:
					objectToSend = method.invoke(command[2]);
					break;
				case 0:
					objectToSend = method.invoke(null);
					break;
				}
			}
		}

		if(objectToSend == null)
		{
			objectToSend = "";
		}

		ByteOutputStream bos = null;
		ObjectOutputStream oos = null;
		try
		{
			bos = new ByteOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(objectToSend);
			byte[] bytesToSend = bos.getBytes();
			sendBytes(bytesToSend, packet.getAddress(), packet.getPort());
		}
		catch(Exception e)
		{
			if(oos != null)
			{
				try
				{
					oos.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
			if(bos != null)
			{
				bos.close();
			}
			e.printStackTrace();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(stopServerButton))
		{
			stop = true;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{/*
		Application.itemLibrary = new ServerItemLibrary();
		Application.productionStepsLibrary = new ServerProductionStepsLibrary();
*/
		new Server();

		return;
	}
}