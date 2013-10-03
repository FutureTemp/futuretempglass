package ui.views;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class InputQuestion extends JComponent{
	private JTextField txtQuestion;
	private JTextField txtAnswer;

	public InputQuestion(String field, String value)
	{
		setLayout(new GridLayout(0, 2, 0, 0));

		txtQuestion = new JTextField();
		txtQuestion.setEditable(false);
		txtQuestion.setText(field);
		add(txtQuestion);
		txtQuestion.setColumns(10);

		txtAnswer = new JTextField();
		txtAnswer.setText(value);
		add(txtAnswer);
		txtAnswer.setColumns(10);
	}

}
