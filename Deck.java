import java.util.*;
/**
 * This class simulates a deck of 52 playing cards.
 * 
 * @author joemacd
 * @version 01/15/2025 (revisited from 2020)
 */
public class Deck
{
    private ArrayList<Card> myDeck; //deck of 52 cards
    private int myTop; //Current top of deck
    
    /**
     * Construct a "deck" of cards containing all 52 different types of cards
     * use two nested loops to create all the combinations
     * shuffle the the "deck", then set myTop to point to the first card
     * in the deck (list)
     */
    public Deck(){
        //Activity 2 code
    	myDeck = new ArrayList<Card>();
    	myTop = 0;
        for (int i = 0; i < 4; i++) {
        	for (int j = 1; j < 14; j++) {
        		Card temp = new Card(j,i);
        		myDeck.add(temp);
        	}
        }
   }
   
    /**
     * Shuffles the Deck by using the built in Collections method shuffle()
     */
    public void shuffle(){
    	ArrayList<Card> temp = new ArrayList<Card>();
    	while (myDeck.size()>0) {
    		int randNum = (int)(Math.random()*myDeck.size());
    		temp.add(myDeck.get(randNum));
    		myDeck.remove(randNum);
    	}
    	myDeck = temp;
        myTop = 0;
    }
    
    /**
     * @return true if all the cards from the deck have been dealt
     */
    public boolean isEmpty(){
    	if (myTop > 51) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * This checks there are still cards in the deck.  if not it shuffles
     * the deck and sets top back to 0.
     * It then returns the top card of the deck and increments topCard
     * @return topcard of deck
     */
    public Card dealCard(){
    	
        //Activity 2 code 
        if(isEmpty() == false) {
        	Card temp = myDeck.get(myTop);
        	myTop++;
        	return temp;
        }
        else {
        	shuffle();
        	Card temp = myDeck.get(myTop);
        	myTop++;
        	return temp;
        }
    }
    
    /**
     * @return current top of the deck
     */
    public int getTop(){
    	return myTop;
    }
    
    /**
     * @see java.lang.Object#toString()
     * @return a string version of the deck
     */
    @Override
    public String toString(){
    	int item = 1;
    	String str = "";
    	for(Card cd: myDeck){
    		if(item % 4 == 0)
    			str += cd.toString() + '\n';
    		else
    			str+= cd.toString()+ ", ";
    		item++;
    	}
    	return str;
    }
    
}