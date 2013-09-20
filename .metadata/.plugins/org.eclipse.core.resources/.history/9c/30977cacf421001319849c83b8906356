package implementation;

import important.Application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import framework.Graphics;
import framework.Screen;

public abstract class ScreenImp implements Screen{

	private boolean running;
	private boolean paused;
	
	@Override
	public void init() {
		running = true;
		paused = false;
	}

	@Override
	public void run() {
		try
		{
			init();
			gameLoop();
			cleanUp();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			File errorFile = new File("error.txt");
			FileOutputStream fos = null;
			try{
				if(!errorFile.exists())
					errorFile.createNewFile();
				fos = new FileOutputStream(errorFile);
				for(int i = 0; i < e.toString().length(); i++)
				{
					fos.write(e.toString().charAt(i));
				}
				fos.write(10);
				StackTraceElement[] stackTrace = e.getStackTrace();
				for(int i = 0; i < stackTrace.length; i++)
				{
					String message = stackTrace[i].toString();
					for(int k = 0; k < message.length(); k++)
						fos.write(message.charAt(k));
					fos.write(10);
				}
			}
			catch(Exception e2){}
			finally
			{
				try 
				{
					fos.close();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
	}

	private void gameLoop() {
		long startTime = System.nanoTime();
		long cumulative = startTime;
		long start = startTime;
		long MILLISECONDS = Application.MILLISECONDS_PER_FRAME*1000000;
		while(running)
		{
			long timePassed = System.nanoTime() - cumulative;
			cumulative += timePassed;
			/*
			if(timePassed > (Processes.MILLISECONDS_PER_FRAME+1)*1000000)
				MILLISECONDS--;
			else if(timePassed < (Processes.MILLISECONDS_PER_FRAME-1)*1000000)
				MILLISECONDS++;*/
			
			if(!paused)
				update(timePassed);
			
			Graphics g = Application.game.getGraphics();
			clear(g);
			draw(g);
			g.setColor(255,255,255);
			g.drawString(""+ ((timePassed == 0 ? "Too Fast" : 1000000000/timePassed)) +" FPS : Time Passed Per Frame: "+timePassed/1000000, Application.game.getScreenWidth()/2, 50);
			g.dispose();
			((GameImp) Application.game).getScreenManager().update();
			
			int waitTime = (int) (MILLISECONDS - (System.nanoTime()-start))/1000000;
			if(waitTime > 0)
			{
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			

			start = System.nanoTime();
		}
	}

	@Override
	public void cleanUp() {
		running = false;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public abstract void draw(Graphics g);
	
	public void clear(Graphics g)
	{
		g.setColor(0, 0, 0);
		g.fillRect(0, 0, Application.game.getScreenWidth(), Application.game.getScreenHeight());
	}

	protected abstract void update(long timePassed);
}
