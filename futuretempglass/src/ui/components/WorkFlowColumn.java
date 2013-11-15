package ui.components;

import items.Item;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ui.views.Window;

public class WorkFlowColumn extends Component{

	private List<Item> items;

	private List<ItemWorkFlowComponent> itemComponents;

	/**
	 * Create the panel.
	 */
	public WorkFlowColumn(List<Item> items, Window parentWindow)
	{
		super(parentWindow);

		this.items = items;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblProductionStep = new JLabel("Production Step");
		lblProductionStep.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductionStep.setFont(new Font("SansSerif", Font.PLAIN, 26));
		GridBagConstraints gbc_lblProductionStep = new GridBagConstraints();
		gbc_lblProductionStep.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductionStep.gridx = 0;
		gbc_lblProductionStep.gridy = 0;
		add(lblProductionStep, gbc_lblProductionStep);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		JPanel itemsInStepPanel = new JPanel();
		scrollPane.setViewportView(itemsInStepPanel);
		GridBagLayout gbl_itemsInStepPanel = new GridBagLayout();

		int[] rowHeights = new int[items.size() + 1];
		double[] rowWeights = new double[items.size() + 1];
		rowHeights[items.size()] = 0;
		rowWeights[items.size()] = Double.MIN_VALUE;
		for(int i = 0; i < items.size(); i++)
		{
			rowHeights[i] = 0;
			rowWeights[i] = 0;
		}

		gbl_itemsInStepPanel.columnWidths = new int[] { 0, 0 };
		gbl_itemsInStepPanel.rowHeights = rowHeights;
		gbl_itemsInStepPanel.columnWeights = new double[] { 1.0,
				Double.MIN_VALUE };
		gbl_itemsInStepPanel.rowWeights = rowWeights;
		itemsInStepPanel.setLayout(gbl_itemsInStepPanel);

		itemComponents = new ArrayList<ItemWorkFlowComponent>();
		for(int i = 0; i < items.size(); i++)
		{
			ItemWorkFlowComponent itemWorkFlowComponent = new ItemWorkFlowComponent(
					items.get(i), getParent());
			GridBagConstraints gbc_itemWorkFlowComponent = new GridBagConstraints();
			gbc_itemWorkFlowComponent.insets = new Insets(0, 0, 5, 0);
			gbc_itemWorkFlowComponent.fill = GridBagConstraints.HORIZONTAL;
			gbc_itemWorkFlowComponent.gridx = 0;
			gbc_itemWorkFlowComponent.gridy = i;
			itemsInStepPanel.add(itemWorkFlowComponent,
					gbc_itemWorkFlowComponent);
			itemComponents.add(itemWorkFlowComponent);
		}

	}

}
