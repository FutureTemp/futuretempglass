package ui.components;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class InputQuestion extends JComponent{
	
	private QuestionTypes type;
	
	private JTextField txtQuestion;
	private JComponent txtAnswer;

	public InputQuestion(String field, Object value)
	{
		this(QuestionTypes.INPUT, field, value);
	}
	
	public InputQuestion(QuestionTypes type, String field, Object value)
	{
		setLayout(new GridLayout(0, 2, 0, 0));

		this.type = type;
		switch(type)
		{
		case INPUT:
			txtQuestion = new JTextField();
			txtQuestion.setEditable(false);
			txtQuestion.setText(field);
			add(txtQuestion);
			txtQuestion.setColumns(10);

			txtAnswer = new JTextField();
			((JTextField)txtAnswer).setText((String)value);
			add(txtAnswer);
			((JTextField)txtAnswer).setColumns(10);
			break;
		default:
			break;
		}
	}
	
	public String getField()
	{
		return txtQuestion.getText();
	}
	
	public Object getValue()
	{
		switch(type)
		{
		case INPUT:
			return ((JTextComponent)txtAnswer).getText();
		}
		return null;
	}
	
	public enum QuestionTypes
	{
		INPUT,
		NUM,
		BOOLEAN,
		CHOICE
	}

}
