package frame;

import javax.swing.JFrame;
import UI.AirWarObject;
import UI.MainJPanel;


public class AirWar{
	
	
	public static void main(String[] args)
	{
		 AirWarObject.loadResource();
		 JFrame frame = new JFrame();
		 frame.setResizable(false);
		 
		 MainJPanel startPanel = new MainJPanel(frame);
		
		 frame.setContentPane(startPanel);
		 frame.show();
	 }

}
