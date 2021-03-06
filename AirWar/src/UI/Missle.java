package UI;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Missle extends AirWarObject {
	AirWarObject target = null;
	public Missle(MainJPanel _panel, Point _p, AirWarObject obj)
	{
		super(_panel, _p);
		this.loadImage("missle.png");
		this.rect.x -= this.rect.width/2;
		this.rect.y -= this.rect.height - 10;
		target = obj;
		this.direction.x = 0;
		this.direction.y = -1;
		this.speed = 10;
	}
	public void draw(Graphics2D g)
	{
		AffineTransform at = new AffineTransform();
		//at.rotate(rotation, (Math.round(rect.width) >> 1), (Math.round(rect.height) >> 1));
		at.translate(rect.x, rect.y);
		g.drawImage(im, at, null);
		if(target != null && target.isDisappear == false)
		{
			double mx = target.rect.x + target.rect.width /2 - this.rect.x - this.rect.width/2;
			double my = target.rect.y + target.rect.height/2 - this.rect.y - this.rect.height/2;
			double len = Math.sqrt(mx*mx + my * my);
			if(len >= 0.1 )
			{
				
				this.direction.x = (float) (mx / len);
				this.direction.y = (float) (my / len);
				
			}
		}
		rect.x += this.direction.x * speed;
		rect.y += this.direction.y * speed;
		if(rect.x > panel.getWidth() || rect.x + rect.width < 0 || rect.y > panel.getHeight() || rect.y +rect.height < 0)
		{
			this.isDisappear = true;
		}
		
	}
	
}
