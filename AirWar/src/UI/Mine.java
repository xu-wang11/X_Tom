package UI;

import java.awt.Point;

public class Mine extends AirWarObject{
	public Mine(MainJPanel _panel, Point _p)
	{
		super(_panel, _p);
		this.loadImage("res/mine.png");
	}

}
