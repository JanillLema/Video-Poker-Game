import java.util.ArrayList;
import java.util.Collections;
/*Janill Lema
 * jl4827
 * This program creates the player class needed to create 
 * player objects to play video poker 
 */
public class Player {
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
		
	public Player(){		
	    // create a player here 
		hand=new ArrayList<Card>(); 
		bankroll=0; 
		bet=0; 
	}
	public void addCard(Card c){
	    // add the card c to the player's hand
		hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
		hand.remove(c); 
	}

	//subtracts the players bets from bankroll
	public void bets(double amt){
		bet=amt; 
		bankroll=bankroll-bet; 
	}
	
	//increases the player bankroll if win 
	public void winnings(double odds){
            //	adjust bankroll if player wins
		bankroll= bankroll+odds;
	}
	
    //returns the current bankroll value 
	public double getBankroll(){
            // return current balance of bankroll
		return bankroll; 
	}
	
	//sorts the hand to make it easier to calculate
	//type of hand 
	public void sort(){
		Collections.sort(hand); 
	}

	//returns hand 
	public ArrayList<Card> getHand(){
		return hand; 
	}
	
	//removes all hards from players hand 
	public void resetHand(){
		hand.clear();
		
	}
}


