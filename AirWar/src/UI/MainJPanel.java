package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.XmlParse;

/**
 * 
 * @author Jing Kong
 * @since Oct 21, 2014
 */
public class MainJPanel extends JPanel implements KeyListener{
	//private JButton button;
	BufferedImage background = null;
	Timer timer;
	Set<AirWarObject> enermy = new HashSet<AirWarObject>();
	Set<AirWarObject> bullets = new HashSet<AirWarObject>();
	Set<AirWarObject> explosions = new HashSet<AirWarObject>();
	Hero hero = null;
	int level = -1;
	protected MainJPanel that = this;
	int keyDown = -1;//0, left, 1, right 2, up, 3 down, -1 not move.
	public MainJPanel(final JFrame frame)
	{
		try
		{
			background = ImageIO.read(new File("res/backdrop.png"));
		}
		catch(Exception e)
		{
			System.out.println("cannot load res/backdrop.png");
		}
		frame.setSize(360, 660);
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-360)/2, (screenSize.height-660)/2, 360, 660);
		this.setLayout(null);
		
		final JButton btn1 = new JButton("开始游戏");
		final JButton btn2 = new JButton("退出游戏");
		//ImageIcon icon = new ImageIcon(background);
		//JLabel bklabel = new JLabel(icon);
		//bklabel.setBounds(0, 0, 320, 480);
		//add(bklabel);
		btn1.setBounds(110, 260, 100, 30);
		btn2.setBounds(110, 300, 100, 30);
		btn1.addMouseListener(new MouseListener()
		{

			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton() == MouseEvent.BUTTON1)
				{
					level = 0;
					btn1.hide();
					btn2.hide();
					timer.start();
				}
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		btn2.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton() == MouseEvent.BUTTON1)
				{
					System.exit(0);
				}
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//this.add(bklabel);
		this.add(btn1);
		this.add(btn2);
		//this.updateUI();
		final Random r = new Random();
		timer = new Timer(10, new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				int select = r.nextInt(50);
				if(select == 1)
				{
					int width = that.getWidth();
					int x = r.nextInt(width);
					int y = r.nextInt(20);
					Mine mine = new Mine(that , new Point(x, y));
					enermy.add(mine);
				}
				select = r.nextInt(200);
				if(select == 1)
				{
					int width = that.getWidth();
					int x = r.nextInt(width);
					int y = r.nextInt(20);
					Destroyer mine = new Destroyer(that , new Point(x, y));
					enermy.add(mine);
				}
				switch(that.keyDown)
				{
				case 0:
					hero.moveLeft();
					break;
				case 1:
					hero.moveRight();
					break;
				case 2:
					hero.moveUp();
					break;
				case 3:
					hero.moveDown();
					break;
				}
				//check hero and enermy
				Iterator it = enermy.iterator();
				
				while(it.hasNext())
				{
					AirWarObject obj = (AirWarObject)it.next();
					if(obj.isIntersection(hero))
					{
						it.remove();
						explosions.add(new Explosion(that, new Point((int)(hero.rect.x + hero.rect.width / 2), (int)(hero.rect.y + hero.rect.height/ 2)),1));
					}
				}
				it = enermy.iterator();
				Iterator it1 = bullets.iterator();
				while(it.hasNext())
				{
					AirWarObject obj = (AirWarObject)it.next();
					while(it1.hasNext()){
						AirWarObject obj1 = (AirWarObject)it1.next();
						if(obj.isIntersection(obj1))
						{
							obj.blood --;
							it1.remove();
							if(obj.blood<=0)
							{
								explosions.add(new Explosion(that, new Point((int)(obj.rect.x + obj.rect.width / 2), (int)(obj.rect.y + obj.rect.height/ 2)),1));
								it.remove();
								break;
							}
						}
						
					}
					
				}
				updateUI();
			}
			
		});
		timer.setRepeats(true);
		
	
		this.addKeyListener(this);
		
		this.hero = new Hero(this, new Point(160, 400));
		this.setFocusable(true);
	}
	
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int i = arg0.getKeyCode();
		
		switch(i)
		{
			case KeyEvent.VK_A:
				this.keyDown = 0;
				break;
			case KeyEvent.VK_D:
				this.keyDown = 1;
				break;
			case KeyEvent.VK_W:
				this.keyDown = 2;
				break;
			case KeyEvent.VK_S:
				this.keyDown = 3;
				break;
			case KeyEvent.VK_J:
				bullets.add(new Plasma(this, new Point((int)Math.round(hero.rect.x+ hero.rect.width / 2 ), (int)Math.round(hero.rect.y))));
				break;
			case KeyEvent.VK_K:
				Iterator it = this.enermy.iterator();
				AirWarObject obj = null;
				while(it.hasNext())
				{
					AirWarObject ob = (AirWarObject)it.next();
					if(ob.rect.y + ob.rect.height < hero.rect.y)
					{
						if(obj == null)
						{
							obj = ob;
						}
						else
						{
							if(ob.rect.y > obj.rect.y)
							{
								obj = ob;
							}
						}
					}
				}
				bullets.add(new Missle(this, new Point((int)Math.round(hero.rect.x+ hero.rect.width / 2 ), (int)Math.round(hero.rect.y)),obj));
				break;
		}
	}
	public void paintComponent(Graphics g)  
	{  
	    super.paintComponent(g);    
	    //g.drawImage(background,0,0,null);
	    Graphics2D g2d = (Graphics2D)g;
	    if(level>=0)
	    {
	    	hero.draw(g2d);
	    }
	    
	    Iterator it = this.bullets.iterator();
	    while(it.hasNext())
	    {
	    	AirWarObject obj = (AirWarObject) it.next();
	    	obj.draw(g2d);
	    	if(obj.isDisappear)
	    	{
	    		it.remove();
	    	}
	    }
	    it = this.enermy.iterator();
	    while(it.hasNext())
	    {
	    	AirWarObject obj = (AirWarObject)it.next();
	    	obj.draw(g2d);
	    	if(obj.isDisappear)
	    	{
	    		it.remove();
	    	}
	    }
	    it = this.explosions.iterator();
	    while(it.hasNext())
	    {
	    	Explosion obj = (Explosion)it.next();
	    	obj.draw(g2d);
	    	obj.life --;
	    	if(obj.life == 0)
	    	{
	    		it.remove();
	    	}
	    }
	    //explosions.clear();
	    //this.updateUI();
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A ||e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_W)
		{
			this.keyDown = -1;
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	} 
	
	
	
	

}
