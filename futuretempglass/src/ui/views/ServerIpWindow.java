package ui.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

import core.Client;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ServerIpWindow extends JFrame implements MouseListener{

	private JPanel contentPane;
	private JTextField textField;
	private JButton doneButton;

	/**
	 * Create the frame.
	 */
	public ServerIpWindow()
	{
		setTitle("Enter Server IP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 214, 106);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 44, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		doneButton = new JButton("Done");
		doneButton.addMouseListener(this);
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.anchor = GridBagConstraints.SOUTH;
		gbc_doneButton.gridx = 0;
		gbc_doneButton.gridy = 1;
		contentPane.add(doneButton, gbc_doneButton);
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(doneButton))
		{
			Client.serverIp = textField.getText().trim();
		}
		new NewOrderWindow();
		dispose();
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
