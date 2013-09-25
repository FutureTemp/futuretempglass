package ui.views;

import java.awt.event.KeyEvent;

import ui.elements.Button;

import framework.Application;
import framework.Graphics;
import implementation.ScreenImp;

public class NewOrderScreen extends ScreenImp{

	private Button newOrderButton = new Button("New Order");
	
	public NewOrderScreen(Application application)
	{
		super(application);
	}
	
	@Override
	public void init()
	{
		super.init();
		newOrderButton.setPosition(100, 100);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g)
	{
		newOrderButton.draw(g);
	}

	@Override
	protected void update(long timePassed)
	{
		// TODO Auto-generated method stub
		
	}

}
