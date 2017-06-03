/*Janill Lema
 * jl4827
 * This program creates the card class needed to create 
 * card objects to play video poker 
 */
public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		//make a card with suit s and value v
		this.suit=s-1; 
		this.rank=r-1; 
	}
	
	public int compareTo(Card c){
		//compare based off rank 
		int answer=0;
	 	if ( this.rank < c.rank)
	 		answer=-1;
	 	if ( this.rank > c.rank)
	 		answer=1;
	 	//if same suit, compare based off rank 
	 	if( this.suit == c.suit && this.rank < c.rank )
	 		answer= -1; 
	 	if( this.suit == c.suit && this.rank > c.rank )
	 		answer= 1; 
	 	
	 	return answer;
	}
	
	public String toString(){
		// use this method to easily print a Card object
		String[] rankName = {"Ace", "Two", "Three", "Four", "Five", "Six", 
				"Seven", "Eight", "Nine", "Ten","Jack", "Queen", "King"};
		
		String[] suitName = {"clubs", "spades", "hearts", "diamonds"};
			
		//rank and suit of card same as position of str
		String s = rankName[rank] + " of "+ suitName[suit] ; 
		
		return s; 
	}
	
	//returns the rank of the card 
	public int getRank(){
		return rank+1; 
	}
	//returns the suit of the card 
	public int getSuit(){
		return suit+1; 
	}

}
