package ui.components;

import items.Item;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ui.views.Window;
import core.Application;

public class ItemWorkFlowComponent extends Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Item item;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public ItemWorkFlowComponent(Item item, Window parentWindow) throws Exception
	{
		super(parentWindow);

		this.item = item;

		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				item.getItemName(), TitledBorder.LEADING, TitledBorder.TOP,
				null, Color.BLACK));
		((TitledBorder)getBorder()).setTitleFont(new Font("Verdana", Font.BOLD,
				18));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblOrder = new JLabel(item.getOrderNumber());
		GridBagConstraints gbc_lblOrder = new GridBagConstraints();
		gbc_lblOrder.insets = new Insets(0, 0, 5, 0);
		gbc_lblOrder.gridx = 0;
		gbc_lblOrder.gridy = 0;
		add(lblOrder, gbc_lblOrder);

		JLabel lblItemName = new JLabel(Application.getOrderLibrary()
				.getOrder(item.getOrderNumber()).getCustomer());
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 1;
		add(lblItemName, gbc_lblItemName);

	}

}
