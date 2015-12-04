import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Card extends JPanel{

	protected int value;
	protected String faceValue;
	protected String suit;
	protected ImageIcon image;
	private ImageIcon faceDownImage;
	private Color bgColor;
	//private MouseListener ml;// = new CardAnimator();
	
	//Constructs a card
	public Card(){
		this.value = 0;
		this.suit = null;
		this.image = null;
		this.faceDownImage = new ImageIcon(getClass().getResource("\\Images\\CardFaceDown.jpg"));
		this.bgColor = Color.GRAY;
		//this.addMouseListener(ml);
	}
	
	/**
	 * Draws the shape
	 * @param face the face value of the card (i.e. "king" for king)
	 * @param suit the suit of the card (i.e. diamond)
	 */
	public Card(String faceValue, String suit, int value){
		this.value = value;
		this.faceValue = faceValue;
		this.suit = suit;
		//get card images
		this.image = new ImageIcon(getClass().getResource("\\Images\\" + faceValue + suit + ".jpg"));
		this.faceDownImage = new ImageIcon(getClass().getResource("\\Images\\CardFaceDown.jpg"));
	}
	
	
	
	
	
	/**
	 * public getImage()
	 * @return the picture of the card
	 */
	public ImageIcon getImage(){
		return this.image;
	}
	

	/**
	 * this method returns the image of a facedown card
	 * @return the ImageIcon picture of a facedown card
	 */
	public ImageIcon getFaceDownImage(){
		return this.faceDownImage;
	}
	
	/**
	 * Allows the user to get the value of a card
	 * @return the value of the card
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * Allows the user to set the value of a card
	 * @param v the value to which the card will be set
	 */
	public void setValue(int v){
		this.value = v;
	}
	
	/**
	 * Allows the user to get the face value of the card
	 * @return A String representing the face value
	 */
	public String getFaceValue(){
		return this.faceValue;
	}
	
	/**
	 * Allows the user to get the suit of a card
	 * @return a String representation of the suit of the card
	 */
	public String getSuit(){
		return this.suit;
	}
	
	public String toString(){
		return "Face: " + this.faceValue + " Suit: " + this.suit + " Value: " + this.value;
	}
	
	
	
	
	
	
	
	public class Animator{
		
	}
}
