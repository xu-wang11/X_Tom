package UI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JLabel;

//这是游戏元素的基类，每一个元素继承JLabel对象。
//
public abstract class AirWarObject extends JLabel {
	private JFrame frame = null;
	//物体是否要消失
	public boolean willDisappear = false;
	public double speed;
	public Point2D direction;
	private Rectangle boundBox = new Rectangle();
	abstract public void updateLocation();
	abstract protected boolean isIntersect(AirWarObject obj);
	//判断两个元素是否相交
	public boolean judgeIntersection(AirWarObject obj)
	{
		//首先判断包围盒是否相交
		if(this.boundBox.x +this.boundBox.width < obj.boundBox.x || this.boundBox.x > obj.boundBox.x + obj.boundBox.width ||
				this.boundBox.y + this.boundBox.height < obj.boundBox.y || this.boundBox.y > obj.boundBox.y + obj.boundBox.height)
			return false;
		
		//如果包围盒相交，更精确地判断两个物体是否相交
		return this.isIntersect(obj);
		
	}
	public AirWarObject(JFrame _frame, Rectangle _rectangle) {
		this.frame = _frame;
		this.boundBox.x = _rectangle.x;
		this.boundBox.y = _rectangle.y;
		this.boundBox.width = _rectangle.width;
		this.boundBox.height = _rectangle.height;
	}
	public boolean judgeWillDisappear()
	{
		Dimension d = frame.getSize();
		if(this.boundBox.x + this.boundBox.width < 0 || this.boundBox.y + this.boundBox.height < 0 || this.boundBox.x > d.width || this.boundBox.y > this.boundBox.height )
			return true;
		return false;
	}

}
