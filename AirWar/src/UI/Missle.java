package UI;

import java.awt.Point;

public class Missle extends AirWarObject {
	public Missle(MainJPanel _panel, Point _p)
	{
		super(_panel, _p);
		this.loadImage("res/missle.png");
	}
}
