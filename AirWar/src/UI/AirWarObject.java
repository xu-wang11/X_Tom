package UI;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


import javax.swing.JPanel;

import util.Rect;
public abstract class AirWarObject{
	public Rect rect = new Rect();
	public BufferedImage im = null;
	public double rotation = 0;//旋转角度
	public int level = 0;
	public MainJPanel panel;
	public int speed = 0;
	public boolean isDisappear;//超出边界
	public AirWarObject(MainJPanel _panel, Point _point)
	{
		this.panel = _panel;
		rect.x = _point.x;
		rect.y = _point.y;
		
	}
	public void draw(Graphics2D g)
	{
		AffineTransform at = new AffineTransform();
		at.rotate(rotation, (Math.round(rect.width) >> 1), (Math.round(rect.height) >> 1));
		at.translate(rect.x, rect.y);
		g.drawImage(im, at, null);
		rect.x += Math.sin(rotation) * speed;
		rect.y += Math.cos(rotation) * speed;
		if(rect.x > panel.getWidth() || rect.x + rect.width < 0 || rect.y > panel.getHeight() || rect.y +rect.height < 0)
		{
			this.isDisappear = true;
		}
	}
	public void loadImage(String img)
	{
		try
		{
			this.im = ImageIO.read(new File(img));
			rect.width = im.getWidth();
			rect.height = im.getHeight();
		}
		catch(Exception e)
		{
			System.out.println("cannot load file:" + img);
		}
		
	}

}
