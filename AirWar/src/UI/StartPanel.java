package UI;

import java.awt.FlowLayout;

import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jing Kong
 * @since Oct 21, 2014
 */
public class StartPanel extends JPanel {
	//private JButton button;
	private Set<AirWarObject> enermies;
	private Set<AirWarObject> bullets;
	private Set<AirWarObject> missiles;
	private Hero hero;
	//当前等级
	private int level;
	private int score;
	public StartPanel(JFrame frame)
	{
		this.setLayout(null);
		//this.setSize(400, 600);
		frame.setSize(400, 800);
		//frame.setResizable(false);
		//button = new JButton("游戏开始");
		//button.setBounds(150,100, 100, 40);
		//this.add(button);
		//this.add
		
	
		
	}
	
	public void update()
	{
		//更新所有元素的位置
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
		//检查英雄是否和敌人相交
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
		//判断子弹是否和敌人相撞
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
		
		//判断导弹是否与敌人相交
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
		
		//移除所有的爆炸对象，并且添加爆炸效果
		
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
