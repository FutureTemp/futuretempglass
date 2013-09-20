package implementation;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import screens.levels.FirstLevel;
import framework.Game;
import framework.Graphics;
import framework.Resources;
import framework.Screen;

public class GameImp implements Game, KeyListener{
	
	private Screen currentScreen;
	private Resources resources;
	private ScreenManager screenManager;

	
	@Override
	public void startGame(Screen startScreen) {
		resources = new ResourcesImp();
		resources.init();
		screenManager = new ScreenManager();
		DisplayMode dm = screenManager.findFirstCompatibleMode(ResourcesImp.modes);
		screenManager.setFullScreen(dm);
		
		Window w = screenManager.getFullScreenWindow();
		w.addKeyListener(this);
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.BLACK);
		w.setForeground(Color.WHITE);
		
		goToScreen(startScreen);
	}

	@Override
	public void stopGame() {
		screenManager.getFullScreenWindow().removeKeyListener(this);
		currentScreen.cleanUp();
		screenManager.restoreScreen();
	}

	@Override
	public void pauseGame() {
		currentScreen.pause();
	}
	
	@Override
	public void resumeGame() {
		currentScreen.resume();
	}

	@Override
	public void goToScreen(Screen screen) {
		if(currentScreen != null)
			currentScreen.cleanUp();
		currentScreen = screen;
		currentScreen.run();
	}

	@Override
	public Screen getCurrentScreen() {
		return currentScreen;
	}

	@Override
	public Resources getResources() {
		return resources;
	}

	@Override
	public Graphics getGraphics() {
		return new GraphicsImp(screenManager.getGraphics());
	}

	@Override
	public int getScreenWidth() {
		return screenManager.getWidth();
	}

	@Override
	public int getScreenHeight() {
		return screenManager.getHeight();
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_ESCAPE:
			stopGame();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
