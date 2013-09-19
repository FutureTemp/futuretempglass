package framework;

public interface Screen{

	public void init();
	
	public void run();
	
	public void cleanUp();
	
	public void pause();
	
	public void resume();
	
	public void clear(Graphics g);
}
