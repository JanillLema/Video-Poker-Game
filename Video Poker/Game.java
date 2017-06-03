import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
/*Janill Lema
 * jl4827
 * This program creates a game class needed to 
 * play video poker. 
 */
public class Game {
	
	private Player p;
	private Deck cards;
	private int payout; 
    private Scanner input; 
	
	public Game(String[] testHand){

		p= new Player(); 
		cards= new Deck();
		payout=0; 
		input = new Scanner(System.in);
		//shuffle before distribute cards 
		cards.shuffle();
		int r=0; 
		int s=0;
        //breaks elements of string array into components
		//creates card objects for hand using components
		for(int i=0; i<testHand.length; i++){
			//gathers suit info 
			char x= testHand[i].charAt(0);
			if(x=='c'){
				s=1; 
			}
			else{
				if(x=='s'){
					s=2; 
				}
				else{
					if(x=='h'){
						s=3; 
					}
					else{
						if(x=='d'){
							s=4; 
						}
					}
				}
			}
			
            //gathers rank info
			if(i<testHand.length){
				if (testHand[i].length()==3){
					r=Integer.parseInt(testHand[i].substring(1,3));
				}
				else{
					r=Integer.parseInt(testHand[i].substring(1,2));
				}
			}
			//adds created cards to players hand 
			p.getHand().add(new Card(s,r)); 

		}	
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		p= new Player(); 
		cards= new Deck();
		payout=0; 
		input = new Scanner(System.in);
		//shuffle before distribute cards 
		cards.shuffle();
		//create 5 hard hand 
		for(int i=5; i>0; i--){
			p.addCard(cards.deal());
		}
	}
	
	public void play(){
		double balance=0; 
		System.out.println("~~~~~WECOME TO VIDEO POKER~~~~~~~");
		System.out.println("Enter '1' to play else enter '0'");
		int again=input.nextInt();
		if(again==1){
			System.out.println("Enter the amount of money you would like "
					+ "to start off with: "); 
			balance = input.nextDouble(); 
			p.winnings(balance);
			System.out.println("Balance: "+ p.getBankroll());
		}
		
		//game played as long as player wants to 
		while(again==1){
			int k=0; 
			int j=0; 
			int z=0; 
			//ensures that never run out of cards from deck
			//shuffles deck 
		    if(cards.getTop()>40){
		    	cards.resetDeck();
		    }
			double bet=0; 
			System.out.println("How much would you like to bet?"
					+ "Choose 1-5: ");
			bet=input.nextDouble(); 
			p.bets(bet);
			System.out.println("Hand:" + p.getHand());
			System.out.println("Hand Score:"+ 
			this.checkHand(p.getHand()));
			System.out.println("Associated Hand Payout: "
			+this.getPayout());
			System.out.println("Potential Gain: "+
			this.getPayout()*bet); 
			System.out.println("How many cards would you "
					+ "like to remove? Choose 0-5: ");
			k=input.nextInt();
			
			//removes and adds cards to players hand 
			while(j<k&&k!=0){
				System.out.println("Enter the position "+
						"of the card you wish to replace. Choose 1-5:");
				System.out.println("CLICK ENTER"); 
				z=input.nextInt()-1; 
				p.getHand().remove(z); 
				p.getHand().add(z, cards.deal());
				j++; 
			}
			System.out.println("Hand: "+p.getHand());
			//checks hand 
			System.out.println("Hand Score: "+ 
			this.checkHand(p.getHand()));
			System.out.println("Bet: "+bet);
			System.out.println("Gain: " + this.getPayout()*bet);
		    p.winnings(this.getPayout() * bet);	
			System.out.println("Balance: "+ p.getBankroll());
			//removes all cards from hand 
			p.resetHand();
			//adds 5 new cards to hand 
			for(int i=5; i>0; i--){
				p.addCard(cards.deal());
			}
			System.out.println("Would You Like To Play Again, "
					+ "Enter 1 for Yes "
					+ "or 0 for No");
		    again=input.nextInt();	
		    System.out.println("********************"
		    		+ "**************");
		}

	}
	
