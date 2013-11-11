package ui.components;

import items.Item;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.views.Window;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class ItemOrderComponent extends Component{

	private JButton editButton;

	private JButton deleteButton;

	private Item item;

	public ItemOrderComponent(Item item, Window parent)
	{
		super(parent);

		this.item = item;

		refresh();
	}

	private void refresh()
	{
		setFocusable(true);
		RowSpec[] rowSpec = new RowSpec[(item.getAttributeNames().size() + 1) * 2];
		for(int i = 0; i < rowSpec.length; i++)
		{
			rowSpec[i] = FormFactory.DEFAULT_ROWSPEC;
		}

		//setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default"), }, rowSpec));

		JPanel panel = new JPanel();
		add(panel, "2, 2, fill, fill");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblItemName = new JLabel(item.getItemName());
		lblItemName.setFont(new Font("DejaVu Sans Light", Font.BOLD, 18));
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.insets = new Insets(0, 0, 0, 5);
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 0;
		panel.add(lblItemName, gbc_lblItemName);

		editButton = new JButton("edit");
		editButton.setFocusable(true);
		editButton.addMouseListener(getParent());
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
		gbc_btnEdit.gridx = 1;
		gbc_btnEdit.gridy = 0;
		panel.add(editButton, gbc_btnEdit);

		deleteButton = new JButton("del");
		deleteButton.setFocusable(true);
		deleteButton.addMouseListener(getParent());
		GridBagConstraints gbc_btnDel = new GridBagConstraints();
		gbc_btnDel.gridx = 2;
		gbc_btnDel.gridy = 0;
		panel.add(deleteButton, gbc_btnDel);

		int row = 4;
		for(String attributeName: item.getAttributeNames())
		{
			JLabel attributeLabel = new JLabel(attributeName + ": "
					+ item.getAttribute(attributeName));
			attributeLabel
					.setFont(new Font("DejaVu Sans Light", Font.BOLD, 12));
			add(attributeLabel, "2 " + row++);
		}
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
		refresh();
	}

	public JButton getDeleteButton()
	{
		return deleteButton;
	}

	public JButton getEditButton()
	{
		return editButton;
	}
}
