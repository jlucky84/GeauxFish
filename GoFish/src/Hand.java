import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.Arrays;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * @Author Justin LeGrand
 * @CreatedOn December 9, 2014
 */

public class Hand {

	protected CopyOnWriteArrayList<Card> inHand;
	protected int count;
	protected final ArrayList<String> orderedCardValues = new ArrayList<String>(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
	private static Color bgColor = Color.getHSBColor((float)120.00, (float)0.543,(float)0.62);
	/**
	 * Constructor
	 */
	public Hand(){
		this.inHand = new CopyOnWriteArrayList<Card>();
	}
	
	
	/**
	 * sorts the Hand, A being low, K being high.
	 */
	public void sort(){	
		for(int i=0; i< inHand.size() - 1; i++){
			for(int j = i+1; j < inHand.size(); j++){
				Card firstCard = inHand.get(i);
				Card secondCard = inHand.get(j);
				if(orderedCardValues.indexOf(firstCard.getValue()) > orderedCardValues.indexOf(secondCard.getValue())){
					Card temp = firstCard;
					firstCard = secondCard;
					secondCard = temp;
				}	
			}
		}
	}
	
	
	/**
	 * This method checks the hand to see if it contains a card (i.e. a 2 of hearts)
	 * @param card - the card to look for in the deck
	 * @return true if card is contained, false if not contained
	 */
	public boolean containsCard(Card card){
		return this.inHand.contains(card);
	}
	
	
	/**
	 * This method iterates over the hand and finds groups (matching values of cards).
	 * if found, it adds the grouo to the player's groups, and removes the cards from the hand
	 * @REQUIRES deck to be sorted prior to grouping.
	 * @return an ArrayList of Card objects that have been grouped. ArrayList is empty if no groups found
	 */
	public ArrayList<ArrayList<Card>> getGroups(){
		ArrayList<ArrayList<Card>> groups = new ArrayList<ArrayList<Card>>();
		Iterator<Card> scan = inHand.iterator();
		//iterate over each card
		while(scan.hasNext()){
			Card cardA = scan.next();
			Card cardB = getCardFollowing(cardA);
			if(cardB != null &&
					cardB.getValue() == cardA.getValue()){
				ArrayList<Card> group = new ArrayList<Card>();
				group.add(cardA);
				group.add(cardB);
				//sscan.remove();
				inHand.remove(cardB);
				inHand.remove(cardA);
				groups.add(group);
			}
		}
		return groups;
	}
	
	/**
	 * in a sorted hand, returns the card immediaately following the card passed as a parameter
	 * @param card the card preceding the one being grabbed
	 * @return the next card (gauged by numerical value) in the hand.
	 */
	public Card getCardFollowing(Card card){
		int cardIndex = inHand.indexOf(card);
		if(cardIndex < inHand.size()-1)
			return inHand.get(cardIndex+1);
		else return null;
	}
	
	
	/**
	 * This method checks the hand to see if a card value is contained (i.e. a '2').
	 * It does not check if suit matches
	 * @param value - the face value of the card to look for in the deck
	 * @return true if value is contained in deck, false if not contained
	 */
	public boolean containsCardValue(String value){
		Iterator<Card> scan = inHand.iterator();
		while(scan.hasNext()){
			Card chkCard = scan.next();
			if(value != null && value.equalsIgnoreCase(chkCard.getFaceValue()))
				return true;
		}
		//reached end of hand
		return false;
	}
	
	/**
	 * Grabs a card by value from a deck, removes it from the deck, and returns it to the calling method.
	 * @param value - value the value of the card to look for in the deck (i.e. a '2')
	 * @return the Card object matching that value
	 */
	public Card getCardByValue(int value){
		Iterator<Card> scan = inHand.iterator();
		while(scan.hasNext()){
			Card chkCard = scan.next();
			if(chkCard.getValue() == value)
				return chkCard;
		}
		return null;
	}
	
	/**
	 * Grabs a card by face value from a deck, removes it from the deck, and returns it to the calling method.
	 * @param value - value the value of the card to look for in the deck (i.e. a '2' or 'J')
	 * @return the Card object matching that value
	 */
	public Card getCardByFaceValue(String value){
		Iterator<Card> scan = inHand.iterator();
		while(scan.hasNext()){
			Card chkCard = scan.next();
			if(chkCard.faceValue.equalsIgnoreCase(value))
				return chkCard;
		}
		return null;
	}
	
	
	/**
	 * adds a card to a players hand in numeric order, i.e. adding a "5" to the hand would be placed after all card values < 5
	 * @param newCard new card to add to a player's deck. assumes not NULL
	 */
	public void addInPlace(Card newCard){
		Iterator<Card> scan = inHand.iterator();
		if(inHand.isEmpty())
			inHand.add(newCard);
		else{
			int i = 0;
			while(scan.hasNext()){
				Card card = scan.next();
				if(card.getValue() >= newCard.getValue()){
					inHand.add(i, newCard);
					return;
				}
				i++;
			}
			//card value is greater than other cards in hand
			inHand.add(newCard);
		}
	}
	
	/**
	 * This method removes a card from a deck, returning that card to the calling method
	 * @param card - the Card object to grab from the deck
	 * @return the Card object that has been removed, returns null if card not found in hand.
	 */
	public Card removeCard(Card card){
		boolean isRemoved = this.inHand.remove(card);
		if(isRemoved)
			return card;
		return null;
	}
	
	/**
	 * Prints a player's hand to the console.
	 * Display format is comma delimited
	 */
/*	public void displayHand(){
		String hand = "";
		for(Card card : this.inHand){
			hand += card.getCardString() + ", ";
		}
		System.out.println(hand);
	}*/
	
	/**
	 * returns a JPanel of images showing the back of a card of an opponents hand
	 * @param isMasked if false the card value is displayed. if true the back of the card is displayed
	 */
	public JPanel displayHand(boolean isMasked){
		JPanel handPanel = new JPanel();
		for(Card card : this.inHand){
			ImageIcon cardImage;
			//display for Opponents' cards
			if(isMasked){
				cardImage = card.getFaceDownImage();
			}
			//display for player's cards
			else{
				cardImage = card.getImage();
			}
			
			JLabel cardPanel = new JLabel(cardImage);
			cardPanel.setBounds(0, 0, 76, 108);
			handPanel.add(cardPanel);
		}
		handPanel.setBackground(bgColor);
		return handPanel;
	}
	
	/**
	 * Returns the number of cards in the hand 
	 * @return the number of cards in the hand
	 */
	
	public int size(){
		return this.inHand.size();
	}
	
	
	/*
	 * Returns a string representation of the hand.
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String result="";
		Card card = null;
		Iterator<Card> scan = inHand.iterator();
		while (scan.hasNext()){
			card= scan.next();
			result += card.getFaceValue()+ card.getSuit() + ", ";
		}
		return result;
	}
}