package ui.views;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Application;

import workflow.ProductionStep;

public class ProductionStepsSelectWindow extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private List<ProductionStep> productionSteps;

	private JButton doneButton;

	private JButton cancelButton;

	private List<JCheckBox> stepCheckBoxes;

	public ProductionStepsSelectWindow(Window parentWindow)
	{
		this(parentWindow, new ArrayList<ProductionStep>());
	}

	/**
	 * Create the frame.
	 */
	public ProductionStepsSelectWindow(Window parentWindow,
			List<ProductionStep> itemSteps)
	{
		super(parentWindow);
		if(itemSteps == null)
		{
			itemSteps = new ArrayList<ProductionStep>();
		}
		productionSteps = Application.getProductionStepsLibrary()
				.getProductionSteps();

		setTitle("Production Steps");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 260, 348);
		contentPane = new JPanel();
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel productionStepsPanel = new JPanel();
		GridBagConstraints gbc_productionStepsPanel = new GridBagConstraints();
		gbc_productionStepsPanel.anchor = GridBagConstraints.WEST;
		gbc_productionStepsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_productionStepsPanel.fill = GridBagConstraints.VERTICAL;
		gbc_productionStepsPanel.gridx = 0;
		gbc_productionStepsPanel.gridy = 0;
		contentPane.add(productionStepsPanel, gbc_productionStepsPanel);
		GridBagLayout gbl_productionStepsPanel = new GridBagLayout();

		int[] rowHeights = new int[productionSteps.size() + 1];
		double[] rowWeights = new double[productionSteps.size() + 1];
		for(int i = 0; i < productionSteps.size(); i++)
		{
			rowHeights[i] = 0;
			rowWeights[i] = 0;
		}
		rowHeights[productionSteps.size()] = 0;
		rowWeights[productionSteps.size()] = Double.MIN_VALUE;

		gbl_productionStepsPanel.columnWidths = new int[] { 0, 0 };
		gbl_productionStepsPanel.rowHeights = rowHeights;
		gbl_productionStepsPanel.columnWeights = new double[] { 0.0,
				Double.MIN_VALUE };
		gbl_productionStepsPanel.rowWeights = rowWeights;
		productionStepsPanel.setLayout(gbl_productionStepsPanel);

		stepCheckBoxes = new ArrayList<JCheckBox>();
		for(int i = 0; i < productionSteps.size(); i++)
		{
			JCheckBox stepCheckBox = new JCheckBox(productionSteps.get(i)
					.getName());
			stepCheckBoxes.add(stepCheckBox);
			GridBagConstraints gbc_stepCheckBox = new GridBagConstraints();
			gbc_stepCheckBox.insets = new Insets(0, 0, 5, 0);
			gbc_stepCheckBox.anchor = GridBagConstraints.WEST;
			gbc_stepCheckBox.gridx = 0;
			gbc_stepCheckBox.gridy = i;
			if(itemSteps.contains(productionSteps.get(i)))
			{
				stepCheckBox.setSelected(true);
			}
			productionStepsPanel.add(stepCheckBox, gbc_stepCheckBox);
		}

		JPanel footerPanel = new JPanel();
		GridBagConstraints gbc_footerPanel = new GridBagConstraints();
		gbc_footerPanel.fill = GridBagConstraints.BOTH;
		gbc_footerPanel.gridx = 0;
		gbc_footerPanel.gridy = 1;
		contentPane.add(footerPanel, gbc_footerPanel);
		footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		doneButton = new JButton("Done");
		doneButton.addMouseListener(this);
		footerPanel.add(doneButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(this);
		footerPanel.add(cancelButton);

		setVisible(true);
		pack();
	}

	private List<ProductionStep> getSelectedProductionSteps()
	{
		List<ProductionStep> productionSteps = new ArrayList<ProductionStep>();
		for(JCheckBox checkBox: stepCheckBoxes)
		{
			if(checkBox.isSelected())
			{
				String stepName = checkBox.getText();
				for(ProductionStep step: this.productionSteps)
				{
					if(stepName.equals(step.getName()))
					{
						productionSteps.add(step);
					}
				}
			}
		}
		return productionSteps;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(doneButton))
		{
			getParent().sendData(this, getSelectedProductionSteps());
			dispose();
		}
		else if(e.getSource().equals(cancelButton))
		{
			dispose();
		}
	}
}
