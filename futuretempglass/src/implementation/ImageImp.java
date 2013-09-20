package implementation;

import java.awt.image.BufferedImage;

import framework.Graphics;
import framework.Image;

public class ImageImp implements Image{

	private BufferedImage image;
	
	public ImageImp(BufferedImage image)
	{
		this.image = image;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}

	@Override
	public int getWidth() {
		return getImage().getWidth();
	}

	@Override
	public int getHeight() {
		return getImage().getHeight();
	}

	@Override
	public Image subImage(int x, int y, int width, int height) {
		return new ImageImp(image.getSubimage(x, y, width, height));
	}

}
