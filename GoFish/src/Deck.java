/*
 * An object of type deck represents by default a 52-card deck of playing cards. 
 *TODO add joker capability
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
	//An array of 52 cards
	private ArrayList<Card> deck = new ArrayList<Card>();
	private final String[] SUITS = {"H","C","S","D"};//Hearts, Clubs, Spades, Diamonds
	private final String[] FACE_VALUES = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};


	
	/**
	 * Constructs a regular 52-card poker deck. Initially, the cards
	 * are sorted in order. the shuffle() method can be called to
	 * randomize the order. (Note that "new Deck()" is equivalent to
	 * "new Deck(false)".)
	*/
	public Deck(){
		this(false);//Just call the other constructor in this class
	}
	
	
	/**
	 * Constructs a 52- or 54-card poker deck, depending on which boolean value is passed to the constructor.
	 * Initially, the cards are sorted in order. the shuffle() method can be called to
	 * randomize the order. (Note that "new Deck()" is equivalent to "new Deck(false)".)
	 * @param includeJokers if true, two jokers are included in the deck; if false, there are no jokers in the deck.
	*/
	public Deck(boolean includeJokers){
		
		for(String suit : SUITS){
			for(String face : FACE_VALUES){
				deck.add(new Card(face, suit,Arrays.asList(FACE_VALUES).indexOf(face) + 1));
			}
		}
		
		//add two jokers if requested
		if(includeJokers){
			for(int i = 0; i < 2; i++)
				deck.add(new Card("Joker","*",-1));
		}	
	}
	
	
	/**
     * Put all the used cards back into the deck (if any), and
     * shuffle the deck into a random order.
     */
    public void shuffle() {
    	System.out.println("--------- SHUFFLING DECK ---------");
    	for(int i=0; i < 4; i++)
    		Collections.shuffle(deck);
    	System.out.println("--------- DECK SHUFFLED! ---------");
    }
    
    /**
     * deals 5 cards to each player, removing each card from the Deck list
     * as they are dealt
     * @param players an array of Player objects
     * @param cardsPerPlayer how many cards to deal to each player
     */
    public void deal(Player[] players, int cardsPerPlayer){
    	System.out.println("--------- DEALING CARDS ---------");
    	//Deal the specified number of cards to each player
    	for(int cards = 0; cards < cardsPerPlayer; cards++){
	    	for(Player player : players){
	    		player.addToHand(this.getTop());
	    		System.out.println(player.getName()+ "'s hand: " + player.getHand().toString());
	    	}
    	}
    	System.out.println("--------- CARDS DEALT! ---------");
    }

    
	/**
	 * Selects the top (index 0) card from the deck, removes it from the deck list,
	 * and returns it
	 * @return topCard the card at the top of the deck.
	 */
	public Card getTop(){
		if(!deck.isEmpty()){
			Card topCard = deck.get(0);
			deck.remove(0);
			return topCard;
		}
		return null;
	}
	
	/**
	 * returns the number of cards remaining in the deck
	 * @return
	 */
	public int cardCount(){
		return deck.size();
	}
	
	/**
	 * returns whether the deck contains jokers
	 * @return a boolean stating whether the deck contains jokers
	 */
	public boolean hasJokers(){
    	return deck.size() == 54;
    }
	
	/**
	 * Return a string representation of the deck with format <value1><suit1>, <value2><suit2>, etc.
	 */
	public String toString(){
		String deckString = "";
		for(Card card : deck){
			deckString += card.toString()+"\n\r";
		}
		return deckString;
	}
}
