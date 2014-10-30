package UI;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Oppressor extends AirWarObject {

	public Oppressor(MainJPanel _panel, Point _p)
	{
		super(_panel, _p);
		this.loadImage("oppressor.png");
		this.direction.x = 1;
		this.direction.y = 0;
		this.blood = 20;
		this.speed = 1;
	}
	
	public List<Mine> emit()
	{
		List<Mine> result = new ArrayList<Mine>();
		Mine mine = new Mine(this.panel, new Point((int)(this.rect.x + this.rect.width / 2), (int)(this.rect.y + this.rect.height)));
		result.add(mine);
		int num = 2* (this.panel.level + 1);
		double angle = 20 / (this.panel.level + 1);
		for(int i = 0; i < num; i ++)
		{
			Mine mine1= new Mine(this.panel, new Point((int)(this.rect.x + this.rect.width / 2), (int)(this.rect.y + this.rect.height)));
			mine1.direction.x = (float) Math.sin(Math.PI / 180 * angle* (i + 1));
			mine1.direction.y = (float) Math.cos(Math.PI / 180 * angle* (i + 1));
			result.add(mine1);
			Mine mine2= new Mine(this.panel, new Point((int)(this.rect.x + this.rect.width / 2), (int)(this.rect.y + this.rect.height)));
			mine2.direction.x = -(float) Math.sin(Math.PI / 180 * angle* (i + 1));
			mine2.direction.y = (float) Math.cos(Math.PI / 180 * angle* (i + 1));
			
			result.add(mine2);
		}
		return result;
	}
	
	public void draw(Graphics2D g)
	{
		AffineTransform at = new AffineTransform();
		//at.rotate(rotation, (Math.round(rect.width) >> 1), (Math.round(rect.height) >> 1));
		at.translate(rect.x, rect.y);
		g.drawImage(im, at, null);
		rect.x += this.direction.x * speed;
		rect.y += this.direction.y * speed;
		if(rect.x  + rect.width> panel.getWidth() || rect.x < 0 )
		{
			this.direction.x = -this.direction.x;
		}
		
	}

}
