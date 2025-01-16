
/**
 * This class represents a playing card
 * 
 * @author joemacd
 * @version 01/15/2025 (revisited from 2020)
 */
public class Card implements Comparable<Card>
{
    public static final int SPADES = 0;
    public static final int HEARTS = 1;
    public static final int DIAMONDS = 2;
    public static final int CLUBS = 3;
    
    private int rank; //value 1 - 13
    private int suit; // 0 - 3
    
    /**
     * Constructor for objects of class Card
     */
    public Card(int r, int s)
    {
        //Activity 1 code
    	rank = r;
    	suit = s;
    }

    /**
     * Give the numerical suit of the card 
     * @return   the suit Spades = 0 Hearts = 1, Diamonds = 2, Clubs = 3
     */
      public int getSuit()
      {
    	  //Activity 1
    	  return suit;
      }
      
   
  /**
     * Give the numerical rank of the card 1 - 13
     * @return   the rank
     */ 
    public int getRank()
    {
    	//Activity 1
    	return rank;
    }
    
    /* 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * determine this card's rank - card2's rank
     * @param card2  card to compare to this card
     * @return <0 of this card's rank is less than card2's rank,
     * 		   >0 if this card's rank is greater than card2's rank,
     * 	 	   0 if this card's rank equals card2's rank
     */
    @Override
	public int compareTo(Card card2) {
        
        int diff = this.rank - card2.rank;
		
		return diff;//activity 1
	}

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     * @param obj - card to compare to this card
     * @return - true if both card's rank are equal
     */
    @Override
    public boolean equals(Object obj)
    {
    	boolean same = false;
    	if(obj instanceof Card)
    		{
    			Card card2= (Card)obj;
    			//Activity 1
    			if(card2.getRank() == this.rank) {
    				same = true;
    			}
    			
    		}
    	else
    		throw new IllegalArgumentException("not a Card object");
    	
    	return same;
    }
  
     /** 
     * @see java.lang.Object#toString()
     * Create a string version of the card
     * Used for printing a card (rank of suit)
     * 
     * @return string description of the card
     */
    @Override
    public String toString()
     {
         String[]rankName = {"Ace","Two","Three","Four","Five","Six","Seven",
             "Eight","Nine","Ten","Jack","Queen","King"};
         
         String[]suitType ={"Spades","Hearts","Diamonds","Clubs"};
         
         return "The " + rankName[rank-1] + " of " + suitType[suit];//Activity 1;
      }

	
  //DO NOT Change anything below this line           
	 /**
     * Give the suit of the card as a string
     * Used to read the card gif images
     * 
     * @return   the suit Spades = 0 Hearts = 1, Diamonds = 2, Clubs = 3
     */
      public String getStringSuit()
      {
    	  String[]suitType ={"Spades","Hearts","Diamonds","Clubs"};
          return suitType[suit];
      }
      
      /**
       * Give the rank of the card 1 - 13 as a string name
       * Used to read the card gif images
       * 
       * @return   the rank
       */ 
      public String getStringRank()
      {
      	String[]rankName = {"ace","2","3","4","5","6","7",
                  "8","9","10","jack","queen","king"};
      	
          return rankName[rank-1];
      }
      
  }
   
