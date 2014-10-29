package UI;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.imageio.ImageIO;


import javax.swing.JPanel;

import util.Point2F;
import util.Rect;
public abstract class AirWarObject{
	public Rect rect = new Rect();
	public BufferedImage im = null;
	public double rotation = 0;//旋转角度
	public Point2F direction = new Point2F();
	public int level = 0;
	public MainJPanel panel;
	public float speed = 0;
	public boolean isDisappear;//超出边界
	int blood = 1;
	public static Dictionary<String, BufferedImage> imgs = null;
	public AirWarObject(MainJPanel _panel, Point _point)
	{
		this.panel = _panel;
		rect.x = _point.x;
		rect.y = _point.y;
		direction.x = (float)0.0;
		direction.y = (float)1.0;
		
	}
	public void draw(Graphics2D g)
	{
		AffineTransform at = new AffineTransform();
		//at.rotate(rotation, (Math.round(rect.width) >> 1), (Math.round(rect.height) >> 1));
		at.translate(rect.x, rect.y);
		g.drawImage(im, at, null);
		rect.x += this.direction.x * speed;
		rect.y += this.direction.y * speed;
		if(rect.x > panel.getWidth() || rect.x + rect.width < 0 || rect.y > panel.getHeight() || rect.y +rect.height < 0)
		{
			this.isDisappear = true;
		}
	}
	public void loadImage(String img)
	{
		try
		{
			this.im = AirWarObject.imgs.get(img);
			rect.width = im.getWidth();
			rect.height = im.getHeight();
			
		}
		catch(Exception e)
		{
			System.out.println("cannot load file:" + img);
		}
		
	}
	boolean isIntersection(AirWarObject obj)
	{
		if(this.rect.x > obj.rect.x + obj.rect.width || this.rect.x + this.rect.width < obj.rect.x ||
				this.rect.y > obj.rect.y + obj.rect.height || this.rect.y + this.rect.height < obj.rect.y)
		{
			return false;
		}
		return true;
		
	}
	void exploid(Graphics2D g)
	{
		
	}
	public static void loadResource()
	{
		AirWarObject.imgs = new Hashtable<String, BufferedImage>();
		String[] imgs = {"bullet.png", "destroyer.png", "exp2.png", "mine.png", "missle.png", "oppressor.png", "plasma.png", "ship.png"};
		for(int i = 0; i < imgs.length; i ++)
		{
			String img = imgs[i];
			try
			{
				BufferedImage im1 = ImageIO.read(new File("res/" + img));
				
				for(int k = 0; k < im1.getWidth(); k ++)
				{
					for(int j = 0; j < im1.getHeight(); j ++)
					{
						 int rgb = im1.getRGB(k, j);
						 int R = (rgb & 0xff0000) >> 16;  
					     int G = (rgb & 0xff00) >> 8;  
					     int B = (rgb & 0xff);  
						 if(R==0&& G==0&& B==0) 
						 { 
							 rgb =  0x00ffffff;  
		                     im1.setRGB(k, j, rgb);
						 }
						 
					}
				}
				AirWarObject.imgs.put(img, im1);
			}
			catch(Exception e)
			{
				System.out.println("cannot load file:" + img);
			}
		}
	}

}
