package ui.views;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import orders.Order;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class NewOrderWindow extends JFrame{

	/**
	 * Create the frame.
	 */
	public NewOrderWindow(Order order)
	{
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(98dlu;default)"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		InputQuestion inputQuestion_9 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_9, "2, 2");
		
		InputQuestion inputQuestion_8 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_8, "2, 4");
		
		InputQuestion inputQuestion_7 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_7, "2, 6");
		
		InputQuestion inputQuestion_6 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_6, "2, 8");
		
		InputQuestion inputQuestion_5 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_5, "2, 10");
		
		InputQuestion inputQuestion_4 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_4, "2, 12");
		
		InputQuestion inputQuestion_3 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_3, "2, 14");
		
		InputQuestion inputQuestion_2 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_2, "2, 16");
		
		InputQuestion inputQuestion_1 = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion_1, "2, 18");
		
		InputQuestion inputQuestion = new InputQuestion((String) null, (String) null);
		getContentPane().add(inputQuestion, "2, 20");

		setVisible(true);
	}

}
