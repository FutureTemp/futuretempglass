package ui.views;

import items.Item;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import xml.InventoryXml;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

public class ItemsListWindow extends JFrame{

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public ItemsListWindow()
	{
		List<Item> items = InventoryXml.getItems();
		String[] itemStrings = new String[items.size()];
		for(int i = 0; i < items.size(); i++)
		{
			itemStrings[i] = items.get(i).getItemName();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JList list = new JList(itemStrings);
		panel.add(list);
		setVisible(true);
	}

}
