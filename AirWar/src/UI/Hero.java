package UI;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

import util.Rect;

public class Hero extends AirWarObject{

	
	public Hero(JFrame _frame, Point _rectangle, Rect rect) {
		super(_frame, _rectangle, rect);
		// TODO Auto-generated constructor stub
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
