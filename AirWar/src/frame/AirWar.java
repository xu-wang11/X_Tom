package frame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;




import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.XmlParse;
import UI.StartPanel;


public class AirWar{
	
	 
	 public static void main(String[] args)
	 {
		 XmlParse parse = new XmlParse();
		 parse.getLocation();
		 JFrame frame = new JFrame();
		 frame.setResizable(false);
		 //frame.setSize(480, 800);
		 //frame.getContentPane().add(new JLabel("hehe"));
		 StartPanel startPanel = new StartPanel(frame,  parse);
		 //frame.add(startPanel);
		 frame.setContentPane(startPanel);
		 frame.show();
	 }

}
