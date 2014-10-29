package UI;

import java.awt.Point;

public class Plasma extends AirWarObject{
	
	public Plasma(MainJPanel _panel, Point _p)
	{
		super(_panel, _p);
		this.loadImage("plasma.png");
		this.rect.x -= ((int)this.rect.width>>1);
		this.rect.y -= this.rect.height;
		this.speed = 10;
		this.direction.y = -1;
	}
	

}
