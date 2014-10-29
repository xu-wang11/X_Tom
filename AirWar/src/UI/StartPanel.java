package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.XmlParse;

/**
 * 
 * @author Jing Kong
 * @since Oct 21, 2014
 */
public class StartPanel extends JPanel {
	//private JButton button;
	ImageIcon background = new ImageIcon("bg_02.jpg");
	Image im = Toolkit.getDefaultToolkit().getImage("bg_02.jpg");
	private Set<AirWarObject> enermies;
	private Set<AirWarObject> bullets;
	private Set<AirWarObject> missiles;
	private Hero hero;
	private XmlParse parse;

	private int level;
	private int score;
	public StartPanel(JFrame frame, XmlParse _parse)
	{
		super();
		this.setSize(480, 800);
		//this.setBackground(Color.black);
		this.parse = _parse;
		this.setLayout(null);
		//this.setSize(400, 600);
		frame.setSize(480, 800);
		Point rect1 = new Point();
		rect1.x = 100;
		rect1.y = 100;
		
		Hero hero = new Hero(frame, rect1, this.parse.rects.get("hero_1"));
		hero.setBackground(Color.black);
		hero.setBounds(0, 0, 100, 100);
		this.add(hero);
		JLabel label = new JLabel("hhe");
		label.setBackground(Color.black);
		label.setBounds(0, 0, 100, 100);
		//hero.setOpaque(false);
	
		//this.add(label);
		//JButton button = new JButton("hehe");
		
		//frame.setResizable(false);
		//button = new JButton("æ¸¸æˆ�å¼€å§‹");
		//button.setBounds(150,100, 100, 40);
		//this.add(button);
		//this.add
		
	
		
	}

	
	
	public void update()
	{
	
		Iterator it = enermies.iterator();
		while(it.hasNext())
		{
			AirWarObject obj = (AirWarObject) it.next();
			obj.updateLocation();
		}
		it = bullets.iterator();
		while(it.hasNext())
		{
			AirWarObject obj = (AirWarObject)it.next();
			obj.updateLocation();
		}
		it = missiles.iterator();
		while(it.hasNext())
		{
			AirWarObject obj = (AirWarObject)it.next();
			obj.updateLocation();
		}
		hero.updateLocation();
	
		it = enermies.iterator();
		while(it.hasNext())
		{
			AirWarObject obj = (AirWarObject)it.next();
			if(obj.judgeIntersection(hero))
			{
				this.gameEnd();
				return;
			}
		}
	
		Iterator it1 = enermies.iterator();
		
		while(it1.hasNext())
		{
			AirWarObject enermy = (AirWarObject) it1.next();
			Iterator it2 = bullets.iterator();
			while(it2.hasNext())
			{
				AirWarObject bullet = (AirWarObject)it2.next();
				enermy.judgeIntersection(bullet);
			}
		}
		
		
		it1 = enermies.iterator();
		
		while(it1.hasNext())
		{
			AirWarObject enermy = (AirWarObject) it1.next();
			Iterator it2 = bullets.iterator();
			while(it2.hasNext())
			{
				AirWarObject bullet = (AirWarObject)it2.next();
				enermy.judgeIntersection(bullet);
			}
		}
		
	
		
	}
	
	//
	public void gameEnd()
	{
		
	}
	
	public void gameStart()
	{
		
	}
	
	public void gameUpgrade()
	{
		
	}

}
