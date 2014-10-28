package UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import util.Rect;

//
public abstract class AirWarObject extends JLabel {
	private JFrame frame = null;
	ImageIcon background = new ImageIcon("plane.png");
	Image im = Toolkit.getDefaultToolkit().getImage("plane.png");
	public boolean willDisappear = false;
	public double speed;
	public Point2D direction;
	private Rectangle boundBox = new Rectangle();
	private Point2D startPoint;
	abstract public void updateLocation();
	abstract protected boolean isIntersect(AirWarObject obj);
	public Rect rect;
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(im, rect.x, rect.y, rect.width, rect.height, null);
	}
	public boolean judgeIntersection(AirWarObject obj)
	{
		
		if(this.boundBox.x +this.boundBox.width < obj.boundBox.x || this.boundBox.x > obj.boundBox.x + obj.boundBox.width ||
				this.boundBox.y + this.boundBox.height < obj.boundBox.y || this.boundBox.y > obj.boundBox.y + obj.boundBox.height)
			return false;
		
	
		return this.isIntersect(obj);
		
	}
	public AirWarObject(JFrame _frame, Point _rectangle, Rect imglocation) {
		this.frame = _frame;
		this.boundBox.x = _rectangle.x;
		this.boundBox.y = _rectangle.y;
		this.boundBox.width = imglocation.width;
		this.boundBox.height = imglocation.height;
		rect = imglocation;
		this.setBounds(this.boundBox.x, this.boundBox.y, this.boundBox.width, this.boundBox.height);
	}
	public boolean judgeWillDisappear()
	{
		Dimension d = frame.getSize();
		if(this.boundBox.x + this.boundBox.width < 0 || this.boundBox.y + this.boundBox.height < 0 || this.boundBox.x > d.width || this.boundBox.y > this.boundBox.height )
			return true;
		return false;
	}
	
	

}
