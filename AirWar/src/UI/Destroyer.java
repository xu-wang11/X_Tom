package UI;

import javax.imageio.ImageIO;

import java.awt.Point;
import java.io.File;
public class Destroyer extends AirWarObject {
	public Destroyer(MainJPanel _panel, Point _p)
	{
		super(_panel, _p);
		this.loadImage("res/destroyer.png");
	}

}
