import java.util.ArrayList;


public class Player {

	private ArrayList<ArrayList<Card>> sets;
	private Hand hand; 
	private String name;
	
	public Player(String name){
		this.sets = new ArrayList<ArrayList<Card>>();
		this.hand = new Hand();
		this.name = name;	
	}
	
	
	/**
	 * Returns an ArrayList of Card matches that were found in a Hand
	 * @return an ArrayList of Card matches that were found in a Hand
	 */
	public ArrayList<ArrayList<Card>> getSets(){
		return this.sets;
	}
	
	
	public void addSets(ArrayList<ArrayList<Card>> sets){
		this.sets.addAll(sets);
	}
	
	public void printSets(){
		for(ArrayList<Card> set : sets){
			for(Card card : set){
				System.out.print(card.getFaceValue());
			}
			System.out.print(", ");
		}
	}
	
	/**
	 * This method calls the Hand method scanForGroups, and if a group is found, adds it to the players Sets
	 */
	public void movePairsToSets(){
		//Find matches in player's hand
		ArrayList<ArrayList<Card>> groups = this.hand.getGroups();
		
		if(groups.size() != 0){
			this.sets.addAll(groups);
		}
	}
	
	
	/**
	 * 
	 * @return returns name of player
	 */
	public String getName(){
		return this.name;
	}
	
	public void addToHand(Card card){
		this.hand.addInPlace(card);
	}
	
	public Hand getHand(){
		return this.hand;
	}
	
	public void sortHand(){
		this.getHand().sort();
	}
}