	//checks hand of the player 
	public String checkHand(ArrayList<Card> hand){
		//copies  array into new array 
		ArrayList<Card> pHand=new ArrayList<Card>(); 	
		for (Card elem: hand){
			pHand.add(elem); 
		}
		//sorts the hand to make it easier to compare
		Collections.sort(pHand);
		String handType= ""; 
		//determines hand type an associated payout 
		if(this.royalFlush(pHand)){
			handType="ROYAL FLUSH";
			payout=250; 
		}
		else{
			if(this.straightFlush(pHand)){
				handType="STRAIGHT FLUSH";
				payout=50; 
			}
			else{
				if(this.fourKind(pHand)){
					handType="FOUR OF A KIND"; 
					payout=25; 
				}
				else{
					if(this.fullHouse(pHand)){
						handType="Full House"; 
						payout=6; 
					}
					else{
						if(this.flush(pHand)){
							handType="Flush"; 
							payout=5; 
						}
						else{
							if(this.straight(pHand)){
								handType="STRAIGHT"; 
								payout=4; 
							}
							else{
								if(this.threeOfAKind(pHand)){
									handType="THREE OF A KIND"; 
									payout=3; 
								}
								else{
									if(this.twoPairs(pHand)){
										handType="TWO PAIRS"; 
										payout=2; 
									}
									else{
										if(this.onePair(pHand)){
										handType="ONE PAIR"; 
										payout=1; 
										}
										else{
											handType="NO PAIR"; 
											payout=0; 
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return handType;
	}
	
	//returns payout based of hand type 
	public int getPayout(){
		return payout; 
	}
	
	//checks if hand is a one pair 
	public boolean onePair(ArrayList<Card> hand){
		boolean x= false; 
		for (int i=4; i>0; i--){
			Card c= hand.get(i); 
			Card v= hand.get(i-1); 
			if (c.getRank()==v.getRank())
				x=true; 	
		}
		return x; 
	}
	//checks if hand is two pairs 
	public boolean twoPairs(ArrayList<Card> hand){
		boolean x= false; 
		for (int i=4; i>2; i--)
		{
			Card c= hand.get(i); 
			Card v= hand.get(i-1); 
			Card z= hand.get(i-2);
			Card g= hand.get(i-3);
			//checks that 2 pairs of cards have the same rank 
			if (c.getRank()==v.getRank()&& z.getRank()==g.getRank()){
				x=true; 
			} 
		}
		return x;
	}
	
	//checks if hand is three of a kind 
	public boolean threeOfAKind(ArrayList<Card> hand){
		boolean x= false; 
		for (int i=4; i>1; i--){
			Card c= hand.get(i); 
			Card v= hand.get(i-1); 
			Card g= hand.get(i-2);
			//checks that 3 cards have the same rank 
			if (c.getRank()==v.getRank()&&v.getRank()==g.getRank()){
				x=true; 
			}
		}
		return x; 
	}
	
	//checks if hand is a straight
	public boolean straight(ArrayList<Card> hand){
		boolean inc= false;
		boolean incOne=true; 
		boolean incTwo=true; 
		
		Card ace=hand.get(0);
		Card king=hand.get(4); 
		//checks that cards are in order 
		for(int i= 4; i>0; i--){
			Card c= hand.get(i); 
			Card v= hand.get(i-1); 
			if(c.getRank()-v.getRank()!=1){
				incOne=false; 
			}
		}
        //checks that cards are in order and that ace is first
		//and kind is last in hand 
		
		for(int z= 4; z>1; z--){
			Card a= hand.get(z); 
			Card b= hand.get(z-1); 
			if(a.getRank()-b.getRank()!=1 || ace.getRank()!=1 ||
					king.getRank()!=13){
				incTwo=false; 
			}		
		}
		if (incOne==true || incTwo==true)
			inc=true;
		
		return inc; 
	}
	
	//checks if hand is a flush 
	public boolean flush(ArrayList<Card> hand){
		boolean x=false; 
		//checks that suits are equal 
		for (int i=4; i>3; i--){
			Card c= hand.get(i); 
			Card v= hand.get(i-1); 
			Card z= hand.get(i-2);
			Card g= hand.get(i-3);
			Card k= hand.get(i-4);
			if (c.getSuit()==v.getSuit()&& v.getSuit()==z.getSuit()
					&& z.getSuit()==g.getSuit() && g.getSuit()==k.getSuit())
				x=true; 
		}
		return x; 		
	}
	
	//checks if hand is a full house 
	public boolean fullHouse(ArrayList<Card> hand){
		boolean x=false; 
		if(this.threeOfAKind(hand) && this.twoPairs(hand))
			x=true; 
		return x; 
	}
	
	//checks if hand is four of a kind 
	public boolean fourKind(ArrayList<Card> hand){
		boolean x= false; 
		//checks that there are 4 equal cards 
		for (int i=4; i>2; i--)
		{
			Card c= hand.get(i); 
			Card v= hand.get(i-1); 
			Card z= hand.get(i-2);
			Card g= hand.get(i-3);
			if (c.getRank()==v.getRank()&& v.getRank()==z.getRank()
					&& z.getRank()==g.getRank())
				x=true; 
		}
		return x; 
	}
	
	//checks if hand is a straight flush 
	public boolean straightFlush(ArrayList<Card> hand){
		boolean x=false;
		if(this.straight(hand) && this.flush(hand))
			x=true; 
		return x;
	}
	
	//checks if hand is a royal flush 
	public boolean royalFlush(ArrayList<Card> hand){
		boolean y= false; 
		boolean x= false; 
		Card ace= hand.get(0);
		Card ten=hand.get(1); 
		Card jack= hand.get(2); 
		Card queen=hand.get(3); 
		Card king= hand.get(4);
		if(ace.getRank() == 1 && ten.getRank()== 10 &&
				jack.getRank()==11 && queen.getRank()==12
				&& king.getRank()==13){
			y=true; 
		}
		else 
			y=false; 
		if(y && this.flush(hand))
			x=true; 
		
		return x; 
	}
	
}
