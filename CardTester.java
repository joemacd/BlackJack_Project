/**
 * This is a class that tests the Card class.
 */
public class CardTester {

    /**
     * The main method in this class checks the Card operations for consistency.
     *  @param args is not used.
     */
    public static void main(String[] args) {
        /* *** Make sure your output is correct! *** */
        Card c1 = new Card(1,0); //Ace of Spades
        Card c2 = new Card(2,3); //Two of Clubs
        Card c3 = new Card(13,2); //King of Diamonds
        Card c4 = new Card(1,1); //Ace of Hearts
            
        //Testing toString (printing)
        System.out.println(c1 + ", " + c2 + ", " + c3 + ", " +c4);
        System.out.println();
        //Testing getters
        System.out.println(c1 + ": rank is " + c1.getRank() + " : suit is " + c1.getSuit());
        System.out.println(c2 + ": rank is " + c2.getRank() + " : suit is " + c2.getSuit());
        System.out.println(c3 + ": rank is " + c3.getRank() + " : suit is " + c3.getSuit());
        System.out.println(c4 + ": rank is " + c4.getRank() + " : suit is " + c4.getSuit());
        System.out.println();
        
        //Testing equals
        if (c1.equals(c2))
            System.out.println("The rank of " +c1 + " is the same as " + c2);
        else
            System.out.println("The rank of " +c1 + " is not the same as " + c2);
        if (c1.equals(c4))
            System.out.println("The rank of " +c1 + " is the same as " + c4);
        else
            System.out.println("The rank of " +c1 + " is not the same as " + c4);
        if (c2.equals(c3))
            System.out.println("The rank of " +c2 + " is the same as " + c3);
        else
            System.out.println("The rank of " +c2 + " is not the same as " + c3);
        System.out.println();
        
        // Testing compareTo
        if (c3.compareTo(c2) < 0)
            System.out.println("The rank of " +c3 + " is less than the rank of " + c2);
        else
            System.out.println("The rank of " +c3 + " is not less than the rank of " + c2);
        
        if (c3.compareTo(c2) > 0)
            System.out.println("The rank of " +c3 + " is greater than the rank of " + c2);
        else
            System.out.println("The rank of " +c3 + " is not greater than the rank of " + c2);
          
        if (c1.compareTo(c4) == 0)
            System.out.println("The rank of " +c1 + " is the same as the rank of " + c4);
        else
            System.out.println("The rank of " +c1 + " is not the same as rank of " + c4);
    }
}
/* Sample Output:
Ace of Spades, Two of Clubs, King of Diamonds, Ace of Hearts

Ace of Spades: rank is 1 : suit is 0
Two of Clubs: rank is 2 : suit is 3
King of Diamonds: rank is 13 : suit is 2
Ace of Hearts: rank is 1 : suit is 1

The rank of Ace of Spades is not the same as Two of Clubs
The rank of Ace of Spades is the same as Ace of Hearts
The rank of Two of Clubs is not the same as King of Diamonds

The rank of King of Diamonds is not less than the rank of Two of Clubs
The rank of King of Diamonds is greater than the rank of Ace of Spades
The rank of Ace of Spades is the same as the rank of Ace of Hearts  */

