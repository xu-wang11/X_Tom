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
		this.direction.y = -1;
		this.speed = 3;
	}
	public void draw(Graphics2D g)
	{
		AffineTransform at = new AffineTransform();
		//at.rotate(rotation, (Math.round(rect.width) >> 1), (Math.round(rect.height) >> 1));
		at.translate(rect.x, rect.y);
		g.drawImage(im, at, null);
		if(target != null)
		{
			this.direction.x = (int) (target.rect.x + target.rect.width /2 - this.rect.x - this.rect.width/2);
			this.direction.y = (int)(target.rect.y + target.rect.height/2 - this.rect.y - this.rect.height/2);
			double len = Math.sqrt(this.direction.x * this.direction.x + this.direction.y * this.direction.y);
			if(len != 0 )
			{
				this.direction.x = (float) (this.direction.x / len);
				this.direction.y = (float) (this.direction.y / len);
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
