package UI;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class Missile extends AirWarObject{

	//导弹跟踪对象
	private AirWarObject trackEnermy;

	public Missile(JFrame _frame, Rectangle _rectangle) {
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
	
	public void setTrackObject(AirWarObject _enermy)
	{
		this.trackEnermy = _enermy;
	}

}
