package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import util.Rect;

public class Plasma extends AirWarObject{
	
	public Plasma(MainJPanel _panel, Point _p)
	{
		super(_panel, _p);
		this.loadImage("res/plasma.png");
		this.rect.x -= ((int)this.rect.width>>1);
		this.speed = 5;
	}
	

}
