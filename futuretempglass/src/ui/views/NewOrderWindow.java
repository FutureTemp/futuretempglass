package ui.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ui.components.InputQuestion;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class NewOrderWindow extends JFrame implements Window, MouseListener{

	ItemListWindow itemListWindow;
	
	JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public NewOrderWindow()
	{
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(139dlu;default):grow"), },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("max(164dlu;default):grow"), }));

		btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(this);
		getContentPane().add(btnNewButton, "2, 2, default, bottom");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, "4, 2, fill, fill");

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		InputQuestion inputQuestion = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion = new GridBagConstraints();
		gbc_inputQuestion.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion.gridx = 0;
		gbc_inputQuestion.gridy = 0;
		panel.add(inputQuestion, gbc_inputQuestion);

		InputQuestion inputQuestion_1 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_1 = new GridBagConstraints();
		gbc_inputQuestion_1.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion_1.gridx = 0;
		gbc_inputQuestion_1.gridy = 1;
		panel.add(inputQuestion_1, gbc_inputQuestion_1);

		InputQuestion inputQuestion_3 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_3 = new GridBagConstraints();
		gbc_inputQuestion_3.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion_3.gridx = 0;
		gbc_inputQuestion_3.gridy = 2;
		panel.add(inputQuestion_3, gbc_inputQuestion_3);

		InputQuestion inputQuestion_2 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_2 = new GridBagConstraints();
		gbc_inputQuestion_2.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion_2.gridx = 0;
		gbc_inputQuestion_2.gridy = 3;
		panel.add(inputQuestion_2, gbc_inputQuestion_2);

		InputQuestion inputQuestion_4 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_4 = new GridBagConstraints();
		gbc_inputQuestion_4.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion_4.gridx = 0;
		gbc_inputQuestion_4.gridy = 4;
		panel.add(inputQuestion_4, gbc_inputQuestion_4);

		InputQuestion inputQuestion_5 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_5 = new GridBagConstraints();
		gbc_inputQuestion_5.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion_5.gridx = 0;
		gbc_inputQuestion_5.gridy = 5;
		panel.add(inputQuestion_5, gbc_inputQuestion_5);

		InputQuestion inputQuestion_9 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_9 = new GridBagConstraints();
		gbc_inputQuestion_9.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion_9.gridx = 0;
		gbc_inputQuestion_9.gridy = 6;
		panel.add(inputQuestion_9, gbc_inputQuestion_9);

		InputQuestion inputQuestion_6 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_6 = new GridBagConstraints();
		gbc_inputQuestion_6.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion_6.gridx = 0;
		gbc_inputQuestion_6.gridy = 7;
		panel.add(inputQuestion_6, gbc_inputQuestion_6);

		InputQuestion inputQuestion_8 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_8 = new GridBagConstraints();
		gbc_inputQuestion_8.insets = new Insets(0, 0, 5, 0);
		gbc_inputQuestion_8.gridx = 0;
		gbc_inputQuestion_8.gridy = 8;
		panel.add(inputQuestion_8, gbc_inputQuestion_8);

		InputQuestion inputQuestion_7 = new InputQuestion((String)null,
				(String)null);
		GridBagConstraints gbc_inputQuestion_7 = new GridBagConstraints();
		gbc_inputQuestion_7.gridx = 0;
		gbc_inputQuestion_7.gridy = 9;
		panel.add(inputQuestion_7, gbc_inputQuestion_7);

		pack();
		setVisible(true);
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

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(btnNewButton))
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
