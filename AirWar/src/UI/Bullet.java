package UI;

import javax.imageio.ImageIO;

import java.awt.Point;
import java.io.File;
public class Bullet extends AirWarObject{
	public Bullet(MainJPanel _panel, Point _p)
	{
		super(_panel, _p);
		this.loadImage("res/bullet.png");
	}
}
