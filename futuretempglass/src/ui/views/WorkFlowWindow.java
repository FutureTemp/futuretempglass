package ui.views;

import items.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;

import ui.components.WorkFlowColumn;

public class WorkFlowWindow extends Window{

	private JPanel contentPanel;

	public WorkFlowWindow(Window parentWindow)
	{
		super(parentWindow);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		WorkFlowColumn workFlowColumn_3 = new WorkFlowColumn(
				new ArrayList<Item>(), this);
		GridBagConstraints gbc_workFlowColumn_3 = new GridBagConstraints();
		gbc_workFlowColumn_3.insets = new Insets(0, 0, 0, 5);
		gbc_workFlowColumn_3.fill = GridBagConstraints.BOTH;
		gbc_workFlowColumn_3.gridx = 0;
		gbc_workFlowColumn_3.gridy = 0;
		contentPanel.add(workFlowColumn_3, gbc_workFlowColumn_3);

		WorkFlowColumn workFlowColumn_2 = new WorkFlowColumn(
				new ArrayList<Item>(), this);
		GridBagConstraints gbc_workFlowColumn_2 = new GridBagConstraints();
		gbc_workFlowColumn_2.insets = new Insets(0, 0, 0, 5);
		gbc_workFlowColumn_2.fill = GridBagConstraints.BOTH;
		gbc_workFlowColumn_2.gridx = 1;
		gbc_workFlowColumn_2.gridy = 0;
		contentPanel.add(workFlowColumn_2, gbc_workFlowColumn_2);

		WorkFlowColumn workFlowColumn_1 = new WorkFlowColumn(
				new ArrayList<Item>(), this);
		GridBagConstraints gbc_workFlowColumn_1 = new GridBagConstraints();
		gbc_workFlowColumn_1.insets = new Insets(0, 0, 0, 5);
		gbc_workFlowColumn_1.fill = GridBagConstraints.BOTH;
		gbc_workFlowColumn_1.gridx = 2;
		gbc_workFlowColumn_1.gridy = 0;
		contentPanel.add(workFlowColumn_1, gbc_workFlowColumn_1);

		WorkFlowColumn workFlowColumn = new WorkFlowColumn(
				new ArrayList<Item>(), this);
		GridBagConstraints gbc_workFlowColumn = new GridBagConstraints();
		gbc_workFlowColumn.fill = GridBagConstraints.BOTH;
		gbc_workFlowColumn.gridx = 3;
		gbc_workFlowColumn.gridy = 0;
		contentPanel.add(workFlowColumn, gbc_workFlowColumn);

		// pack();
		setVisible(true);
	}

}
