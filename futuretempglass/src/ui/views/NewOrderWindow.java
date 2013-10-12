package ui.views;

import items.Item;

import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import orders.Order;
import ui.components.ItemOrderComponent;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class NewOrderWindow extends Window implements MouseListener{

	private ItemListWindow itemListWindow;
	
	JButton addItemButton;

	private Order order;

	private JPanel itemsPanel;

	public NewOrderWindow()
	{
		this(new Order());
	}

	/**
	 * Create the frame.
	 */
	public NewOrderWindow(Order order)
	{
		this.order = order;
		if(order.getItems() == null)
		{
			order.setItems(new ArrayList<Item>());
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		informationPane.add(addItemButton);
		getContentPane().add(scrollPane, "2, 4, fill, fill");

		itemsPanel = new JPanel();
		scrollPane.setViewportView(itemsPanel);
		/*scrollPane.setViewportView(itemsPanel);
		itemsPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), }));

		ItemOrderComponent itemOrderComponent_3 = new ItemOrderComponent();
		itemsPanel.add(itemOrderComponent_3, "2, 2, fill, fill");

		ItemOrderComponent itemOrderComponent_2 = new ItemOrderComponent();
		itemsPanel.add(itemOrderComponent_2, "2, 4, fill, fill");

		ItemOrderComponent itemOrderComponent_1 = new ItemOrderComponent();
		itemsPanel.add(itemOrderComponent_1, "2, 6, fill, fill");

		ItemOrderComponent itemOrderComponent = new ItemOrderComponent();
		itemsPanel.add(itemOrderComponent, "2, 8, fill, fill");*/
		populateItemsInView();
		// pack();
		setVisible(true);
	}

	private void populateItemsInView()
	{
		RowSpec[] rowSpec = new RowSpec[order.getItems().size() * 2];
		for(int i = 0; i < rowSpec.length; i++)
		{
			rowSpec[i++] = FormFactory.RELATED_GAP_ROWSPEC;
			rowSpec[i] = RowSpec.decode("default:grow");
		}

		itemsPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, rowSpec));

		for(int i = 0; i < order.getItems().size(); i++)
		{
			ItemOrderComponent itemComponent = new ItemOrderComponent(order
					.getItems().get(i));
			itemsPanel.add(itemComponent, "2, " + (i + 1) * 2 + ", fill, fill");
		}
		itemsPanel.setVisible(true);
		pack();
	}

	@Override
	public void sendMessage(JFrame source, Object object)
	{
		if(source.equals(itemListWindow))
		{
			List<String> itemNames = (List<String>)object;
			for(String itemName: itemNames)
			{
				new EditItemWindow(this, itemName);
			}
		}
		else if(source instanceof EditItemWindow)
		{
			Item item = (Item)object;
			order.getItems().add(item);
			populateItemsInView();
			System.out.println(item.getItemName());
			for(String attributeName: item.getAttributeNames())
			{
				System.out.println(attributeName + " : "
						+ item.getAttribute(attributeName));
			}
			System.out.println();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(addItemButton))
		{
			itemListWindow = new ItemListWindow(this);
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
