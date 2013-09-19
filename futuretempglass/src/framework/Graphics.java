package framework;

public interface Graphics {

	public void drawImage(Image image, int x, int y);
	
	public void drawString(String string, int x, int y);
	
	public void drawRect(int x, int y, int width, int height);
	
	public void fillRect(int x, int y, int width, int height);

	public void setColor(int r, int g, int b, int a);
	
	public void setColor(int r, int g, int b);
	
	public void dispose();
}
