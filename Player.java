import java.util.ArrayList;
import java.util.Scanner;
public class Player 
{
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	// you may choose to use more instance variables
		
	public Player()
	{		
	    this.bankroll=50;
	    this.bet=0;
	    this.hand= new ArrayList<>();
	}

	public void addCard(Card c){
	    hand.add(c);
	}

	public void removeCard(Card c)
	{
		for (int i=0;i< hand.size();i++) 
		{
			if (hand.get(i).overallCompare(c)==0) 
			{
				hand.remove(i);
			}
		}
	}
		
    public void bets(double amt)
    {
    	Scanner s= new Scanner (System.in);
    	if (amt>0&&amt<=5) 
    	{
         bankroll=bankroll-amt;
         this.bet= amt;
        }
    	else 
    	{
    		while (true) 
    		{
    			System.out.println("Please enter a different amount, you can only bet between 1-5 tokens");
    			double newAmt= s.nextDouble();
    			if (newAmt>0&&newAmt<=5) 
    			{
    				bankroll=bankroll-newAmt;
    				this.bet=newAmt;
    				break;
    			}
    		}
    	}
    }

    public void winnings(double odds)
    {
    	bankroll= bankroll+odds;
            //	adjust bankroll if player wins
    }

    public double getBankroll()
    {
            return bankroll;
    }
    public double getBet() 
    {
    	return bet;
    }
    public void handToString() 
    {
    	int counter=0;
    	while (counter<5) 
    	{
    		Card c= this.hand.get(counter);
    		System.out.println(c.toString());
    		counter++;
    	}
    }
    public void addSpecificIndex(int index, Card c) 
    {
    	Card temp=hand.get(index);
    	removeCard(temp);
    	hand.add(index, c);
    }
    public Card getSpecificIndex(int index) 
    {
    	Card c=hand.get(index);
    	return c;
    }
    public ArrayList<Card> getHand()
    {
    	return this.hand;
    }
}


