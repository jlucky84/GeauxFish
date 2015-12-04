

import java.awt.Color;
import java.awt.LayoutManager;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class GoFish {

	//create a new deck. (an empty argument set creates a 52-card deck. passing "true" creates a 54-deck, adding 2 jokers
	private static Deck deck = new Deck();
	private static Scanner sc = new Scanner(System.in);
	private static GUI gui;
	private static Player[] players;
	private static int playerCount = -1;
	private static String winner= "";
	private static boolean soloWinner;
	private static int mostSets;
	private static String playerNameList = "";
	final static int beginningHandSize = 5;
	static Color bgColor = Color.getHSBColor((float)120.00, (float)0.543,(float)0.62);
	
	
	public static void main(String[] args){
		gui = new GUI();
		
		do{
			//Prompt user for the number of players
			try{
				playerCount = Integer.parseInt(JOptionPane.showInputDialog(null, "Welcome to GO FISH!\n\r\n\r"
						+ "How many players?"));
				//Game requires at least 2 players
				if(playerCount <2){
					displayMessage("This game requires at least 2 players!");
				}
			}catch(NumberFormatException e){
				displayMessage("That's not a number!");
			}
			
		}while(playerCount < 2);
		
		players = new Player[playerCount];
		//Prompt user for player names, and create a Player object for each
		for(int i=0; i<players.length; i++){
			players[i] = new Player(JOptionPane.showInputDialog(null, "What is player " + (i+1) +"'s name?"));
			playerNameList += players[i].getName() + ", ";
		}
		
		//shuffle the deck
		deck.shuffle();
		
		//deal 5 cards to each player
		try{
			deck.deal(players, beginningHandSize);
			for(Player player : players){
				player.movePairsToSets();
				System.out.println(player.getName() + "'s hand is:" + player.getHand().toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		displayMessage( "Great! " + playerNameList + "let's get started!");
		
		//play until one person is out of cards
		while(getMinHandSize(players) > 0){
			playRound(players);
		}
		
		
		for(Player player : players){
			if(player.getSets().size() > mostSets){
				winner = player.getName();
				mostSets = player.getSets().size();
			}
			else if(player.getSets().size() == mostSets){
				winner += " & " + player.getName();
				soloWinner = false;
			}
		}
		if(soloWinner)
			displayMessage("Congratulations, " + winner + "! You've won the game with " + mostSets + " sets!");
		else
			displayMessage("There is a tie for first place! " + winner + " both finished the game with " + mostSets + " sets!");
		System.exit(0);
	}
	
	
	public static int getMinHandSize(Player[] players){
		int minHandSize = players[0].getHand().size();
		for(int i=0; i<players.length; i++){
			if(players[i].getHand().size() < minHandSize)
				minHandSize = players[i].getHand().size();
		}
		return minHandSize;
	}
	
	/**
	 * 
	 */
	
	
	public static void playRound(Player[] players){
		//iterate over players for each turn	
		for(Player currentPlayer : players){
			displayMessage("Ok, " + currentPlayer.getName() + ", it's your turn! Click OK when you are ready!");
			
			boolean cardMatched = false;
			String personAsked;
			
			//Player goes at least once. Player's turn continues so long as a match is found.
			do{
				System.out.print("You have the following " + currentPlayer.getSets().size() + " sets: ");
				currentPlayer.printSets();
				System.out.println("\n\rYour cards are:");
				System.out.println(currentPlayer.getHand().toString());//.displayHand();
				
				
				JPanel handPanel = getPlayerHandDisplay(players, currentPlayer);
				gui.add(handPanel);
				gui.pack();
				
				String guess;
				do{
					//TODO Create a ClickListener to determine which card will be matched
					guess = JOptionPane.showInputDialog(null,"Enter the card value you'd like to match");
					if(!currentPlayer.getHand().containsCardValue(guess))
						displayMessage( "You cannot guess a card you do not have.");
				}
				while(!currentPlayer.getHand().containsCardValue(guess));
				
				Player opponent;
				do{
					personAsked = JOptionPane.showInputDialog("Whom would you like to ask?");
					if(personAsked.equalsIgnoreCase(currentPlayer.getName()))
						displayMessage("You cannot ask yourself for a card");
					opponent = getPlayerByName(personAsked);
					if(opponent == null)
						displayMessage("No player found named " + personAsked +".");
				}
				while(opponent == null || opponent.getName().equalsIgnoreCase(currentPlayer.getName()));
				
				cardMatched = false;
				//scan opponent's hand for guessed card
				if(opponent.getHand().containsCardValue(guess)){
					Card cardFromOpponent = opponent.getHand().getCardByFaceValue(guess);
					if(cardFromOpponent != null){
						currentPlayer.addToHand(cardFromOpponent);
						opponent.getHand().removeCard(cardFromOpponent);
						cardMatched = true;
						displayMessage( "You made a match, " + currentPlayer.getName() + ", so your turn continues!");
					}
				}
				else{
					displayMessage( personAsked + " says, \"GeauxFish!\"");
					System.out.println("------- Drawing a Card From the Deck -------");
					//draw a card from the deck
					Card cardFromDeck = deck.getTop();
					//add drawn card to hand
					currentPlayer.addToHand(cardFromDeck);
					//check if drawn card matches guess
					if(cardFromDeck.getFaceValue().equalsIgnoreCase(guess)){
						cardMatched = true;
						displayMessage( "Lucky! You drew a " + guess + " from the deck! Your turn continues!");
					}//end drawn card check
				}//end else
				currentPlayer.movePairsToSets();
			}//end do
			while(cardMatched && currentPlayer.getHand().size() > 0);
			if(currentPlayer.getHand().size() > 0)
				displayMessage("Your turn is over. Please hit enter to move to the next turn.");
			else{
				displayMessage("Your turn is over. You are out of cards!");
				return;
			}		
		}
	}
	
	public final static void clearConsole(){
		for(int i=0; i < 40; i++)
			System.out.println();
	}
	
	public static JPanel getPlayerHandDisplay(Player[] players, Player currentPlayer){
		//CONSOLE OUTPUT
		System.out.println("Opponent's Hands:");
		for(Player player : players){
			System.out.println(player.getName() + ":\t" + player.getHand().toString());
		}
		
		//SWING OUTPUT
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(bgColor);
		for(Player player : players){
			JPanel playerInfo = new JPanel();
			playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.Y_AXIS));
			playerInfo.add(new JTextArea(player.getName()));
			playerInfo.add(new JTextArea("SETS: " + player.getSets().size()));
			
			
			JPanel playerPanel = new JPanel();
			
			playerPanel.add(playerInfo);
			//Display card faces for current player
			if(player.getName().equalsIgnoreCase(currentPlayer.getName())){
				playerPanel.add(player.getHand().displayHand(false));
			}
			//hide faces for opponents
			else{
				playerPanel.add(player.getHand().displayHand(true));
			}
			mainPanel.add(playerPanel);
		}
		return mainPanel;
	}
	
	public static Player getPlayerByName(String name){
		for(Player p : players){
			if(p.getName().equalsIgnoreCase(name))
				return p;
		}
		return null;
	}
	
	public static void displayMessage(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}

}
