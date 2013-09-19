package framework;

public interface Image {

	public int getWidth();
	
	public int getHeight();
	
	public Image subImage(int x, int y, int width, int height);
}
