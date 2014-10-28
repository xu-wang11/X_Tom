package UI;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JLabel;

import util.Rect;

public class Bullet extends AirWarObject{

	public Bullet(JFrame frame, Point rectangle, Rect rect) {
		// TODO Auto-generated constructor stub
		super(frame, rectangle, rect);
	}

	@Override
	public void updateLocation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isIntersect(AirWarObject obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
