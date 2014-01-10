package storage.database;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ui.views.Window;

public class QueryWindow extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable resultsTable;
	private JTextField dbAddressField;
	private JTextField dbNameField;
	private JTextArea queryArea;
	private JButton executeButton;
	private JLabel errorLabel;
	private JLabel lblUsername;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			public void run()
			{
				try
				{
					QueryWindow frame = new QueryWindow();
					frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QueryWindow()
	{
		super(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { Double.MIN_VALUE,
				Double.MIN_VALUE, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel infoPanel = new JPanel();
		GridBagConstraints gbc_infoPanel = new GridBagConstraints();
		gbc_infoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_infoPanel.fill = GridBagConstraints.BOTH;
		gbc_infoPanel.gridx = 0;
		gbc_infoPanel.gridy = 0;
		contentPane.add(infoPanel, gbc_infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_infoPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_infoPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_infoPanel.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		infoPanel.setLayout(gbl_infoPanel);

		JLabel lblDbName = new JLabel("DB Name");
		GridBagConstraints gbc_lblDbName = new GridBagConstraints();
		gbc_lblDbName.anchor = GridBagConstraints.EAST;
		gbc_lblDbName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDbName.gridx = 0;
		gbc_lblDbName.gridy = 0;
		infoPanel.add(lblDbName, gbc_lblDbName);

		dbNameField = new JTextField();
		GridBagConstraints gbc_dbNameField = new GridBagConstraints();
		gbc_dbNameField.insets = new Insets(0, 0, 5, 5);
		gbc_dbNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dbNameField.gridx = 1;
		gbc_dbNameField.gridy = 0;
		infoPanel.add(dbNameField, gbc_dbNameField);
		dbNameField.setColumns(10);

		JLabel lblDbAddress = new JLabel("DB Server Address");
		GridBagConstraints gbc_lblDbAddress = new GridBagConstraints();
		gbc_lblDbAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblDbAddress.anchor = GridBagConstraints.EAST;
		gbc_lblDbAddress.gridx = 2;
		gbc_lblDbAddress.gridy = 0;
		infoPanel.add(lblDbAddress, gbc_lblDbAddress);

		dbAddressField = new JTextField();
		GridBagConstraints gbc_dbAddressField = new GridBagConstraints();
		gbc_dbAddressField.insets = new Insets(0, 0, 5, 0);
		gbc_dbAddressField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dbAddressField.gridx = 3;
		gbc_dbAddressField.gridy = 0;
		infoPanel.add(dbAddressField, gbc_dbAddressField);
		dbAddressField.setColumns(10);

		executeButton = new JButton("Excecute");
		executeButton.addMouseListener(this);

		lblUsername = new JLabel("Username");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		infoPanel.add(lblUsername, gbc_lblUsername);

		usernameField = new JTextField();
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 1;
		gbc_usernameField.gridy = 1;
		infoPanel.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);

		lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 1;
		infoPanel.add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 1;
		infoPanel.add(passwordField, gbc_passwordField);
		GridBagConstraints gbc_executeButton = new GridBagConstraints();
		gbc_executeButton.gridx = 3;
		gbc_executeButton.gridy = 2;
		infoPanel.add(executeButton, gbc_executeButton);

		JPanel queryPanel = new JPanel();
		GridBagConstraints gbc_queryPanel = new GridBagConstraints();
		gbc_queryPanel.insets = new Insets(0, 0, 5, 0);
		gbc_queryPanel.fill = GridBagConstraints.BOTH;
		gbc_queryPanel.gridx = 0;
		gbc_queryPanel.gridy = 1;
		contentPane.add(queryPanel, gbc_queryPanel);
		GridBagLayout gbl_queryPanel = new GridBagLayout();
		gbl_queryPanel.columnWidths = new int[] { 0, 0 };
		gbl_queryPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_queryPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_queryPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		queryPanel.setLayout(gbl_queryPanel);

		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorLabel = new GridBagConstraints();
		gbc_errorLabel.insets = new Insets(0, 0, 5, 0);
		gbc_errorLabel.gridx = 0;
		gbc_errorLabel.gridy = 0;
		queryPanel.add(errorLabel, gbc_errorLabel);

		queryArea = new JTextArea();
		queryArea.setTabSize(4);
		GridBagConstraints gbc_queryArea = new GridBagConstraints();
		gbc_queryArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_queryArea.anchor = GridBagConstraints.NORTH;
		gbc_queryArea.gridx = 0;
		gbc_queryArea.gridy = 1;
		queryPanel.add(queryArea, gbc_queryArea);

		JScrollPane resultsScrollPane = new JScrollPane();
		GridBagConstraints gbc_resultsScrollPane = new GridBagConstraints();
		gbc_resultsScrollPane.fill = GridBagConstraints.BOTH;
		gbc_resultsScrollPane.gridx = 0;
		gbc_resultsScrollPane.gridy = 2;
		contentPane.add(resultsScrollPane, gbc_resultsScrollPane);

		JPanel resultsPanel = new JPanel();
		resultsScrollPane.setViewportView(resultsPanel);

		resultsTable = new JTable();
		resultsTable.setEnabled(false);
		resultsPanel.add(resultsTable);
		
		dbNameField.setText("futuretemp");
		dbAddressField.setText("127.0.0.1:3306/");
		usernameField.setText("root");
	}

	private void executeQuery()
	{
		DBHelper.setConnectionURL(dbAddressField.getText());
		DBHelper.setUserName(usernameField.getText());
		DBHelper.setPassword(new String(passwordField.getPassword()));
		String query = "";//"USE " + dbNameField.getText() + "\n";
		query += queryArea.getText();
		DBResults results = DBHelper.queryDb(query);
		populateResultsTable(results);
	}

	private void displayError(String error)
	{
		errorLabel.setText(error);
	}

	private void populateResultsTable(DBResults results)
	{
		try
		{
			if(results == null)
			{
				return;
			}
			resultsTable
					.setModel(new DefaultTableModel(results.getAllResults(),
							results.getColumnNames().toArray()));
		}
		catch(Exception e)
		{
			displayError(e.getMessage());
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if(executeButton.equals(e.getSource()))
		{
			executeQuery();
		}
	}

}
