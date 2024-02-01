import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class Game 
{
	
	private Player p;
	private Deck cards;

	public Game(String[] testHand)
	{
		this.p= new Player();
		this.cards= new Deck();
		convtoArrayList(testHand);		
	}
	private void convtoArrayList(String[]testHand) 
	{
		for (int i=0;i<testHand.length;i++) 
		{
			int rank = Integer.parseInt(testHand[i].substring(1));
			char initialSuit=testHand[i].charAt(0);
			//  1= spade
			// 2= diamond
			// 3= club
			// 4= heart
			char arrSuits[]= {'n','s','d','c','h'};
			int suit=0;
			for (int x=0;x<5;x++) 
			{
				if (initialSuit==arrSuits[x]) 
				{
					suit=x;
				}
			}
			Card c= new Card (suit,rank);
			p.addCard(c);		
		}
	}
	public Game()
	{
		this.p= new Player();
		this.cards= new Deck();		
	}
	public void play()
	{
		cards.shuffle();
		Scanner s= new Scanner(System.in);
		System.out.println("How many tokens would you like to bet?");
		double tokens= s.nextDouble();
		p.bets(tokens); //adjusts the amount of tokens total
		int counter=0;
		if (p.getHand().size()==0) 
		{
			while (counter<5)
			{
				p.addCard(cards.deal());
				counter++;
			}
		}
		System.out.println("These are your cards: ");
		p.handToString();
		System.out.println("Would you like replace any cards? If yes, type 1, if no, type 0");
		int response= s.nextInt();
		if (response==1) 
		{
			System.out.println("Please state the amount of cards you would like to replace");
			int replace= s.nextInt();
			while (replace<1|| replace>5) 
			{
				System.out.println("Sorry that was an invalid amount, please enter a different amount");
				replace= s.nextInt();
			}
			System.out.println("Here are the cards as a reminder");
			p.handToString();
			int arrIndexTrack[]= new int [replace];
			for (int i=0;i<replace;i++) 
			{
				System.out.println("Card #1 is index 0, card #2 index 1, card #3 index 2 etc.");
				System.out.println("Please specify the index number of the card you would like to replace.");
				int tempIndex= s.nextInt();
				if (i!=0) 
				{
					for (int x=0;x<i;x++) 
					{
						while (arrIndexTrack[x]==tempIndex) 
						{
							System.out.println("You can't replace that card, provide a different index number");
							tempIndex= s.nextInt();
						}
					}
				}
				arrIndexTrack[i]= tempIndex;
				p.addSpecificIndex(tempIndex, cards.deal()); 
			}		
		}
		System.out.println("Your hand contains the following cards: ");
		p.handToString();
		System.out.println("Your hand is scored as a: "+checkHand(p.getHand()));
		double odds= p.getBet()*getPayOut();
		p.winnings(odds);
		System.out.println("Your bankroll now has: "+p.getBankroll());
	}
	public String checkHand(ArrayList<Card> hand)
	{
	String playerHand="No pair";
	if (isRoyalFlush(hand)) 
	{
		playerHand= "Royal Flush";
	}
	else if (isStraightFlush(hand)) 
	{
		playerHand= "Straight Flush";
	}
	else if (isFourofaKind(hand)) 
	{
		playerHand= "Four of a Kind";
	}
	else if (isFullHouse(hand)) 
	{
		playerHand= "Full House";
	}
	else if (isFlush(hand)) 
	{
		playerHand= "Flush";
	}
	else if (isStraight(hand)) 
	{
		playerHand= "Straight";
	}
	else if (isThreeofaKind(hand)) 
	{
		playerHand= "Three of a Kind";
	}
	else if (isTwoPairs(hand)) 
	{
		playerHand= "Two Pairs";
	}
	else if (isOnePair(hand)) 
	{
		playerHand= "One Pair";
	}
	return playerHand;
	}
	private int getPayOut()
	{
		int PayOut=0;
		String arrHands[]= {"Royal Flush","Straight Flush","Four of a Kind","Full House","Flush","Straight","Three of a Kind","Two Pairs","One Pair"};
		int payOuts[]= {250,50,25,6,5,4,3,2,1};
		String hand= checkHand(p.getHand());
		for (int i=0;i<arrHands.length;i++) 
		{
			if (hand.equals(arrHands[i])) 
			{
				PayOut=payOuts[i];
				break;
			}
		}
		return PayOut;
	}
	private boolean isRoyalFlush(ArrayList<Card> hand) 
	{
		boolean isRoyalFlush=false;
		if (isStraightFlush(hand)==true) 
		{
			int arrRank[]= new int[5];
			for (int x=0;x<5;x++) 
			{
				arrRank[x]= getSpecificIndex(x, hand).getRank();
			}
			Arrays.sort(arrRank);
			if (arrRank[0]==1 && arrRank[4]==13) 
			{
				isRoyalFlush=true;
			}
			
		}
		return isRoyalFlush;
	}
	private boolean isStraightFlush(ArrayList<Card> hand)
	{
		  return isStraight(hand) && isFlush(hand);
	}
	private boolean isFourofaKind(ArrayList<Card> hand)
	{
		boolean isFour=false;
		for (int i=0;i<5;i++) 
		{
			for (int x=0;x<5;x++) 
			{
				Card iCard= getSpecificIndex(i,hand);
				Card xCard= getSpecificIndex(x,hand);
				if (xCard.compareTo(iCard)==0&&x!=i) 
				{
					int checkRank= xCard.getRank();
					int check4=0;
					int arrRank[]= new int [5];
					for (int y=0;y<5;y++) 
					{
						arrRank[y]= getSpecificIndex(y,hand).getRank();
						if (arrRank[y]==checkRank) 
						{
							check4++;
						}
						if (check4==4) 
						{
							isFour=true;
							break;
						}
					}
					
				}
			}
		}
		return isFour;
	}
	private boolean isFullHouse(ArrayList<Card> hand) 
	{
		boolean isFull= false;
		if (isFourofaKind(hand)==false&&isThreeofaKind(hand)==true) 
		{
			int checkRank= rankThreeofaKind(hand);
			int arrRank[]= new int [5];
			int arrNotThree[]= new int [2];
			int notThreeCount=0;
			for (int y=0;y<5;y++) 
			{
				arrRank[y]= getSpecificIndex(y,hand).getRank();
				if (arrRank[y]!=checkRank&&notThreeCount<2) 
				{
					arrNotThree[notThreeCount]=arrRank[y];
					notThreeCount++;
				}
			}
			if (arrNotThree[0]==arrNotThree[1]) 
			{
				isFull=true;
			}
						
		}
				
		return isFull;
	}
	private boolean isFlush(ArrayList<Card> hand)
	{
		boolean isFlush=true;
		Card compare= getSpecificIndex(0,hand);
		for (int i=1;i<5;i++) 
		{
			if (compare.compareSuit(getSpecificIndex(i,hand))!=0) 
			{
				isFlush=false;
				break;
			}
		}
		return isFlush;
	}
	private boolean isStraight(ArrayList<Card> hand)
	{
		boolean isStraight=true;
		int arrRank[]= new int[5];
		for (int x=0;x<5;x++) 
		{
			arrRank[x]= getSpecificIndex(x,hand).getRank();
		}
		Arrays.sort(arrRank);
		for (int x=0;x<4;x++) 
		{
			int tempSum= arrRank[x]+1;
			if (tempSum!=arrRank[x+1]&& !(arrRank[0]==1&&arrRank[4]==13)) 
			{
					isStraight=false;
					break;
			}
			
		}
		if (arrRank[0]==1&&arrRank[4]==13) 
		{
			for (int i=1;i<4;i++) 
			{
				int tempSum= arrRank[i]+1;
				if (tempSum!=arrRank[i+1]) 
				{
					isStraight=false;
					break;
				}
			}
			
		}
		
		return isStraight;
	}
	private boolean isThreeofaKind(ArrayList<Card> hand) 
	{
		boolean isThree=false;
		for (int i=0;i<5;i++) 
		{
			for (int x=0;x<5;x++) 
			{
				Card iCard= getSpecificIndex(i,hand);
				Card xCard= getSpecificIndex(x,hand);
				if (xCard.compareTo(iCard)==0&&x!=i) 
				{
					int checkRank= xCard.getRank();
					int check3=0;
					int arrRank[]= new int [5];
					for (int y=0;y<5;y++) 
					{
						arrRank[y]= getSpecificIndex(y,hand).getRank();
						if (arrRank[y]==checkRank) 
						{
							check3++;
						}
						if (check3==3) 
						{
							isThree=true;
							break;
						}
					}
					
				}
			}
		}
		return isThree;
	}
	private int rankThreeofaKind(ArrayList<Card> hand) 
	{
		int checkRank=0;
		if (isThreeofaKind(hand)) 
		{
			outerLoop:	for (int i=0;i<5;i++) 
			{
				for (int x=0;x<5;x++) 
				{
					Card iCard= getSpecificIndex(i,hand);
					Card xCard= getSpecificIndex(x,hand);
					if (xCard.compareTo(iCard)==0&&x!=i) 
					{
						checkRank= xCard.getRank();
						int check3=0;
						int arrRank[]= new int [5];
					for (int y=0;y<5;y++) 
					{
						arrRank[y]= getSpecificIndex(y,hand).getRank();
							if (arrRank[y]==checkRank) 
							{
							check3++;
							}	
							if (check3==3) 
							{
							checkRank=xCard.getRank();
							break outerLoop;
							}
						}
					
					}
				}
			}
		
		}
		return checkRank;
	}
	private boolean isTwoPairs(ArrayList<Card> hand) 
	{
		boolean twopair=false;
		boolean pair1=false;
		int repeat=0;
		for (int i=0;i<5;i++) 
		{
			for (int x=0;x<5;x++) 
			{
				Card iCard= getSpecificIndex(i,hand);
				Card xCard= getSpecificIndex(x,hand);
				if (xCard.compareTo(iCard)==0&&x!=i) 
				{
					pair1=true;
					repeat= xCard.getRank();
					break;
				}
			}
		}
		if (pair1==true) 
		{
			for (int i=0;i<5;i++) 
			{
				for (int x=0;x<5;x++) 
				{
					Card iCard= getSpecificIndex(i,hand);
					Card xCard= getSpecificIndex(x,hand);
					if (xCard.compareTo(iCard)==0&&x!=i&&xCard.getRank()!=repeat) 
					{
						twopair=true;
						break;
					}
				}
			}
		}
		return twopair;
	}
	private boolean isOnePair(ArrayList<Card> hand)
	{
		boolean pair=false;
		for (int i=0;i<5;i++) 
		{
			for (int x=0;x<5;x++) 
			{
				Card iCard= getSpecificIndex(i,hand);
				Card xCard= getSpecificIndex(x,hand);
				if (xCard.compareTo(iCard)==0&&x!=i) 
				{
					pair=true;
					break;
				}
			}
		}
		return pair;
	}
    private Card getSpecificIndex(int index,ArrayList<Card> hand ) 
    {
    	Card c=hand.get(index);
    	return c;
    }
	
}