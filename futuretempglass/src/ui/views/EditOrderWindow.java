package ui.views;

import items.Item;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import orders.Order;
import ui.Mode;
import ui.components.ItemOrderComponent;
import utils.ItemUtils;
import utils.OrderUtils;
import utils.StringUtils;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Application;

@SuppressWarnings("serial")
public class EditOrderWindow extends Window{

	private ItemListWindow itemListWindow;

	JButton addItemButton;

	private Order order;

	private List<Item> items = new ArrayList<Item>();

	private List<Item> newItems = new ArrayList<Item>();

	private List<Item> updateItems = new ArrayList<Item>();

	private List<Item> deletedItems = new ArrayList<Item>();

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

	private List<ItemOrderComponent> itemComponents;

	public EditOrderWindow(Window parentWindow) throws Exception
	{
		this(null, Mode.NEW, parentWindow);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public EditOrderWindow(Order order, Mode mode, Window parentWindow)
			throws Exception
	{
		super(parentWindow);
		this.mode = mode;
		setFocusable(true);
		if(order == null)
		{
			order = new Order();
		}
		this.order = order;
		if(order.getItemIds() == null)
		{
			order.setItemIds(new ArrayList<String>());
		}

		switch (mode)
		{
		case EDIT:
			setTitle("Edit Order " + order.getOrderNumber());
			items = ItemUtils.getItems(order.getItemIds());
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
			order.setOrderNumber(OrderUtils.getNextOrderNumber());
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
		RowSpec[] rowSpec = new RowSpec[items.size() * 2];
		for(int i = 0; i < rowSpec.length; i++)
		{
			rowSpec[i++] = FormFactory.RELATED_GAP_ROWSPEC;
			rowSpec[i] = RowSpec.decode("default:grow");
		}

		itemsPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, rowSpec));

		if(itemComponents != null)
		{
			for(ItemOrderComponent item: itemComponents)
			{
				item.removeAll();
			}
			itemComponents.clear();
		}
		itemComponents = new ArrayList<ItemOrderComponent>();
		for(int i = 0; i < items.size(); i++)
		{
			ItemOrderComponent itemComponent = new ItemOrderComponent(
					items.get(i), this);
			itemsPanel.add(itemComponent, "2, " + (i + 1) * 2 + ", fill, fill");
			itemComponents.add(itemComponent);
		}
		repaint();
		setVisible(true);
		pack();
	}

	private void deleteItem(Item item)
	{
		if(newItems.contains(item))
		{
			newItems.remove(item);
		}
		else
		{
			deletedItems.add(item);
		}
		items.remove(item);
		order.getItemIds().remove(item.getItemId());
		refresh();
	}

	private void editItem(Item item)
	{
		new EditItemWindow(this, item, Mode.EDIT);
	}

	private void saveThisOrder() throws Exception
	{
		order.setOrderNumber(orderNumberField.getText());
		order.setCustomer(customerField.getText());
		switch (mode)
		{
		case NEW:
			for(int i = 0; i < order.getItemIds().size(); i++)
			{
				if(order.getItemIds().get(i).startsWith("Temp-"))
				{
					order.getItemIds().remove(i);
					i--;
				}
			}
			for(Item item: newItems)
			{
				item.setItemId("");
				item.setOrderNumber(order.getOrderNumber());
			}
			OrderUtils.addOrder(order);
			ItemUtils.addItems(newItems);

			break;
		case EDIT:
			OrderUtils.updateOrder(order);

			for(Item item: newItems)
			{
				item.setOrderNumber(order.getOrderNumber());
				ItemUtils.addItem(item);
			}
			for(Item item: updateItems)
			{
				ItemUtils.updateItem(item);
			}
			for(Item item: deletedItems)
			{
				ItemUtils.deleteItem(item.getItemId());
			}

			break;
		}
		if(getParent() != null)
		{
			getParent().refresh();
		}
	}

	private void openItemWindows(List<String> itemNames) throws Exception
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
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).equals(item))
			{
				items.remove(i);
				items.add(i, item);
				if(!updateItems.contains(item) && !newItems.contains(item))
				{
					updateItems.add(item);
				}
				exists = true;
			}
		}
		if(!exists)
		{
			if(item.getItemId() == null)
			{
				item.setItemId("Temp-"
						+ StringUtils.getRandomStringOfLettersAndNumbers(8));
			}
			items.add(item);
			newItems.add(item);
			order.getItemIds().add(item.getItemId());
			item.setOrderNumber(order.getOrderNumber());
		}
		refresh();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendData(Window source, Object object) throws Exception
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
		try
		{
			if(e.getSource().equals(addItemButton))
			{
				itemListWindow = new ItemListWindow(this);
			}
			else if(e.getSource().equals(btnDone))
			{
				saveThisOrder();
				dispose();
			}

			for(ItemOrderComponent item: itemComponents)
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
		catch(Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
