import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;


public class Test {

	ImageIcon background = new ImageIcon(getClass().getResource("\\Images\\FeltTable.jpg"));
	private static Color bgColor = Color.getHSBColor((float)120.00, (float)0.543,(float)0.62);
	public static void main(String[] args){
		//cardImage = new JLabel();
		//String directory = System.getProperty("user.dir");
		//System.out.println("DIR =" + directory );
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel main = new JPanel();
		main.setVisible(true);
		main.setBackground(bgColor);
		MouseListener ml = new CardAnimator();
		
		//Create and add card to display
		for(int i = 2; i < 7; i++){
			Card card = new Card(String.valueOf(i), "C",i);
			//ImageIcon cardImage = ;
			
			JLabel cardLabel = new JLabel(card.getImage());
			cardLabel.setVisible(true);
//			cardLabel.setSize(100, 150);
			//cardLabel.setBackground(Color.white);
			//cardLabel.addMouseListener(new CardAnimator());
			
			JLabel cardContainer = new JLabel();
			cardContainer.setBackground(Color.cyan);
			cardContainer.setVisible(true);
			cardContainer.setSize(150, 100);
			cardContainer.add(cardLabel);
			cardContainer.addMouseListener(ml);
			
			main.add(cardLabel);
		}
		
		frame.setVisible(true);
		frame.add(main);
		frame.pack();
		
	}
	
	
	
	
	
	
	
}



