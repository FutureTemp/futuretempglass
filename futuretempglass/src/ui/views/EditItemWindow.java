package ui.views;

import items.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import storage.server.ServerItemLibrary;
import ui.components.InputQuestion;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import core.Application;

public class EditItemWindow extends Window implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Window parentWindow;

	private JPanel contentPane;

	private JButton doneButton;

	private JButton addStepsButton;
	
	private JButton cancelButton;

	private List<InputQuestion> attributeQuestions;

	private Item item;

	/**
	 * Create the frame.
	 */
	public EditItemWindow(Window parentWindow, String itemName)
	{
		this.parentWindow = parentWindow;

		attributeQuestions = new ArrayList<InputQuestion>();

		item = Application.getItemLibrary().getItem(itemName);
		for (String attributeName: item.getAttributeNames())
		{
			attributeQuestions.add(new InputQuestion(attributeName, item.getAttribute(attributeName)));
		}

		setTitle(itemName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 208, 0 };
		gbl_contentPane.rowHeights = new int[] { 300, -66 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0 };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		panel.add(scrollPane);

		RowSpec[] rowSpec = new RowSpec[attributeQuestions.size()*2];
		
		for(int i = 0; i < attributeQuestions.size()*2; i+=2)
		{
			rowSpec[i] = FormFactory.RELATED_GAP_ROWSPEC;
			rowSpec[i+1] = FormFactory.DEFAULT_ROWSPEC;
		}
		
		JPanel scrollPaneContent = new JPanel();
		scrollPane.setViewportView(scrollPaneContent);
		scrollPaneContent.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, rowSpec));
		int i = 2;
		for(InputQuestion inputQuestion: attributeQuestions)
		{
			scrollPaneContent.add(inputQuestion, "2, " + i);
			i += 2;
		}

		JPanel buttonPane = new JPanel();
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.fill = GridBagConstraints.BOTH;
		gbc_buttonPane.gridx = 0;
		gbc_buttonPane.gridy = 1;
		contentPane.add(buttonPane, gbc_buttonPane);

		doneButton = new JButton("Done");
		doneButton.addMouseListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(this);
		
		addStepsButton = new JButton("Add Steps");
		addStepsButton.addMouseListener(this);

		buttonPane.add(doneButton);
		buttonPane.add(addStepsButton);
		buttonPane.add(cancelButton);

		setVisible(true);
	}

	@Override
	public void sendMessage(JFrame source, Object object)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(cancelButton))
		{
			dispose();
		}
		else if(e.getSource().equals(doneButton))
		{
			for(InputQuestion question: attributeQuestions)
			{
				item.setAttribute(question.getField(), question.getValue());
			}
			parentWindow.sendMessage(this, item);
			dispose();
		}
		else if(e.getSource().equals(addStepsButton))
		{
			
		}
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
