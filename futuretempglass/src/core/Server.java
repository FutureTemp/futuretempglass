package core;

import items.Item;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import storage.server.ServerItemLibrary;
import ui.views.Window;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

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

	public void execute(DatagramPacket packet)
	{
		String command = new String(packet.getData(), 0, packet.getLength());

		textArea.insert(command + "\n", 0);

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
}