// add your own banner here

public class Card implements Comparable<Card>
{
	
	private int suit; // use integers 1-4 to encode the suit
	// 1= spade
	// 2= diamond
	// 3= club
	// 4= heart
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		this.suit=s;
		this.rank=r;
	}
	
	//only compares rank
	public int compareTo(Card c)
	{
		int equal=0;
		if (this.rank<c.getRank()) 
		{
			equal=-1;
		}
		if (this.rank>c.getRank()) 
		{
			equal=1;
		}
		return equal;
	}
	//compare suit
	public int compareSuit(Card c) 
	{
		//not of the same suit
		int equal=-1;
		if (this.suit==c.getSuit()) 
		{
			equal=0;
		}
		return equal;
	}
	public int overallCompare(Card c) 
	{
		int equal=-1;
		if (this.suit==c.getSuit()&&this.rank==c.getRank()) 
		{
			equal=0;
		}
		return equal;
	}
	
	public String toString()
	{
		String ranks[]= {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
		String suits[]= {"Spade","Diamond","Club","Heart"};
		String card= ("The suit is: "+suits[this.suit-1]+ " The rank is: "+ranks[this.rank-1]);
		return card;
	}
	public int getRank()
	{
		return rank;
	}
	public int getSuit()
	{
		return suit;
	}

}
