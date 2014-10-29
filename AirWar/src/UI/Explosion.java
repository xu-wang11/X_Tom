package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

public class Explosion extends AirWarObject{
	public int life = 5;
	public Explosion(MainJPanel _panel, Point _point, int x) {
		super(_panel, _point);
		// TODO Auto-generated constructor stub
		getImage(x);
		this.rect.width = this.im.getWidth();
		this.rect.height = this.im.getHeight();
		this.rect.x -= this.rect.width/2;
		this.rect.y -= this.rect.height/2;
		this.speed = 0;
	}
	public void getImage(int x)
	{
		this.im = AirWarObject.imgs.get("exp2.png");
		BufferedImage target = new BufferedImage(64, 51, this.im.getType()); 
		double sx = 64 / this.im.getWidth();
		double sy = 64 / this.im.getHeight();
	        Graphics2D g = target.createGraphics();   
	        // smoother than exlax:   
	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
	                RenderingHints.VALUE_INTERPOLATION_BICUBIC);   
	        g.drawRenderedImage(this.im, AffineTransform.getScaleInstance(sx, sy));   
	        g.dispose();   
	        this.im = target;
		/**
		CropImageFilter filter = new CropImageFilter(x * 32, 0, 32, 32);
		Image img =  Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(this.im.getSource(), filter));
		BufferedImage tag = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);  
         Graphics g = tag.getGraphics();  
         g.drawImage(img, 0, 0, null); // ªÊ÷∆–°Õº  
         g.dispose();
         this.im = tag;*/
	}
}
