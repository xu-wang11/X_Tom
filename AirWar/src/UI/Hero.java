package UI;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Hero extends AirWarObject{

	
	public Hero(JFrame _frame, Rectangle _rectangle) {
		super(_frame, _rectangle);
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
