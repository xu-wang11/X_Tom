package frame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import UI.StartPanel;


public class AirWar{
	
	 
	 public static void main(String[] args)
	 {
		 JFrame frame = new JFrame();
		 StartPanel startPanel = new StartPanel(frame);
		 frame.add(startPanel);
		 
		 frame.show();
		
	 }

}
