import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Simulates the game of Black Jack between
 * One player and the dealer
 * 
 * @author joemacd
 * @version 01/15/2025 (revisited from 2020)
 */
public class BJGame
{
	private Deck deckOfCards;  
	private ArrayList<Card> userHand;
	private ArrayList<Card> dealerHand;
	private String name;


	/**
	 * Create a deck of cards,and 2 hands and deal 2 initial cards into each hand.
	 * 
	 */
	public BJGame(){
		deckOfCards = new Deck();
		deckOfCards.shuffle();
		userHand = new ArrayList<Card>();
		dealerHand = new ArrayList<Card>();
		for(int i = 0; i < 2; i++) {
			userHand.add(deckOfCards.dealCard());
			dealerHand.add(deckOfCards.dealCard());
		}
		
		name = JOptionPane.showInputDialog("Welcome to BlackJack!  What's your name?");
	}

	/**
	 * Remove all cards from both hands and then 
	 * re-deal 2 cards into each of the hands.
	 * 
	 */
	public void reset(){
		userHand.clear();
		dealerHand.clear();
		for(int i = 0; i < 2; i++) {
			userHand.add(deckOfCards.dealCard());
			dealerHand.add(deckOfCards.dealCard());
		}
		
	}

	/**
	 * Determine the point total of a hand so far. 
	 * An ace could be worth 11 or 1 determine the best use
	 * All face cards (11,12,13) are worth 10
	 * @return the point total of hand
	 */
	public int pointTotal(ArrayList<Card> hand){
		int points = 0;
		int numAces = 0;
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getRank() < 10 && hand.get(i).getRank() > 1) {
				points += hand.get(i).getRank();
			}
			else if (hand.get(i).getRank() >= 10){
				points += 10;
			}
		}
		for (int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getRank() == 1) {
				if (points + 11 > 21) {
					points ++;
				}
				else {
					points += 11;
				}
			}
		}
		
		
		return points;
	}
	
	/**
	 * Deal another card into a hand
	 */
	public void hitMe(ArrayList<Card> hand)
	{
		hand.add(deckOfCards.dealCard());
	}

	/**
	 * Create the dealers hand
	 * Dealer must take cards until total is greater than or equal to 17. 
	 */
	public void makeDealerHand(){
		while(pointTotal(dealerHand) < 17) {
			hitMe(dealerHand);
		}

	}

	/**
	 * @return true if hand has Black Jack, false otherwise
	 * BlackJack is 2 cards, one an Ace and one a face card
	 */
	private boolean hasBlackJack(ArrayList<Card> hand) {
		if (pointTotal(hand) == 21 && hand.size() == 2) {
			return true;
		}
		return false;
		
	}


	/**
	 * @return true if the hand exceeds 21
	 */
	public boolean handBusted(ArrayList<Card> hand)
	{
		if (pointTotal(hand) > 21) {
			return true;
		}
		return false;
	}
	
	/**
	 * determine if user busts.  If so make the dealer hand.
	 * if dealer also busts its a tie, otherwise user loses
	 * Use JOptionPane to display appropriate messages
	 * @return true if the game is over and false if we
	 * are still playing
	 */
	public boolean UserBusts()
	{
		if(handBusted(userHand)) {
			makeDealerHand();
			if(handBusted(dealerHand)) {
				JOptionPane.showMessageDialog(null, "It's a tie!");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "You lose");
				return true;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * Determine if user has blackJack (use your method)or 21.
	 * if they do make the dealer hand and see if user wins
	 * or they tie the dealer.
	 * Use JOptionPane to display one of the 4 possible messages
	 * @return true if the player has BlackJack or 21 points
	 */
	public boolean UserBlackJack()
	{
		boolean gameover = false;
		if(hasBlackJack(userHand)) {
			makeDealerHand();
			if(handBusted(dealerHand) || pointTotal(dealerHand) < 21) {
				JOptionPane.showMessageDialog(null, "You got blackjack! You win!");
				gameover = true;
			}
			else if(pointTotal(dealerHand) == 21) {
				JOptionPane.showMessageDialog(null, "You got blackjack! You tied the dealer!");
				gameover = true;
			}
			
		}
		
		
		
		return gameover;
	}

	/**
	 * This method is called if user has hit "Stay" and doesn't have a blackjack
	 * It provides a custom message for Dealer winning with BlackJack
	 * with the first 2 cards.
	 * @return true if dealer has BlackJack and has won the game
	 */
	public boolean dealerHasBlackJack(){
		if(hasBlackJack(dealerHand)) {
			JOptionPane.showMessageDialog(null, "The dealer got blackjack. You lose.");
			return true;
		}
		return false;
		
	}

	/**
	 * Once user decides to "stay" and the dealer does not have 2 card Black Jack
	 * Compare the dealer's final hand and the users hand to see who wins.
	 * Be sure you get all the possibilities. Include dealer gets 21, ties and dealer busting.
	 * Use JOptionPane to print the winning/losing messages
	 */
	public void whoWins()
	{
		if(pointTotal(dealerHand) > pointTotal(userHand)) {
			if(pointTotal(dealerHand)!=21 && pointTotal(dealerHand)<21) {
			JOptionPane.showMessageDialog(null, "The dealer won. You lose.");
			}
			else if(pointTotal(dealerHand)==21){
				JOptionPane.showMessageDialog(null, "The dealer got 21. You lose.");
			}
			else {
				JOptionPane.showMessageDialog(null, "The dealer busted. You win!");
			}
		}
		else if(pointTotal(dealerHand) == pointTotal(userHand)) {
			JOptionPane.showMessageDialog(null, "You tied with the dealer.");
		}
		else {
			JOptionPane.showMessageDialog(null, "The dealer lost. You win!");
		}
		int dealerpts = pointTotal(dealerHand);
		int userpts = pointTotal(userHand);
		
		
	}

	/**
	 * Ask the user if they want to play another game using JOPtionPane
	 * if they do, reset the game, if not show a goodbye message
	 * @return true if playing again
	 */
	public boolean playAgain()
	{
		int response = JOptionPane.showConfirmDialog(null,"Would you like to play again?","Play Another Game?",
				JOptionPane.YES_NO_OPTION);

		if (response == JOptionPane.YES_OPTION)
		{
			reset();
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Thanks for playing BlackJack, " + name + "!");
			return false;
		}

	}
	
	/* ------------------ Getters! ----------------------*/
	/**
	 * @return user hand
	 */
	public ArrayList<Card> getUserHand() {
		return userHand;
	}

	/**
	 * @return dealer hand
	 */
	public ArrayList<Card> getDealerHand() {
		return dealerHand;
	}

	/**
	 * @return user name
	 */
	public String getUserName()
	{
		return name;
	}
	
	
	
	/**
	 * Displays a player's hand in an attractive way
	 * Will indicate whose hand it is displaying
	 * This method for testing purposes only!
	 */
	private void displayHand(ArrayList<Card> hand, String whoseHand) {
		System.out.println("*******************************************");
		System.out.println(whoseHand + ", this is your current hand:");
		for (Card c: hand){
			System.out.print("{" +c + "} ");
		}
		System.out.println();
		System.out.println("You have a point total of: " + pointTotal(hand));
		System.out.println("*******************************************");
	}


}


