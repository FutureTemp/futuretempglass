package framework;

import java.io.Serializable;
import java.util.HashMap;



public class Sprite implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double x, y, vx, vy;
	private long lastCheck;
	private String animationType;
	transient private HashMap<String, Animation> animation;
	private char direction;
	protected double speed;
	protected int damage;
	protected boolean knockback;
	
	public Sprite()
	{
		x = y = vx = vy = 0;
		animationType = null;
		lastCheck = System.currentTimeMillis();
		animationType = null;
		animation = new HashMap<String, Animation>();
		direction = 'N';
		damage = 0;
	}
	
	public void reConstruct()
	{
		animation = new HashMap<String, Animation>();
	}
	
	//change position
	public void update()
	{
		int timePassed = (int)(System.currentTimeMillis() - lastCheck);
		lastCheck = System.currentTimeMillis();
		x += vx*timePassed;
		y += vy*timePassed;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)getX(), (int)getY(), getWidth(), getHeight());
	}
	
	public void addAnimation(String name, Animation a)
	{
		animation.put(name, a);
	}
	
	public void setAnimationType(String animationType)
	{
		this.animationType = animationType;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public synchronized void setX(double x)
	{
		this.x = x;
	}
	
	public synchronized void setY(double y)
	{
		this.y = y;
	}
	
	public synchronized void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public int getCenterX()
	{
		return (int) (this.x + getWidth()/2.0);
	}
	
	public int getCenterY()
	{
		return (int) (this.y + getHeight()/2.0);
	}
	
	public int getWidth()
	{
		return animation.get(animationType).getCurrentImage().getWidth();
	}
	
	public int getHeight()
	{
		return animation.get(animationType).getCurrentImage().getHeight();
	}
	
	public double getVelX()
	{
		return vx;
	}
	
	public double getVelY()
	{
		return vy;
	}
	
	public void setVelX(double vx)
	{
		this.vx = vx;
		if(this.vx == 0 && this.vy == 0)
			stopAnimation();
		else
			startAnimation();
	}
	
	public void setVelY(double vy)
	{
		this.vy = vy;
		if(this.vx == 0 && this.vy == 0)
			stopAnimation();
		else
			startAnimation();
	}
	
	public void setDirection(char direction)
	{
		this.direction = direction;
	}
	
	public char getDirection()
	{
		return direction;
	}
	
	public void moveUp()
	{
		setVelY(-speed);
		setVelX(0);
		setAnimationType("up");
		setDirection('U');
	}
	
	public void moveDown()
	{
		setVelY(speed);
		setVelX(0);
		setAnimationType("down");
		setDirection('D');
	}
	
	public void moveLeft()
	{
		setVelX(-speed);
		setVelY(0);
		setAnimationType("left");
		setDirection('L');
	}
	
	public void moveRight()
	{
		setVelX(speed);
		setVelY(0);
		setAnimationType("right");
		setDirection('R');
	}
	
	public void stopMovement()
	{
		setVelX(0);
		setVelY(0);
		stopAnimation();
		restartAnimation();
		setDirection('N');
	}
	
	public void restartAnimation()
	{
		animation.get(animationType).restartAnimation();
	}
	
	public void stopAnimation()
	{
		animation.get(animationType).stopAnimation();
	}
	
	public void startAnimation()
	{
		animation.get(animationType).startAnimation();
	}
	
	public boolean pointOnSprite(Point p)
	{
		int x = (int) p.getX();
		int y = (int) p.getY();
		if(x > this.x && x < this.x + getWidth() && y > this.y && y < this.y + getHeight())
			return true;
		return false;
	}
	
	public boolean isTouching(Sprite s)
	{
		if(this.x > s.getX())
		{
			if(this.y > s.getY())
			{
				if(this.x - s.getX() < s.getWidth() && this.y - s.getY() < s.getHeight())
					return true;
			}
			else if(this.x - s.getX() < s.getWidth() && s.getY() - this.y < this.getHeight())
				return true;
		}
		else if(this.y > s.getY())
		{
			if(s.getX() - this.x < this.getWidth() && this.y - s.getY() < s.getHeight())
				return true;
		}
		else if(s.getX() - this.x < this.getWidth() && s.getY() - this.y < this.getHeight())
			return true;
		return false;
	}
	
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	public Image getImage()
	{
		update();
		return animation.get(animationType).getCurrentImage();
	}
	
	public void update(long timePassed)
	{
		animation.get(animationType).update(timePassed);
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(getImage(), (int)getX(), (int)getY());
	}
}
