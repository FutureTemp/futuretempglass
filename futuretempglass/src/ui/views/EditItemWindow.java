package ui.views;

import items.Item;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import ui.components.InputQuestion;

public class EditItemWindow extends JFrame implements MouseListener{

	private Window parentWindow;
	
	private JPanel contentPane;

	private JButton doneButton;

	private JButton cancelButton;

	private List<InputQuestion> attributeQuestions;
	
	private Item item;

	/**
	 * Create the frame.
	 */
	public EditItemWindow(Window parentWindow, String itemName)
	{
		this.parentWindow = parentWindow;
		
		setTitle(itemName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 228, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 208, 0 };
		gbl_contentPane.rowHeights = new int[] { 300, -66, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0 };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane);

		JPanel scrollPaneContent = new JPanel();
		scrollPane.setViewportView(scrollPaneContent);
		scrollPaneContent.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel buttonPane = new JPanel();
		FlowLayout fl_buttonPane = (FlowLayout)buttonPane.getLayout();
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.insets = new Insets(0, 0, 5, 0);
		gbc_buttonPane.fill = GridBagConstraints.BOTH;
		gbc_buttonPane.gridx = 0;
		gbc_buttonPane.gridy = 1;
		contentPane.add(buttonPane, gbc_buttonPane);

		doneButton = new JButton("Done");
		doneButton.addMouseListener(this);
		buttonPane.add(doneButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(this);
		buttonPane.add(cancelButton);

		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(cancelButton))
		{
			dispose();
		}
		else if(e.getSource().equals(doneButton))
		{
			parentWindow.sendMessage(this, item);
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
