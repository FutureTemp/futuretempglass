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

import core.Application;

@SuppressWarnings("serial")
public class ItemListWindow extends Window{

	private JPanel contentPane;

	private JButton selectButton;

	private JList<String> itemList;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public ItemListWindow(Window parentWindow) throws Exception
	{
		super(parentWindow);

		List<String> items = Application.getInventoryLibrary().getItemNames();
		String[] itemNames = new String[items.size()];
		for(int i = 0; i < items.size(); i++)
		{
			itemNames[i] = items.get(i);
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 178, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {
				0,
				0
		};
		gbl_contentPane.rowHeights = new int[] {
				0,
				0,
				0
		};
		gbl_contentPane.columnWeights = new double[] {
				1.0,
				Double.MIN_VALUE
		};
		gbl_contentPane.rowWeights = new double[] {
				1.0,
				0.0,
				Double.MIN_VALUE
		};
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

		itemList = new JList<String>(itemNames);
		itemList.addMouseListener(this);
		itemList.addKeyListener(this);
		panel.add(itemList);

		selectButton = new JButton("Select");
		selectButton.addMouseListener(this);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 1;
		contentPane.add(selectButton, gbc_btnSelect);

		setVisible(true);
	}

	private void sendItemNamesToParent() throws Exception
	{
		int[] indecies = itemList.getSelectedIndices();
		List<Object> itemNames = new ArrayList<Object>();
		for(int index: indecies)
		{
			itemNames.add(this.itemList.getModel().getElementAt(index));
		}
		getParent().sendData(this, itemNames);
		dispose();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		try
		{
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_ENTER:
				sendItemNamesToParent();
				break;
			}
		}
		catch(Exception e1)
		{
			// TODO
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		try
		{
			if(e.getSource().equals(selectButton))
			{
				sendItemNamesToParent();
			}
			else if(e.getSource().equals(itemList) && e.getClickCount() == 2)
			{
				sendItemNamesToParent();
			}
		}
		catch(Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
