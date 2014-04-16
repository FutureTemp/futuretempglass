package server;

import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.handlers.AccountHandler;
import server.handlers.FileHandler;
import server.handlers.InventoryHandler;
import server.handlers.ItemsHandler;
import server.handlers.LoginHandler;
import server.handlers.LogoutHandler;
import server.handlers.OrderHandler;
import server.handlers.PageHandler;
import server.handlers.TaskHandler;
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

	private static int PORT = 80;

	private static boolean stop = false;

	private JButton stopServerButton;

	private JTextArea textArea;

	private HttpServer server;

	private static List<Session> activeSessions = new ArrayList<Session>();

	/**
	 * Adds all the Handler objects for the server
	 */
	private void addHandlers()
	{
		server.createContext(OrderHandler.getContext(), new OrderHandler(this));
		server.createContext(TokenHandler.getContext(), new TokenHandler(this));
		server.createContext(LoginHandler.getContext(), new LoginHandler(this));
		server.createContext(ItemsHandler.getContext(), new ItemsHandler(this));
		server.createContext(LogoutHandler.getContext(),
				new LogoutHandler(this));
		server.createContext(PageHandler.getContext(), new PageHandler(this));
		server.createContext(AccountHandler.getContext(), new AccountHandler(
				this));
		server.createContext(InventoryHandler.getContext(),
				new InventoryHandler(this));
		server.createContext(TaskHandler.getContext(), new TaskHandler(this));
		server.createContext(FileHandler.getContext(), new FileHandler(this));
	}

	/**
	 * Sets the libraries in Application
	 */
	private void addLibraries()
	{
		Application.setJSONLibraries();
	}

	/**
	 * Finds the local IP
	 * @return local ip
	 */
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
		try
		{
			setTitle(getLocalIp());
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(
					new FormLayout(new ColumnSpec[] {
							FormFactory.RELATED_GAP_COLSPEC,
							ColumnSpec.decode("default:grow"), },
							new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
									RowSpec.decode("default:grow"),
									FormFactory.RELATED_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC, }));

			textArea = new JTextArea();
			JScrollPane scrollTextPane = new JScrollPane(textArea);
			mainPanel.add(scrollTextPane, "2, 2, fill, fill");

			stopServerButton = new JButton("Stop Server");
			stopServerButton.addMouseListener(this);
			mainPanel.add(stopServerButton, "2, 4");
			getContentPane().add(mainPanel);
			getContentPane().setPreferredSize(new Dimension(300, 300));
			pack();
			setVisible(true);

			addLibraries();
			server = HttpServer.create(new InetSocketAddress(PORT), 0);
			addHandlers();
			server.start();
			while (!stop)
			{
				Thread.sleep(1000);
			}
		}
		catch(Exception e)
		{
			if(server != null)
			{
				server.stop(0);
			}
			dispose();
			throw e;
		}
		finally
		{
			if(server != null)
			{
				server.stop(0);
			}
			dispose();
		}
	}

	/**
	 * Prints text to the server log
	 * 
	 * @param string
	 */
	public void print(String string)
	{
		this.textArea.append(string);
		pack();
	}

	/**
	 * Prints text to the server log and goes to the next line after
	 * 
	 * @param string
	 */
	public void println(String string)
	{
		this.print(string + "\n");
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
	 * Application.itemLibrary = new ServerItemLibrary();
	 * Application.productionStepsLibrary = new ServerProductionStepsLibrary();
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
	 * @param activeSessions
	 *            the activeSessions to set
	 */
	public static void setActiveSessions(List<Session> activeSessions)
	{
		Server.activeSessions = activeSessions;
	}
}