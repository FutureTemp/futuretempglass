package ui.views;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class Window extends JFrame implements MouseListener{
	
	private Window parentWindow;
	
	public Window(Window parentWindow)
	{
		this.parentWindow = parentWindow;
	}
	
	public Window getParent()
	{
		return parentWindow;
	}
	
	public void sendData(JFrame source, Object object)
	{
		
	}
	
	public void refresh()
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}
