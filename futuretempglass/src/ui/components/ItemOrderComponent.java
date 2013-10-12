package ui.components;

import items.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ItemOrderComponent extends JPanel{
	private JTextField txtItemName;
	private JTextField attribute1;
	private JTextField txtAttributeValue;

	private Item item;

	public ItemOrderComponent()
	{
		this(new Item());
	}

	public ItemOrderComponent(Item item)
	{
		this.item = item;
		RowSpec[] rowSpec = new RowSpec[item.getAttributeNames().size() * 2 + 2];
		for(int i = 0; i < item.getAttributeNames().size() * 2; i++)
		{
			rowSpec[i++] = FormFactory.RELATED_GAP_ROWSPEC;
			rowSpec[i] = FormFactory.DEFAULT_ROWSPEC;
		}
		rowSpec[rowSpec.length - 2] = FormFactory.RELATED_GAP_ROWSPEC;
		rowSpec[rowSpec.length - 1] = RowSpec.decode("max(22dlu;default):grow");

		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, rowSpec));

		JPanel itemNamePanel = new JPanel();
		add(itemNamePanel, "2, 2, fill, fill");
		GridBagLayout gbl_itemNamePanel = new GridBagLayout();
		gbl_itemNamePanel.columnWidths = new int[] { 62, 0, 0 };
		gbl_itemNamePanel.rowHeights = new int[] { 0, 0 };
		gbl_itemNamePanel.columnWeights = new double[] { 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_itemNamePanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		itemNamePanel.setLayout(gbl_itemNamePanel);

		txtItemName = new JTextField();
		txtItemName.setEditable(false);
		txtItemName.setText(item.getItemName());
		GridBagConstraints gbc_txtItemName = new GridBagConstraints();
		gbc_txtItemName.insets = new Insets(0, 0, 0, 5);
		gbc_txtItemName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtItemName.gridx = 0;
		gbc_txtItemName.gridy = 0;
		itemNamePanel.add(txtItemName, gbc_txtItemName);
		txtItemName.setColumns(10);

		JButton editButton = new JButton("edit");
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.gridx = 1;
		gbc_editButton.gridy = 0;
		itemNamePanel.add(editButton, gbc_editButton);

		for(int i = 0; i < item.getAttributeNames().size(); i++)
		{
			JTextField attributeText = new JTextField();
			String display = item.getAttributeNames().get(i) + ": ";
			display += item.getAttribute(item.getAttributeNames().get(i));
			attributeText.setText(display);
			attributeText.setEditable(false);
			// attributeText.setColumns(10);
			add(attributeText, "2, " + (i + 2) * 2 + ", fill, top");
		}
		setVisible(true);
	}

}
