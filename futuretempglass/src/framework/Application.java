package framework;

public interface Application {
	
	public void startApplication(Screen startScreen);
	
	public void stopApplication();
	
	public void pause();
	
	public void resume();
	
	public void goToScreen(Screen screen);
	
	public Screen getCurrentScreen();
	
	public Graphics getGraphics();
	
	public Resources getResources();
	
	public int getScreenWidth();
	
	public int getScreenHeight();
	
}
