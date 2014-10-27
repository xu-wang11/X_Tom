package UI;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Bullet extends AirWarObject{

	public Bullet(JFrame frame, Rectangle rectangle) {
		// TODO Auto-generated constructor stub
		super(frame, rectangle);
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
