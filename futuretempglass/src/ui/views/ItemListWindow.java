package ui.views;

import items.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import xml.InventoryXml;

public class ItemListWindow extends Window implements MouseListener{

	private Window parentWindow;
	
	private JPanel contentPane;
	
	private JButton selectButton;
	
	private JList itemList;

	/**
	 * Create the frame.
	 */
	public ItemListWindow(Window parentWindow)
	{
		this.parentWindow = parentWindow;
		
		List<Item> items = InventoryXml.getItems();
		String[] itemNames = new String[items.size()];
		for(int i = 0; i < items.size(); i++)
		{
			itemNames[i] = items.get(i).getItemName();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 178, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		itemList = new JList(itemNames);
		panel.add(itemList);
		
		selectButton = new JButton("Select");
		selectButton.addMouseListener(this);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 1;
		contentPane.add(selectButton, gbc_btnSelect);
		
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(selectButton))
		{
			int[] indecies = itemList.getSelectedIndices();
			List<Object> itemNames = new ArrayList<Object>();
			for(int index: indecies)
			{
				itemNames.add(this.itemList.getModel().getElementAt(index));
			}
			parentWindow.sendMessage(this, itemNames);
			dispose();
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
