import java.awt.Point;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.net.URL;
import java.util.ArrayList;


/**
 * This class provides a GUI for a BlackJack game.
 *
 * @author joemacd
 * @version 01/15/2025 (revisited from 2020)
 */
public class CardGameGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	/** Width of a card. */
	private static final int CARD_WIDTH = 73;
	/** Height of a card. */
	private static final int CARD_HEIGHT = 97;
	/** Row (y coord) of the upper left corner of the first card. */
	private static final int LAYOUT_TOP = 30;
	/** Column (x coord) of the upper left corner of the first card. */
	private static final int LAYOUT_LEFT = 30;
	/** Distance between the upper left x coords of
	 *  two horizonally adjacent cards. */
	private static final int LAYOUT_WIDTH_INC = 100;
	/** Distance between the upper left y coords of
	 *  two vertically adjacent cards. */
	private static final int LAYOUT_HEIGHT_INC = 125;
	/** y coord of the " user point total" label. */
	private static final int LABEL_TOP = 160;
	/** x coord of the "user point total" label. */
	private static final int LABEL_LEFT = 540;
	/** Distance between the tops of the "user point total" and
	 *  the "dealer points" labels. */
	private static final int LABEL_HEIGHT_INC = 35;

	/** The Black Jack Game */
	private BJGame myGame;
	private ArrayList<Card>userHand;
	private ArrayList<Card>dealerHand;
	private JLabel firstDCard;

	/** The player hand point total message. */
	private JLabel playerPoints;
	/** The dealer hand point total message. */
	private JLabel dealerPoints;
	
	/** Total games played. */
	//private JLabel games;
	//private int totalGames;
	/** The number of games won. */
	//private int totalWins;
	
	/** The user card displays. */
	private ArrayList<JLabel> displayCards;
	/** The dealer card displays. */
	private ArrayList<JLabel> displayCardsD;
	/** The coordinates of the user card displays. */
	private Point[] cardCoords;
	/** The coordinates of the dealer card displays. */
	private Point[] cardCoordsD;

	/** The player's name */
	private String playerName;
	

	/**
	 * Initialize the GUI.
	 */
	public CardGameGUI(BJGame game) {
		myGame = game;
		userHand = myGame.getUserHand();
		dealerHand = myGame.getDealerHand();
		playerName = myGame.getUserName();
		displayCards = new ArrayList<JLabel>(userHand.size()); //array of JLabels
		displayCardsD = new ArrayList<JLabel>(dealerHand.size());
		
		//user points
		playerPoints = new JLabel("User point total: ");
		add(playerPoints);
		playerPoints.setBounds(LABEL_LEFT, LABEL_TOP, 250, 30);

		//dealer points
		dealerPoints = new JLabel("Dealer point total: ");
		add(dealerPoints);
		dealerPoints.setBounds(LABEL_LEFT, LABEL_TOP + LABEL_HEIGHT_INC*3 , 250, 30);
		
		//draw the hands and points
		updateInitialDisplay();
		
	}
	
	/**
	 * determine the coordinates for all the cards
	 * in each of the playing hands
	 * store the coordinates in 2 arrays, one for each hand
	 */
	public void setCardCoords()
	{
		//user's hand
		cardCoords = new Point[userHand.size()];
		int x = LAYOUT_LEFT;
		int y = LAYOUT_TOP;
		for (int i = 0; i < cardCoords.length; i++) {
			cardCoords[i] = new Point(x, y);
			if (i % 5 == 4) {
				x = LAYOUT_LEFT;
				y += LAYOUT_HEIGHT_INC;
			} else { 
				x += LAYOUT_WIDTH_INC;
			}
		}
		//Dealer's hand
		cardCoordsD = new Point[dealerHand.size()];
		x = LAYOUT_LEFT;
		int y2 = LAYOUT_TOP +LAYOUT_HEIGHT_INC *2;
		for (int j = 0; j < cardCoordsD.length; j++) {
			cardCoordsD[j] = new Point(x, y2);
			if (j % 5 == 4) {
				x = LAYOUT_LEFT;
				y2 += LAYOUT_HEIGHT_INC;
			} else { 
				x += LAYOUT_WIDTH_INC;
			}
		}

	}

	/**
	 * Draw the initial display (cards and messages)
	 */
	private void updateInitialDisplay() {
		
		//get current state of the hands
		userHand = myGame.getUserHand();
		dealerHand = myGame.getDealerHand();
		boolean covered = true;
		String dPoints = "???";

		//draw the cards for both hands
		setCardCoords();
		makeDisplayCards();
		setCardImages(userHand, displayCards);
		setCardImages(dealerHand,displayCardsD);

		//cover the first card of the dealer if not end of game
		if(covered){
			URL imageURL = getClass().getResource("cards/back1.GIF");
			if (imageURL != null) {
				ImageIcon icon = new ImageIcon(imageURL);
				firstDCard.setIcon(icon);
			}
		}
		
		//update the points for the user's hand
		playerPoints.setText(playerName +"\'s point total: " + 
				myGame.pointTotal(userHand) + " points.");
		playerPoints.setVisible(true);

		//update the points for the dealer's hand
		dealerPoints.setText("Dealer point total: " +dPoints + " points.");
		dealerPoints.setVisible(true);

		this.repaint();
	}

	/**
	 * Create an arraylist of JLabels once, representing the hand of cards 
	 * for each player.  Add the JLabels to the main panel
	 * of the graphics window. 
	 */
	public void makeDisplayCards()
	{
		for (int k = 0; k < userHand.size(); k++) 
		{
			displayCards.add(new JLabel());
			this.add(displayCards.get(k));
			displayCards.get(k).setBounds(cardCoords[k].x, cardCoords[k].y,
					CARD_WIDTH, CARD_HEIGHT);
		}

		//dealer cards
		for (int k = 0; k < dealerHand.size(); k++) 
		{
			displayCardsD.add(new JLabel());
			this.add(displayCardsD.get(k));
			displayCardsD.get(k).setBounds(cardCoordsD[k].x, cardCoordsD[k].y,
					CARD_WIDTH, CARD_HEIGHT);
		}
		firstDCard = displayCardsD.get(0); //save dealers first card
	}

	/**
	 * Redraw the player's display (cards and messages)
	 * after adding one additional card (hit)
	 */
	public void updatePlayerCards()
	{
			userHand = myGame.getUserHand();
			setCardCoords(); //set the coordinates of the cards
		
			displayCards.add(new JLabel()); //add a card
			int last = displayCards.size()-1; //last card added
			this.add(displayCards.get(last)); //get the card
			displayCards.get(last).setBounds(cardCoords[last].x, cardCoords[last].y,
					CARD_WIDTH, CARD_HEIGHT); //set last cards coordinates
			
			setCardImages(userHand, displayCards); //attach icons to JLabels
			
			//update the points for the user's hand
			playerPoints.setText(playerName +"\'s point total: " + 
					myGame.pointTotal(userHand) + " points.");
			playerPoints.setVisible(true);
			this.repaint();
	}
	
	/**
	 * Redraw the dealer's display (cards and messages)
	 * after drawing hit complete hand
	 * @param dPoints - dealer's current points
	 * @param covered - is the dealer's first card covered
	 */
	public void updateDealerCards(String dPoints, boolean covered) {
				
		dealerHand = myGame.getDealerHand();
		setCardCoords(); //set positions of the cards
	
		for (int k = 2; k < dealerHand.size(); k++) //add new cards if needed
		{
			displayCardsD.add(new JLabel());
			this.add(displayCardsD.get(k));
			displayCardsD.get(k).setBounds(cardCoordsD[k].x, cardCoordsD[k].y,
					CARD_WIDTH, CARD_HEIGHT);
		}
		
		setCardImages(dealerHand, displayCardsD); //set icons to card gifs
		//cover the first card of the dealer if not end of game
		if(covered){
			URL imageURL = getClass().getResource("cards/back1.GIF");
			if (imageURL != null) {
				ImageIcon icon = new ImageIcon(imageURL);
				firstDCard.setIcon(icon);
			}
		}
		
		//update the points for the dealer's hand
		dealerPoints.setText("Dealer point total: " +dPoints + " points.");
		dealerPoints.setVisible(true);
		repaint();
	}



	/**
	 * Setting the arraylist of JLabels with the correct card icon
	 * @param hand - the player hand to set
	 * @param dCards - arraylist of JLabels before icons are set
	 */
	public void setCardImages(ArrayList<Card> hand, ArrayList<JLabel>dCards){

		for (int k = 0; k < dCards.size(); k++) {
			String cardImageFileName =
				imageFileName(hand.get(k), false);
			URL imageURL = getClass().getResource(cardImageFileName);
			if (imageURL != null) {
				ImageIcon icon = new ImageIcon(imageURL);
				dCards.get(k).setIcon(icon);
				dCards.get(k).setVisible(true);
			} else {
				throw new RuntimeException(
						"Card image not found: \"" + cardImageFileName + "\"");
			}
		}
	}

	/**
	 * Returns the image that corresponds to the input card.
	 * Image names have the format "[Rank][Suit].GIF" or "[Rank][Suit]S.GIF",
	 * for example "aceclubs.GIF" or "8heartsS.GIF". The "S" indicates that
	 * the card is selected.
	 *
	 * @param c Card to get the image for
	 * @param isSelected flag that indicates if the card is selected
	 * @return String representation of the image
	 */
	private String imageFileName(Card c, boolean isSelected) {
		String str = "cards/";
		if (c == null) {
			return "cards/back1.GIF";
		}
		str += c.getStringRank() + c.getStringSuit();
		if (isSelected) {
			str += "S";
		}
		str += ".GIF";
		return str;
	}

	

}

