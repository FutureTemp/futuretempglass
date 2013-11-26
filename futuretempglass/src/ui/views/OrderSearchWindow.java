package ui.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import orders.Order;
import ui.Mode;
import core.Application;

public class OrderSearchWindow extends Window{

	private JPanel contentPane;

	private JButton newOrderButton;

	private JButton editOrderButton;

	private JButton viewOrderButton;

	private JButton deleteOrderButton;

	private JList<Object> orderList;

	public OrderSearchWindow()
	{
		this(null);
	}

	/**
	 * Create the frame.
	 */
	public OrderSearchWindow(Window parentWindow)
	{
		super(parentWindow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		refresh();
	}

	@Override
	public void refresh()
	{
		setBounds(100, 100, 203, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.2, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel mainPane = new JPanel();
		GridBagConstraints gbc_mainPane = new GridBagConstraints();
		gbc_mainPane.insets = new Insets(0, 0, 5, 0);
		gbc_mainPane.fill = GridBagConstraints.BOTH;
		gbc_mainPane.gridx = 0;
		gbc_mainPane.gridy = 0;
		contentPane.add(mainPane, gbc_mainPane);
		GridBagLayout gbl_mainPane = new GridBagLayout();
		gbl_mainPane.columnWidths = new int[] { 0, 0 };
		gbl_mainPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_mainPane.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_mainPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		mainPane.setLayout(gbl_mainPane);

		newOrderButton = new JButton("New Order");
		newOrderButton.addMouseListener(this);
		GridBagConstraints gbc_newOrderButton = new GridBagConstraints();
		gbc_newOrderButton.insets = new Insets(0, 0, 5, 0);
		gbc_newOrderButton.gridx = 0;
		gbc_newOrderButton.gridy = 0;
		mainPane.add(newOrderButton, gbc_newOrderButton);

		editOrderButton = new JButton("Edit Order");
		editOrderButton.addMouseListener(this);
		GridBagConstraints gbc_editOrderButton = new GridBagConstraints();
		gbc_editOrderButton.insets = new Insets(0, 0, 5, 0);
		gbc_editOrderButton.gridx = 0;
		gbc_editOrderButton.gridy = 1;
		mainPane.add(editOrderButton, gbc_editOrderButton);

		viewOrderButton = new JButton("View Order");
		viewOrderButton.addMouseListener(this);
		GridBagConstraints gbc_viewOrderButton = new GridBagConstraints();
		gbc_viewOrderButton.gridx = 0;
		gbc_viewOrderButton.gridy = 2;
		mainPane.add(viewOrderButton, gbc_viewOrderButton);

		deleteOrderButton = new JButton("Delete Order");
		deleteOrderButton.addMouseListener(this);
		GridBagConstraints gbc_deleteOrderButton = new GridBagConstraints();
		gbc_deleteOrderButton.gridx = 0;
		gbc_deleteOrderButton.gridy = 3;
		mainPane.add(deleteOrderButton, gbc_deleteOrderButton);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);

		List<String> orderNumbers = Application.getOrderLibrary()
				.getOrderNumbers();
		if(orderNumbers == null)
		{
			orderNumbers = new ArrayList<String>();
		}
		orderList = new JList<Object>(orderNumbers.toArray());
		orderList.addMouseListener(this);
		orderList.addKeyListener(this);
		scrollPane.setViewportView(orderList);
		setVisible(true);
		repaint();
	}

	private void editSelectedOrders()
	{
		for(Object orderNumber: orderList.getSelectedValuesList())
		{
			Order order = Application.getOrderLibrary().getOrder(
					(String)orderNumber);
			new EditOrderWindow(order, Mode.EDIT, this);
		}
	}

	private void deleteSelectedOrders()
	{
		for(Object orderNumber: orderList.getSelectedValuesList())
		{
			Application.getOrderLibrary().deleteOrder((String)orderNumber);
			refresh();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(newOrderButton))
		{
			new EditOrderWindow(null, Mode.NEW, this);
		}
		else if(e.getSource().equals(editOrderButton))
		{
			editSelectedOrders();
		}
		else if(e.getSource().equals(deleteOrderButton))
		{
			deleteSelectedOrders();
		}
		else if(e.getSource().equals(orderList) && e.getClickCount() == 2)
		{
			editSelectedOrders();
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_DELETE:
			deleteSelectedOrders();
			break;
		case KeyEvent.VK_ENTER:
			editSelectedOrders();
			break;
		}
	}

}
