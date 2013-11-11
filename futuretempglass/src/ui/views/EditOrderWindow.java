package ui.views;

import items.Item;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import orders.Order;
import ui.Mode;
import ui.components.ItemOrderComponent;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Application;

@SuppressWarnings("serial")
public class EditOrderWindow extends Window implements MouseListener{

	private ItemListWindow itemListWindow;

	JButton addItemButton;

	private Order order;

	private Mode mode;

	private JPanel itemsPanel;
	private JTextField customerField;
	private JLabel customerLabel;
	private JTextField orderNumberField;
	private JLabel orderNumberLabel;
	private JLabel entryDateLabel;
	private JLabel dueDateLabel;
	private JTextField entryDateField;
	private JTextField dueDateField;
	private JButton btnDone;

	private List<ItemOrderComponent> items;

	public EditOrderWindow(Window parentWindow)
	{
		this(null, Mode.NEW, parentWindow);
	}

	/**
	 * Create the frame.
	 */
	public EditOrderWindow(Order order, Mode mode, Window parentWindow)
	{
		super(parentWindow);
		this.mode = mode;
		setFocusable(true);
		if(order == null)
		{
			order = new Order();
		}
		this.order = order;
		if(order.getItems() == null)
		{
			order.setItems(new ArrayList<Item>());
		}

		switch (mode)
		{
		case EDIT:
			setTitle("Edit Order " + order.getOrderNumber());
			break;
		case NEW:
			setTitle("Order Entry");
			break;
		}

		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		JPanel informationPane = new JPanel();
		getContentPane().add(informationPane, "2, 2, fill, fill");

		addItemButton = new JButton("Add Item");
		addItemButton.addMouseListener(this);
		informationPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("96px:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("25px"), }));

		orderNumberLabel = new JLabel("Order #: ");
		informationPane.add(orderNumberLabel, "2, 2, right, default");

		orderNumberField = new JTextField();
		informationPane.add(orderNumberField, "4, 2, fill, default");
		orderNumberField.setColumns(10);

		if(mode == Mode.NEW)
		{
			order.setOrderNumber(Application.getOrderLibrary().getNextOrderNumber());
		}
		orderNumberField.setText(order.getOrderNumber());
		
		customerLabel = new JLabel("Customer: ");
		informationPane.add(customerLabel, "2, 4, right, default");

		customerField = new JTextField();
		informationPane.add(customerField, "4, 4, fill, default");
		customerField.setColumns(10);

		entryDateLabel = new JLabel("Entry Date: ");
		informationPane.add(entryDateLabel, "2, 6, right, default");

		entryDateField = new JTextField();
		informationPane.add(entryDateField, "4, 6, fill, default");
		entryDateField.setColumns(10);

		dueDateLabel = new JLabel("Due Date: ");
		informationPane.add(dueDateLabel, "2, 8, right, default");

		dueDateField = new JTextField();
		informationPane.add(dueDateField, "4, 8, fill, default");
		dueDateField.setColumns(10);

		btnDone = new JButton("Done");
		btnDone.addMouseListener(this);
		informationPane.add(btnDone, "2, 10");
		informationPane.add(addItemButton, "4, 10, left, top");
		getContentPane().add(scrollPane, "2, 4, fill, fill");

		itemsPanel = new JPanel();
		scrollPane.setViewportView(itemsPanel);
		/*
		 * scrollPane.setViewportView(itemsPanel); itemsPanel.setLayout(new
		 * FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
		 * ColumnSpec.decode("default:grow"), }, new RowSpec[] {
		 * FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
		 * FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
		 * FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
		 * FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));
		 * 
		 * ItemOrderComponent itemOrderComponent_3 = new ItemOrderComponent();
		 * itemsPanel.add(itemOrderComponent_3, "2, 2, fill, fill");
		 * 
		 * ItemOrderComponent itemOrderComponent_2 = new ItemOrderComponent();
		 * itemsPanel.add(itemOrderComponent_2, "2, 4, fill, fill");
		 * 
		 * ItemOrderComponent itemOrderComponent_1 = new ItemOrderComponent();
		 * itemsPanel.add(itemOrderComponent_1, "2, 6, fill, fill");
		 * 
		 * ItemOrderComponent itemOrderComponent = new ItemOrderComponent();
		 * itemsPanel.add(itemOrderComponent, "2, 8, fill, fill");
		 */
		refresh();
		// pack();
		setVisible(true);
	}

	public void refresh()
	{
		// removeAll();
		// setContentPane(new JPanel());
		RowSpec[] rowSpec = new RowSpec[order.getItems().size() * 2];
		for(int i = 0; i < rowSpec.length; i++)
		{
			rowSpec[i++] = FormFactory.RELATED_GAP_ROWSPEC;
			rowSpec[i] = RowSpec.decode("default:grow");
		}

		itemsPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, rowSpec));

		if(items != null)
		{
			for(ItemOrderComponent item: items)
			{
				item.removeAll();
			}
			items.clear();
		}
		items = new ArrayList<ItemOrderComponent>();
		for(int i = 0; i < order.getItems().size(); i++)
		{
			ItemOrderComponent itemComponent = new ItemOrderComponent(order
					.getItems().get(i), this);
			itemsPanel.add(itemComponent, "2, " + (i + 1) * 2 + ", fill, fill");
			items.add(itemComponent);
		}
		repaint();
		setVisible(true);
		pack();
	}

	private void deleteItem(Item item)
	{
		order.getItems().remove(item);
		refresh();
	}

	private void editItem(Item item)
	{
		new EditItemWindow(this, item, Mode.EDIT);
	}

	private void saveThisOrder()
	{
		order.setOrderNumber(orderNumberField.getText());
		switch(mode)
		{
		case NEW:
			Application.getOrderLibrary().addOrder(order);
			break;
		case EDIT:
			Application.getOrderLibrary().updateOrder(order);
			break;
		}
		if(getParent() != null)
		{
			getParent().refresh();
		}
		dispose();
	}

	private void openItemWindows(List<String> itemNames)
	{
		for(String itemName: itemNames)
		{
			new EditItemWindow(this, Application.getInventoryLibrary().getItem(
					itemName), Mode.NEW);
		}
	}

	private void addOrUpdateItem(Item item)
	{
		boolean exists = false;
		for(int i = 0; i < order.getItems().size(); i++)
		{
			if(order.getItems().get(i).equals(item))
			{
				order.getItems().remove(i);
				order.getItems().add(i, item);
				exists = true;
			}
		}
		if(!exists)
		{
			order.getItems().add(item);
		}
		if(item.getItemId() == null)
		{
			item.setItemId(Application.getItemLibrary().getAvailableId());
		}
		refresh();
	}

	@Override
	public void sendData(JFrame source, Object object)
	{
		super.sendData(source, object);
		if(source.equals(itemListWindow))
		{
			openItemWindows((List<String>)object);
		}
		else if(source instanceof EditItemWindow)
		{
			addOrUpdateItem((Item)object);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(addItemButton))
		{
			itemListWindow = new ItemListWindow(this);
		}
		else if(e.getSource().equals(btnDone))
		{
			saveThisOrder();
		}

		for(ItemOrderComponent item: items)
		{
			if(e.getSource() == item.getEditButton())
			{
				editItem(item.getItem());
			}
			else if(e.getSource() == item.getDeleteButton())
			{
				deleteItem(item.getItem());
				return;
			}
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
