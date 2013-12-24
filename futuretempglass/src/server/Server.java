package server;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import server.handlers.ItemsHandler;
import server.handlers.LoginHandler;
import server.handlers.LogoutHandler;
import server.handlers.OrderHandler;
import server.handlers.TokenHandler;
import ui.views.Window;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.sun.net.httpserver.HttpServer;

import core.Application;

public class Server extends Window implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int PORT = 8080;

	private static boolean stop = false;

	private JButton stopServerButton;

	private JTextArea textArea;
	
	private HttpServer server;
	
	private static List<Session> activeSessions = new ArrayList<Session>();

	private void addHandlers()
	{
		server.createContext(OrderHandler.getContext(), new OrderHandler());
		server.createContext(TokenHandler.getContext(), new TokenHandler());
		server.createContext(LoginHandler.getContext(), new LoginHandler());
		server.createContext(ItemsHandler.getContext(), new ItemsHandler());
		server.createContext(LogoutHandler.getContext(), new LogoutHandler());
	}
	
	private void addLibraries()
	{
		Application.setDBLibraries();
	}
	
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

	public Server() throws Exception
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

		addLibraries();
		server = HttpServer.create(new InetSocketAddress("192.168.1.14",PORT), 0);
		addHandlers();
		server.start();
		while (!stop)
		{
			Thread.sleep(1000);
		}
		server.stop(0);
		dispose();
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

	public static void main(String[] args) throws Exception
	{/*
		Application.itemLibrary = new ServerItemLibrary();
		Application.productionStepsLibrary = new ServerProductionStepsLibrary();
*/
		new Server();

		return;
	}

	/**
	 * @return the activeSessions
	 */
	public static List<Session> getActiveSessions()
	{
		return activeSessions;
	}

	/**
	 * @param activeSessions the activeSessions to set
	 */
	public static void setActiveSessions(List<Session> activeSessions)
	{
		Server.activeSessions = activeSessions;
	}
}