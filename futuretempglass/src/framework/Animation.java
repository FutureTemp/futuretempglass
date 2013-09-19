package framework;
import java.util.ArrayList;


public class Animation {

	private ArrayList<Image> scenes;
	private ArrayList<Integer> length;
	private int currentScene;
	private boolean stop;
	private boolean repeat;
	
	public Animation()
	{
		scenes = new ArrayList<Image>();
		length = new ArrayList<Integer>();
		currentScene = 0;
		stop = true;
		repeat = true;
	}
	
	public void addScene(Image i, int duration)
	{
		scenes.add(i);
		length.add(duration);
	}
	
	public Image getCurrentImage()
	{
		try{
			return scenes.get(currentScene);
		}
		catch(Exception e)
		{
			return scenes.get(0);
		}
	}
	
	public void restartAnimation()
	{
		currentScene = 0;
	}
	
	public void stopAnimation()
	{
		stop = true;
	}
	
	public void startAnimation()
	{
		if(stop)
		{
			currentScene = 0;
		}
		stop = false;
	}
	
	public void setRepeating(boolean repeat)
	{
		this.repeat = repeat;
	}
	
	public void update(long timePassed)
	{
		if(stop)
			return;
		
		try
		{
			while(timePassed > length.get(currentScene))
			{
				timePassed -= length.get(currentScene);
				currentScene++;
				if(currentScene >= scenes.size())
				{
					if(!repeat)
						stopAnimation();
					else
						currentScene = 0;
				}
			}
		}
		catch(Exception e)
		{
			if(!repeat)
				currentScene = scenes.size()-1;
			else
				currentScene = 0;
		}
	}
	
}
