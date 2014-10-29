package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hero extends AirWarObject {
	int level = 0;
	int step = 4;
	
	public Hero(MainJPanel _panel, Point _p)
	{
		super(_panel, _p);
		this.getImage();
		this.rect.width = 24;
		this.rect.height = 24;
	}
	public void draw(Graphics2D g)
	{
		AffineTransform at = new AffineTransform();
		at.rotate(rotation, ((int)rect.width >> 1), ((int)rect.height >> 1));
		at.translate((int)rect.x, (int)rect.y);
		
		g.drawImage(im, at, 
				null);
		
	}
	public void moveLeft()
	{
		if(this.rect.x > 0)
			this.rect.x -= step;
	}
	public void moveRight()
	{
		if(this.rect.x + this.rect.width < this.panel.getWidth())
		{
			this.rect.x += step;
		}
		
	}
	public void moveUp()
	{
		if(this.rect.y > 0)
			this.rect.y -= step;
	}
	public void moveDown()
	{
		if(this.rect.y + this.rect.height < this.panel.getHeight())
		{
			this.rect.y += step;
		}
	}
	public void getImage()
	{
		this.loadImage("res/ship.png");
		CropImageFilter filter = new CropImageFilter(level* 24, 0, 24, 24);
		Image img =  Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(this.im.getSource(), filter));
		BufferedImage tag = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);  
         Graphics g = tag.getGraphics();  
         g.drawImage(img, 0, 0, null); // 绘制小图  
         g.dispose();
         this.im = tag;
	}

}
