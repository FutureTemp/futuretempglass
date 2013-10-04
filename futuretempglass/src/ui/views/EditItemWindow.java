package ui.views;

import items.Item;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditItemWindow extends JFrame{

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public EditItemWindow(Window parentWindow, String itemName)
	{
		setTitle(itemName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setVisible(true);
	}

}
