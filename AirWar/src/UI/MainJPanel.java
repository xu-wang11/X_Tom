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
	Set<AirWarObject> enermy = null;
	Set<AirWarObject> bullets = new HashSet<AirWarObject>();
	Hero hero = null;
	int level = -1;
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
		timer = new Timer(100, new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				updateUI();
			}
			
		});
	
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
				hero.moveLeft();
				break;
			case KeyEvent.VK_D:
				hero.moveRight();
				break;
			case KeyEvent.VK_W:
				hero.moveUp();
				break;
			case KeyEvent.VK_S:
				hero.moveDown();
				break;
			case KeyEvent.VK_K:
				bullets.add(new Plasma(this, new Point((int)Math.round(hero.rect.x+ hero.rect.width / 2 ), (int)Math.round(hero.rect.y))));
				break;
		}
	}
	public void paintComponent(Graphics g)  
	{  
	    super.paintComponent(g);    
	    g.drawImage(background,0,0,null);
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
	    this.updateUI();
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	} 
	
	
	
	

}
