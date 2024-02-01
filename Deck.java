import java.util.Random;
public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck()
	{
		this.cards= new Card [52];
		this.top= 51;
		int counter=0;
		for (int i=1;i<5;i++) //suit
		{
			for (int x=1;x<14;x++) //rank 
			{
				Card c= new Card (i,x);
				cards[counter]=c;
				counter++;
			}
		}
	}
	
	public void shuffle()
	{
		// create a new array named shuffled of length 52
		// choose a random value between 0 and 51
		// place randomly in new array
		// if already placed in array, choose diff random value
        Card [] shuffled= new Card [52];
        int cardDeckCounter=0;
        Random random = new Random();
        while (isArrayFull(shuffled)==false) 
        {
            int randomValue = random.nextInt(52);
            if (shuffled[randomValue]==null) 
            {
            	shuffled[randomValue]=cards[cardDeckCounter];
            	cardDeckCounter++;
            }
        }
        for (int i=0; i<52;i++) 
        {
        	cards[i]=shuffled[i];
        }
   
        
		// shuffle the deck here
	}
	
	public Card deal()
	{
		int returnTop=this.top;
		this.top--;
		return cards[returnTop];
	}
	
	public boolean isArrayFull(Card cardArray[]) 
	{
		boolean isFull=true;
		for (int i=0; i<cardArray.length;i++) 
		{
			if (cardArray[i]==null) 
			{
				isFull=false;
				break;
			}
		}
		
		return isFull;
	}
	

}
