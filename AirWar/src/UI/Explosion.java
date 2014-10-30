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
	public int life = 12;
	public Explosion(MainJPanel _panel, Point _point) {
		super(_panel, _point);
		// TODO Auto-generated constructor stub
		getImage();
		this.rect.width = this.im.getWidth();
		this.rect.height = this.im.getHeight();
		this.rect.x -= this.rect.width/2;
		this.rect.y -= this.rect.height/2;
		this.speed = 0;
	}
	public void getImage()
	{
		this.im = AirWarObject.imgs.get("explosion_anim.png");
		CropImageFilter filter = new CropImageFilter((12 - life) * 134, 0, 134, 134);
		Image img =  Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(this.im.getSource(), filter));
		BufferedImage tag = new BufferedImage(134, 134, BufferedImage.TYPE_4BYTE_ABGR);  
         Graphics g1 = tag.getGraphics();  
         g1.drawImage(img, 0, 0, null); // 绘制小图  
         g1.dispose();
         this.im = tag;
		
         //this.im = tag;
	}
	public void draw(Graphics2D g)
	{
		AffineTransform at = new AffineTransform();
		//at.rotate(rotation, (Math.round(rect.width) >> 1), (Math.round(rect.height) >> 1));
		at.translate(rect.x, rect.y);
		//System.out.println(rect.x);
		//System.out.println(rect.y);
		if(life > 0)
		{
			this.im = AirWarObject.imgs.get("explosion_anim.png");
			System.out.println(im.getWidth());
			CropImageFilter filter = new CropImageFilter((12 - life) * 134, 0, 134, 134);
			Image img =  Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(this.im.getSource(), filter));
			BufferedImage tag = new BufferedImage(134, 134, BufferedImage.TYPE_4BYTE_ABGR);  
	         Graphics g1 = tag.getGraphics();  
	         g1.drawImage(img, 0, 0, null); // 绘制小图  
	         g1.dispose();
	        
			
			this.im = tag;
			g.drawImage(this.im, at, null);
			
		}
	}
}
