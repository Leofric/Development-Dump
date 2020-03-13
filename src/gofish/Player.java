package gofish;

import java.util.ArrayList;

public class Player {

	private ArrayList<String> hand;
	private ArrayList<String> pairs;
	
	
	public Player(){
		hand = new ArrayList<String>();
		pairs = new ArrayList<String>();
	}
	
	public void draw(Deck deck) {
		String value = deck.draw();
		if(value == null) {
			//do nothing
		}
		else {
			hand.add(value);			
		}
	}
	
	public boolean check(String input) {
		boolean valid = false;
		for(int i = 0; i<hand.size(); i++) {
			if(hand.get(i).equals(input)) {
				valid = true;
			}
		}
		return valid;
	}
	
	public String showHand() {
		String playerHand = "";
		for(int i = 0; i<hand.size(); i++) {
			if(i == 0 && hand.size() == 1) {
				playerHand = "["+hand.get(i)+"]";
			}
			else if(i == 0) {
				playerHand = "["+hand.get(i)+", ";
			}
			else if(i == hand.size()-1) {
				playerHand += hand.get(i)+"]"; 
			}
			else {
				playerHand += hand.get(i)+", ";
			}
		}
		return playerHand;
	}
	
	public String AIShowHand() {
		String playerHand = "";
		for(int i = 0; i<hand.size(); i++) {
			if(i == 0 && hand.size() == 1) {
				playerHand = "[?]";
			}
			else if(i == 0) {
				playerHand = "[?, ";
			}
			else if(i == hand.size()-1) {
				playerHand += "?]"; 
			}
			else {
				playerHand += "?, ";
			}
		}
		return playerHand;
	}
	
	public String showPairs() {
		String playerPairs = "[";
		for(int i = 0; i<pairs.size(); i++) {
			if(pairs.size() == 1 || i == pairs.size()-1) {
				playerPairs += pairs.get(i);
			}
			else {
				playerPairs += pairs.get(i)+", ";
			}
		}
		playerPairs +="]";
		return playerPairs;
	}
	
	public int handSize() {
		return hand.size();
	}
	
	public int pairSize() {
		return pairs.size();
	}
	
	public String get(int index) {
		return hand.get(index);
	}
	
	public void remove(int index) {
		hand.remove(index);
	}
	
	public void remove(Object o) {
		hand.remove(o);
	}
	
	public Boolean pair(String pair) {
		Boolean valid = false;
		int count = 0;
		for(int i = 0; i<hand.size(); i++) {
			if(pair.equals(hand.get(i))) {
				count++;
			}
		}
		
		if(count>=2) {
			valid = true;
			pairs.add(pair);
			hand.remove(pair);
			hand.remove(pair);
		}
		else {
			valid = false;
		}
		return valid;
	}
	
	//basically tell me what card you want to steal and from who. if the target has the card then you steal it and make a pair. otherwise you go fish.
	public Boolean steal(String card, Player target) {
		Boolean valid = false;
		for(int i = 0; i<target.handSize(); i++) {
			if(target.get(i).equals(card)) {
				pairs.add(card);
				hand.remove(card);
				target.remove(card);
				valid = true;
				i = target.handSize(); //breaks out of loop early if we find what we need. Saves some time sometimes. 
			}
		}
		return valid;
	}
	
	//this could get complicated.
	//Option 1: dumb - computer picks a card from their hand at random to query from player
	//Option 2: smart - computer memorizes players hand, process of elimination and/or probability. (if computer asks for a 2 and they 'go fish' then they know the other player does not have a 2)(also if the player asks the computer for a 5 the computer knows for a fact there is a 5 in their hand that they can utilize later. the cards never leave their hand unless they make a pair. Would be pretty complicated to do it this way but not impossible. Would need extra code and resources to make this work.
	//Going to go with dumb option for now until the game works, then can work on a smarter algorithm after it is all working and bug free. 
	public String AIMove() {
		return hand.get((int)(Math.random() * hand.size()));		
	}
	
	public String AIPair() {
		String pair = null;
		String card;
		for(int i = 0; i<hand.size()-1; i++) {
			card = hand.get(i);
			for(int j = i+1; j<hand.size(); j++) {
				if(card.equals(hand.get(j))) {
					pair = card;
				}
			}
		}
		if(pair != null) {
			pairs.add(pair);
			hand.remove(pair);
			hand.remove(pair);
		}
		return pair;
	}
	
	
}
