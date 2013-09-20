package implementation;


import java.awt.DisplayMode;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import framework.Image;
import framework.Resources;

public class ResourcesImp implements Resources{

	final public static DisplayMode modes[] = {
		new DisplayMode(1920, 1080, 32, 0),
		new DisplayMode(1920, 1080, 24, 0),
		new DisplayMode(1920, 1080, 16, 0),
		new DisplayMode(1680,1050,32,0),
		new DisplayMode(1680,1050,24,0),
		new DisplayMode(1680,1050,16,0),
		new DisplayMode(1600,900,32,0),
		new DisplayMode(1600,900,24,0),
		new DisplayMode(1600,900,16,0),
		new DisplayMode(1366,768,32,0),
		new DisplayMode(1366,768,24,0),
		new DisplayMode(1366,768,16,0),
		new DisplayMode(1360,768,32,0),
		new DisplayMode(1360,768,24,0),
		new DisplayMode(1360,768,16,0),
		new DisplayMode(1280,800,32,0),
		new DisplayMode(1280,800,24,0),
		new DisplayMode(1280,800,16,0),
		new DisplayMode(1280,768,32,0),
		new DisplayMode(1280,768,24,0),
		new DisplayMode(1280,768,16,0),
		new DisplayMode(1280,720,32,0),
		new DisplayMode(1280,720,24,0),
		new DisplayMode(1280,720,16,0),
		new DisplayMode(1024,768,32,0),
		new DisplayMode(1024,768,24,0),
		new DisplayMode(1024,768,16,0),
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0),
	};

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage(String path) {
		URL url = getClass().getResource(path);
		try {
			return new ImageImp(ImageIO.read(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
