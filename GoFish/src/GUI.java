import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.*;


public class GUI extends JPanel{
	
	private ImageIcon table = new ImageIcon();
	private JLabel lblTable = new JLabel();
	private JLabel lblDeck = new JLabel();
	private JPanel topPanel = new JPanel();
	private JPanel playerPanel = new JPanel();
	private JPanel opponentPanel = new JPanel();
	private JButton playAgainButton = new JButton();
	private JButton dealButton = new JButton();
	private Deck deck;
	private Graphics2D g2;
	
//	private ImageIcon card;
	
	JTextPane winLoseBox = new JTextPane();
	
	GoFish game = new GoFish();
	
	
	
	
	
	
	public GUI(){
		
		setLayout(new BorderLayout());
		this.setVisible(true);
		BufferedImage cardImage = new BufferedImage(61,86,1);
		
		
		JPanel cardDisplay = new JPanel();
		
		table = new ImageIcon(getClass().getResource("FeltTable.jpg"));
		lblTable = new JLabel(table);
		add(lblTable);
		add(cardDisplay);		
	    
	    topPanel.setBackground(new Color(0, 122, 0));
	    playerPanel.setBackground(new Color(0, 122, 0));
	    opponentPanel.setBackground(new Color(0, 122, 0));
	    
	    topPanel.setLayout(new FlowLayout());
	    winLoseBox.setText(" ");
	    winLoseBox.setFont(new java.awt.Font("Helvetica Bold", 1, 20));
	    dealButton.setText("  Deal");
	    //dealButton.addActionListener(new dealButton());

	    playAgainButton.setText("  Play Again");
	    playAgainButton.addActionListener(new playAgainButton());
	    playAgainButton.setEnabled(false); 
	    
	    //dealerlabel.setText("  Dealer:  ");
	    //playerlabel.setText("  Player:  ");
	    topPanel.add(winLoseBox);
	    topPanel.add(dealButton);
	    topPanel.add(playAgainButton);
	    //opponentPanel.add(playerlabel);
	    //playerPanel.add(dealerlabel);
	    
	    setLayout(new BorderLayout());
	    add(topPanel,BorderLayout.NORTH);
	    add(playerPanel,BorderLayout.CENTER);
	    add(opponentPanel,BorderLayout.SOUTH);
	}
	
	

	
	
	
	
	
	
/**
 * shows the screen
 */
 public void display(){
   JFrame myFrame = new JFrame("Go Fish");
   myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   myFrame.setContentPane(this);
   myFrame.setPreferredSize(new Dimension(700,550));

   //Display the window.
   myFrame.pack();
   myFrame.setVisible(true);
   
 }//end display
	
	
	
	
	
	/**
	 * displays a message box with the provided message
	 * @param msg
	 */
 	public void displayMessage(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
	
	
	/*************************************************************
	   PlayAgainButton
	    resets screen
	   @param e Play Again button pressed
	*************************************************************/
	class playAgainButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game=new GoFish();
			playerPanel.removeAll();
		    opponentPanel.removeAll();
		    //dealerlabel.setText("Dealer: ");
		    //playerlabel.setText("Player: ");
		    winLoseBox.setText(""); 
		    //dealer = new Hand();
		    Hand player = new Hand();
		    
//		    hitbutton.setEnabled(false);
//		    staybutton.setEnabled(false);
		    playAgainButton.setEnabled(false);
		    dealButton.setEnabled(true);

		}//end actionPerformed
	}//end playAgainButton
	
	
	
	
	/*************************************************************
	   DealButton
	   @param e Deal button pressed
	*************************************************************/

/*	
	class dealButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
//			playerPanel.add(dealerlabel);
//			opponentPanel.add(playerlabel);
			
			/**
			* Get's dealer and player cards from Hand
			* and the image associated with that random
			* card and puts them on the screen.
			* *
			
			//dealercard0 = new JLabel(new ImageIcon("back.jpg"));
			deck.deal(players,cardsPerPlayer);
			
			//to iterate set and get current dealer cards
			Card dcard=null;
			Iterator<Card> dscan = (dealer.inHand).iterator();
			int count = 0;
			
			while (dscan.hasNext()){
				dcard = dscan.next();
				if(count==0)
					dealercard1 = new JLabel(dcard.getimage());
				else 
					dealercard2 = new JLabel(dcard.getimage());
				count++;
			}
			
			//to iterate set and get current player cards
			Iterator<Card> pscan = (player.inHand).iterator();
			count = 0;
			
			while (pscan.hasNext()){
				Card pcard = pscan.next();
				if(count==0)
					playercard1 = new JLabel(pcard.getimage());
				else
					playercard2 = new JLabel(pcard.getimage());
				count++;
			}
			
			playerPanel.add(dealercard0);
			playerPanel.add(dealercard2);
	 
			opponentPanel.add(playercard1);
			opponentPanel.add(playercard2);
	    
			dealerlabel.setText("  Dealer:  "+ dcard.getvalue());
			playerlabel.setText("  Player:  " + game.handValue(player));
	    
			dealButton.setEnabled(false);

			add(playerPanel,BorderLayout.CENTER);
			add(opponentPanel,BorderLayout.SOUTH);

	  }
	}//end dealbutton
	
	*/
	
	//public class redrawPanel extends JPanel{
		public void paintComponent(Graphics G){
			super.paintComponent(G);			
		}
	//}
}
