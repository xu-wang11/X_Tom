package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Jing Kong
 * @since Oct 21, 2014
 */
public class MainJPanel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private JButton button;
	BufferedImage background = null;
	Timer timer;
	Set<AirWarObject> enermy = new HashSet<AirWarObject>();
	Set<AirWarObject> bullets = new HashSet<AirWarObject>();
	Set<AirWarObject> explosions = new HashSet<AirWarObject>();
	Hero hero = null;
	int level = -1;
	protected MainJPanel that = this;
	int timerNumber = 0;
	Oppressor oppessor = null;
	public int score = 0;
	int keyDown = -1;// 0, left, 1, right 2, up, 3 down, -1 not move.
	public Clip backgroundmusic = null;

	public MainJPanel(final JFrame frame) {
		try {
			background = ImageIO.read(new File("res/backdrop.png"));
		} catch (Exception e) {
			System.out.println("cannot load res/backdrop.png");
		}
		frame.setSize(360, 660);
		// initial dialog location
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		frame.setBounds((screenSize.width - 360) / 2,
				(screenSize.height - 660) / 2, 360, 660);
		// absolute layout
		this.setLayout(null);

		final JButton btn1 = new JButton("开始游戏");
		final JButton btn2 = new JButton("退出游戏");
		// ImageIcon icon = new ImageIcon(background);
		// JLabel bklabel = new JLabel(icon);
		// bklabel.setBounds(0, 0, 320, 480);
		// add(bklabel);
		btn1.setBounds(110, 260, 100, 30);
		btn2.setBounds(110, 300, 100, 30);

		btn1.addMouseListener(new MouseAdapter() {

			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					level = 0;
					btn1.hide();
					btn2.hide();
					timer.start();
				}
			}
		});
		btn2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					System.exit(0);
				}
			}
		});
		// this.add(bklabel);
		this.add(btn1);
		this.add(btn2);
		// this.updateUI();
		final Random r = new Random();
		timer = new Timer(10, new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				that.timerNumber++;
				// not boss
				if (that.timerNumber < 1000) {
					int select = r.nextInt(50);
					if (select == 1) {
						int width = that.getWidth() - 32;
						int x = r.nextInt(width);
						int y = r.nextInt(20);
						Mine mine = new Mine(that, new Point(x, y));
						enermy.add(mine);
					}
					select = r.nextInt(200);
					if (select == 1) {
						int width = that.getWidth() - 43;
						int x = r.nextInt(width);
						int y = r.nextInt(20);
						Destroyer mine = new Destroyer(that, new Point(x, y));
						enermy.add(mine);
					}
				} else {
					if (that.oppessor == null) {
						int width = that.getWidth() - 68;
						int x = r.nextInt(width);
						int y = r.nextInt(20);
						that.oppessor = new Oppressor(that, new Point(x, y));
					} else {
						int select = r.nextInt(200);
						if (select == 1) {
							List<Mine> mines = that.oppessor.emit();
							Iterator<Mine> it3 = mines.iterator();
							while (it3.hasNext()) {
								that.enermy.add((AirWarObject) it3.next());
							}
						}

					}

				}
				//control hero
				switch (that.keyDown) {
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
				// check hero and enermy

				Iterator<AirWarObject> it = enermy.iterator();

				while (it.hasNext()) {
					AirWarObject obj = (AirWarObject) it.next();
					if (obj.isIntersection(hero)) {
						obj.isDisappear = true;
						it.remove();

						explosions.add(new Explosion(that, new Point(
								(int) (hero.rect.x + hero.rect.width / 2),
								(int) (hero.rect.y + hero.rect.height / 2))));
						hero = null;
						that.updateUI();
						gameEnd();
						return;
					}
				}

				/**
				 * it = enermy.iterator();
				 * 
				 * while(it.hasNext()) { AirWarObject obj =
				 * (AirWarObject)it.next(); Iterator it1 = bullets.iterator();
				 * while(it1.hasNext()){
				 * 
				 * AirWarObject obj1 = (AirWarObject)it1.next();
				 * if(obj.isIntersection(obj1)) { obj.blood --; it1.remove();
				 * if(obj.blood<=0) { explosions.add(new Explosion(that, new
				 * Point((int)(obj.rect.x + obj.rect.width / 2),
				 * (int)(obj.rect.y + obj.rect.height/ 2)))); it.remove();
				 * break; } }
				 * 
				 * }
				 * 
				 * }
				 */
				it = bullets.iterator();
				while (it.hasNext()) {
					AirWarObject obj = (AirWarObject) it.next();
					Iterator<AirWarObject> it1 = enermy.iterator();
					AirWarObject selected = null;
					double minDis = 100000;
					while (it1.hasNext()) {
						AirWarObject obj1 = (AirWarObject) it1.next();
						//select the first object intersecting with bullet.
						if (obj.isIntersection(obj1)) {
							double x = obj1.rect.x + obj1.rect.width / 2
									- obj.rect.x - obj.rect.width / 2;
							double y = obj1.rect.y + obj1.rect.height / 2
									- obj.rect.x - obj.rect.height / 2;
							if (x * x + y * y < minDis) {
								minDis = x * x + y * y;
								selected = obj1;
							}
						}
					}
					if (selected != null) {
						selected.blood--;
						if (selected.blood <= 0) {
							explosions
									.add(new Explosion(
											that,
											new Point(
													(int) (selected.rect.x + selected.rect.width / 2),
													(int) (selected.rect.y + selected.rect.height / 2))));
							selected.isDisappear = true;
							try {
								File soundFile = new File("res/explosion.wav");
								AudioInputStream audioIn = AudioSystem
										.getAudioInputStream(soundFile);
								// Get a sound clip resource.
								Clip clip = AudioSystem.getClip();
								// Open audio clip and load samples from the
								// audio input stream.
								clip.open(audioIn);
								clip.start();
							} catch (Exception e) {
								e.printStackTrace();
							}
							if (selected instanceof Missle) {
								that.score += 100;
							} else if (selected instanceof Mine) {
								that.score += 10;
							} else if (selected instanceof Oppressor) {
								that.score += 1000;
							}
							enermy.remove(selected);
						}

						it.remove();
					}
				}
				it = bullets.iterator();
				if (that.oppessor != null) {
					while (it.hasNext()) {
						AirWarObject obj = (AirWarObject) it.next();
						if (obj.isIntersection(that.oppessor)) {
							it.remove();
							that.oppessor.blood--;
							if (that.oppessor.blood <= 0) {
								explosions
										.add(new Explosion(
												that,
												new Point(
														(int) (that.oppessor.rect.x + that.oppessor.rect.width / 2),
														(int) (that.oppessor.rect.y + that.oppessor.rect.height / 2))));
								that.score += 1000;
								UpGradeOrExit();
								return;
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
		try {
			File soundFile = new File("res/endure.wav");
			AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			this.backgroundmusic = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			backgroundmusic.open(audioIn);
			backgroundmusic.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.addWindowListener(new WindowAdapter() {

			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				if (backgroundmusic != null) {
					backgroundmusic.start();
				}
			}

			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				backgroundmusic.close();
			}

			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				backgroundmusic.stop();
			}
		});

	}

	public void UpGradeOrExit() {
		Object[] options = { "继续", "退出" };
		int select = JOptionPane.showOptionDialog(null, "恭喜晋级！要进入下一关吗？",
				"Warning", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if (select == 0) {
			this.hero = new Hero(this, new Point(160, 400));
			this.level++;
			this.timerNumber = 0;
			this.enermy.clear();
			this.bullets.clear();
			this.oppessor = null;
			this.setFocusable(true);
		} else {
			System.exit(0);
		}

	}

	public void gameEnd() {
		Object[] options = { "再来一局", "退出" };
		int select = JOptionPane.showOptionDialog(null, "Click OK to continue",
				"Warning", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if (select == 0) {
			this.hero = new Hero(this, new Point(160, 400));
			this.timerNumber = 0;
			this.oppessor = null;
			this.level = 0;
			this.enermy.clear();
			this.bullets.clear();
			this.setFocusable(true);

			this.score = 0;
		} else {
			System.exit(0);
		}

	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int i = arg0.getKeyCode();

		switch (i) {
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
			bullets.add(new Plasma(this, new Point((int) Math.round(hero.rect.x
					+ hero.rect.width / 2), (int) Math.round(hero.rect.y))));
			try {
				File soundFile = new File("res/plasma.wav");
				AudioInputStream audioIn = AudioSystem
						.getAudioInputStream(soundFile);
				// Get a sound clip resource.
				Clip clip = AudioSystem.getClip();
				// Open audio clip and load samples from the audio input stream.
				clip.open(audioIn);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case KeyEvent.VK_K:
			Iterator<AirWarObject> it = this.enermy.iterator();
			AirWarObject obj = null;
			while (it.hasNext()) {
				AirWarObject ob = (AirWarObject) it.next();
				if (ob.rect.y + ob.rect.height < hero.rect.y) {
					if (obj == null) {
						obj = ob;
					} else {
						if (ob.rect.y > obj.rect.y) {
							obj = ob;
						}
					}
				}
			}
			if (obj == null && this.oppessor != null) {
				obj = this.oppessor;
			}
			bullets.add(new Missle(this, new Point((int) Math.round(hero.rect.x
					+ hero.rect.width / 2), (int) Math.round(hero.rect.y)), obj));
			try {
				File soundFile = new File("res/plasma.wav");
				AudioInputStream audioIn = AudioSystem
						.getAudioInputStream(soundFile);
				// Get a sound clip resource.
				Clip clip = AudioSystem.getClip();
				// Open audio clip and load samples from the audio input stream.
				clip.open(audioIn);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		Graphics2D g2d = (Graphics2D) g;
		drawScoreAndLevel(g2d);
		if (level >= 0 && hero != null) {
			hero.draw(g2d);
		}
		if (this.oppessor != null) {
			this.oppessor.draw(g2d);
		}
		Iterator<AirWarObject> it = this.bullets.iterator();
		while (it.hasNext()) {
			AirWarObject obj = (AirWarObject) it.next();
			obj.draw(g2d);
			if (obj.isDisappear) {
				it.remove();
			}
		}
		it = this.enermy.iterator();
		while (it.hasNext()) {
			AirWarObject obj = (AirWarObject) it.next();
			obj.draw(g2d);
			if (obj.isDisappear) {
				it.remove();
			}
		}
		it = this.explosions.iterator();
		while (it.hasNext()) {
			Explosion obj = (Explosion) it.next();
			obj.draw(g2d);
			obj.life--;
			if (obj.life == 0) {
				it.remove();
			}
		}

		// explosions.clear();
		// this.updateUI();
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D
				|| e.getKeyCode() == KeyEvent.VK_S
				|| e.getKeyCode() == KeyEvent.VK_W) {
			this.keyDown = -1;
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void drawScoreAndLevel(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		// at.rotate(rotation, (Math.round(rect.width) >> 1),
		// (Math.round(rect.height) >> 1));
		at.translate(5, 5);
		g.drawImage(AirWarObject.imgs.get("Level.png"), at, null);
		at.translate(44, 0);
		int level = this.level + 1;
		String str = String.valueOf(level);
		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			int num = a - '0';
			g.drawImage(AirWarObject.imgs.get("num" + num + ".png"), at, null);
			at.translate(20, 0);
		}
		g.drawImage(AirWarObject.imgs.get("score.png"), at, null);
		String scorestr = String.valueOf(this.score);
		at.translate(46, 0);
		for (int i = 0; i < scorestr.length(); i++) {
			char a = scorestr.charAt(i);
			int num = a - '0';
			g.drawImage(AirWarObject.imgs.get("num" + num + ".png"), at, null);
			at.translate(20, 0);
		}

	}

}
