 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * This is the GUI version of the card game BlackJack
 * This class creates the window(JFrame) in which the
 * game is played.  The JPanel where the cards and buttons
 * are drawn is located in CardGameGUI.java
 * 
 * @author joemacd
 * @version 01/15/2025 (revisited from 2020)
 */
public class BlackJack extends JFrame implements ActionListener {
	
	/** JPanel for buttons and cards */
	private CardGameGUI view;
    
    private static final long serialVersionUID = 1L;
	
    /** Height of the game frame. */
	private static final int DEFAULT_HEIGHT = 302;
	/** Width of the game frame. */
	private static final int DEFAULT_WIDTH = 800;

	/** y coord of the "Hit Me" button. */
	private static final int BUTTON_TOP = 30;
	/** x coord of the "Hit Me" button. */
	private static final int BUTTON_LEFT = 570;
	/** Distance between the tops of the "Hit Me" and "Stay" buttons. */
	private static final int BUTTON_HEIGHT_INC = 50;
	
	/** The BlackJack Game */
	private BJGame myGame;

	/** The Hit button. */
	private JButton hitButton;
	/** The Stay button. */
	private JButton stayButton;
    
    /**
     * constructor for BlackJack JFrame
     */
    private BlackJack()
    {
    	super("The Game of Black Jack"); //Title of the JFrame (window)
    	
    	// build the model
        myGame = new BJGame(); //create a new blackjack game
        drawPanel(); //draw buttons and cards           
    }
    
    
    /**
     * Create the JPanel, create button, add to JFrame
     */
    public void drawPanel()
    {
    	//Set window size
    	setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT*2));
    	
    	// build the view
        view = new CardGameGUI(myGame); //create the JPanel for the game
        view.setBackground(Color.CYAN); //set the JPanel color 
		view.setLayout(null);
		view.setPreferredSize(
				new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT*2)); //set size of JPanel
		
		// create and place buttons in JPanel  
		
		//hit button
		hitButton = new JButton();
		hitButton.setText("Hit");
		view.add(hitButton); //add Hit button to JPanel
		hitButton.setBounds(BUTTON_LEFT, BUTTON_TOP, 100, 30);
		hitButton.addActionListener(this);

		//stay button
		stayButton = new JButton();
		stayButton.setText("Stand");
		view.add(stayButton); //add Stay button to JPanel
		stayButton.setBounds(BUTTON_LEFT, BUTTON_TOP + BUTTON_HEIGHT_INC,
				100, 30);
		stayButton.addActionListener(this);


		pack();
		getContentPane().add(view); //add JPanel to JFrame (window)
		getRootPane().setDefaultButton(hitButton);
		view.setVisible(true);
	
    }
    
    /**
	 * Respond to a button click (on either the "Hit Me" button
	 * or the "Stay" button).
	 * @param e the button click action event
	 */
	public void actionPerformed(ActionEvent e) 
	{

		if (e.getSource().equals(hitButton)) //Hit me!
		{   
			myGame.hitMe(myGame.getUserHand());	//call game hitme()
			view.updatePlayerCards(); //Draw players new hand
			if(myGame.UserBusts()||myGame.UserBlackJack()) //check if game is over
			{
				String dPoints = myGame.pointTotal(myGame.getDealerHand()) + "";
				boolean covered = false;
				view.updateDealerCards(dPoints, covered); // show dealer hand
				playAgain();
			}	
		
		} 
		else if (e.getSource().equals(stayButton)) //stay!
		{
			 boolean covered = false;
			 String dPoints = "";
			getRootPane().setDefaultButton(hitButton);
			if(myGame.UserBlackJack())
			{
				dPoints = myGame.pointTotal(myGame.getDealerHand()) + "";
				view.updateDealerCards(dPoints, covered);
			}
			
			// check if dealer has already won
			else if (myGame.dealerHasBlackJack())
			{
				dPoints = myGame.pointTotal(myGame.getDealerHand()) + "";
				view.updateDealerCards(dPoints, covered);
			}
			//make the dealer hand and see who wins
			else
			{
				myGame.makeDealerHand();
				dPoints = myGame.pointTotal(myGame.getDealerHand()) + "";
				view.updateDealerCards(dPoints, covered);
				myGame.whoWins();	
			}
			
			playAgain();		
		}
	}

	/**
	 * Reset the graphics window
	 */
	public void resetGame()
	{

		this.remove(view);
		drawPanel();
		setBounds(300, 125, 750, 500);
		view.repaint();
	}

	/**
	 * See if user wants to play again
	 */
	public void playAgain()
	{
		if(myGame.playAgain())
			resetGame();
		else
			this.dispose(); //close the JFrame (window)
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * This is the main class that creates the GUI version of the BlackJack game.
		 */

				 BlackJack game = new BlackJack();
				 //game.displayGame();
				 game.setBounds(300, 125, 750, 500);
				 game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 game.setVisible(true);
				
				
			
		}


	

}
