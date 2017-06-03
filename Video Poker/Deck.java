import java.util.Arrays;
/*Janill Lema
 * jl4827
 * This program creates a deck class needed to 
 * create a deck of 52 cards  
 */

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck(){
		// make a 52 card deck here
		this.cards= new Card[52]; 
		//adds card objects to the deck 
		for(int rank=0; rank <13; rank++){
			for (int suit=0; suit < 4; suit++){
				cards[4*rank +suit] = new Card(suit+1, rank+1);
			}
		}
		
		this.top=0; 
	}
	
	public void shuffle(){
		// shuffle the deck here
		//swaps the a card with another random card 
		for (int i = 0; i < 52; i++) {
            int k = i + (int) ((52-i)* Math.random());
            Card temp = cards[k];
            cards[k] = cards[i];
            cards[i] = temp;
		}
            
	}
	
	public Card deal(){
		// deal the top card in the deck
		//keeps track of what the new top card is 
		top = top+1; 
		return cards[top-1];	
	}
	
	//returns the position of current top card 
	public int getTop(){
		return top-1; 
	}
	
	//resets the deck into a new shuffled deck
	public void resetDeck(){
		top=0; 
		this.shuffle();
	}

}
