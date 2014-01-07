package ui.views;

import items.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import ui.components.WorkFlowColumn;
import utils.ItemUtils;
import workflow.ProductionStep;
import core.Application;

public class WorkFlowWindow extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final long REFRESH_INTERVAL = 3000; // 3 seconds

	private JPanel contentPanel;

	private List<ProductionStep> productionSteps;

	private List<WorkFlowColumn> columns = new ArrayList<WorkFlowColumn>();

	private HashMap<String, List<Item>> itemLists;

	private long lastUpdateTime = -1;

	public WorkFlowWindow(Window parentWindow) throws Exception
	{
		super(parentWindow);
		addMouseMotionListener(this);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		refresh();
	}

	@Override
	public void refresh() throws Exception
	{
		super.refresh();
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();

		productionSteps = Application.getProductionStepsLibrary()
				.getProductionSteps();
		itemLists = getSortedItems();

		int[] columnWidths = new int[productionSteps.size() + 2];
		double[] columnWeights = new double[productionSteps.size() + 2];
		columnWidths[productionSteps.size() + 1] = 0;
		columnWeights[productionSteps.size() + 1] = Double.MIN_VALUE;
		for(int i = 0; i < productionSteps.size() + 1; i++)
		{
			columnWidths[i] = 0;
			columnWeights[i] = Double.MIN_VALUE;
		}

		gbl_contentPanel.columnWidths = columnWidths;
		gbl_contentPanel.rowHeights = new int[] {
				0,
				0
		};
		gbl_contentPanel.columnWeights = columnWeights;
		gbl_contentPanel.rowWeights = new double[] {
				1.0,
				Double.MIN_VALUE
		};
		contentPanel.setLayout(gbl_contentPanel);

		for(int i = 0; i < productionSteps.size(); i++)
		{
			WorkFlowColumn column = new WorkFlowColumn(productionSteps.get(i),
					itemLists.get(productionSteps.get(i).getName()), this);
			GridBagConstraints gbc_column = new GridBagConstraints();
			gbc_column.insets = new Insets(0, 0, 0, 5);
			gbc_column.fill = GridBagConstraints.BOTH;
			gbc_column.gridx = i;
			gbc_column.gridy = 0;
			contentPanel.add(column, gbc_column);
			columns.add(column);
		}

		// pack();
		setVisible(true);
	}

	private HashMap<String, List<Item>> getSortedItems() throws Exception
	{
		HashMap<String, List<Item>> itemLists = new HashMap<String, List<Item>>();
		for(ProductionStep productionStep: this.productionSteps)
		{
			itemLists.put(productionStep.getName(), new ArrayList<Item>());
		}
		List<Item> allItems = ItemUtils.getItems();
		for(Item item: allItems)
		{
			if(item.getCurrentStep() == null)
			{
				continue;
			}
			itemLists.get(item.getCurrentStep().getName()).add(item);
		}
		return itemLists;
	}

	@Override
	protected void processMouseMotionEvent(MouseEvent e)
	{
		try
		{
			super.processMouseMotionEvent(e);
			long current = System.currentTimeMillis();
			if(this.isFocused() && (lastUpdateTime == -1
					|| current - lastUpdateTime > REFRESH_INTERVAL))
			{
				lastUpdateTime = current;
				refresh();
			}
		}
		catch(Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
