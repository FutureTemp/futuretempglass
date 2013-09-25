package ui.elements;

import implementation.GraphicsImp;

import java.awt.Font;
import java.awt.FontMetrics;

import framework.Graphics;
import framework.Sprite;

public class Button extends Sprite{

	private static final Font font = new Font("Calibri", Font.PLAIN, 20);

	private static final int VERTICAL_SPACE = 10;

	private static final int HORIZONTAL_SPACE = 10;

	private String label;

	public Button(String label)
	{
		this.label = label;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(0, 0, 0);
		FontMetrics fm = ((GraphicsImp)g).getGraphics2D().getFontMetrics(font);
		int height = fm.getHeight();
		int width = fm.stringWidth(label);
		g.drawRect((int)getX(), (int)getY(), width + 2 * HORIZONTAL_SPACE,
				height + 2 * VERTICAL_SPACE);
		g.drawString(label, (int)getX() + HORIZONTAL_SPACE / 2, (int)getY()
				+ height + VERTICAL_SPACE / 2);
	}

}
