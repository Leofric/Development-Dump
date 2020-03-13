package gofishinteractive;
import gofish.Deck;
import gofish.NaturalOrderComparator;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerInteractive {

	private ArrayList<String> hand;
	private ArrayList<String> pairs;
	private ArrayList<String> memory;
	
	NaturalOrderComparator<String> comparator = new NaturalOrderComparator<String>(false);
	
	//instantiate
	PlayerInteractive(){
		hand = new ArrayList<String>();
		pairs = new ArrayList<String>();
		memory = new ArrayList<String>();
	}
	
	//draw a card from the deck
	public void draw(Deck deck) {
		String value = deck.draw();
		if(value != null) {
			hand.add(value);
		}
	}
	
	//probably insecure
	public ArrayList<String> getHand() {
		Collections.sort(hand, comparator);
		return hand;
	}
	
	//most definitely insecure
	public ArrayList<String> getPairs(){
		return pairs;
	}
			
	//return the size of hand array
	public int handSize() {
		return hand.size();
	}
	
	//return size of pair array
	public int pairSize() {
		return pairs.size();
	}
	
	//return card data
	public String get(int index) {
		return hand.get(index);
	}

	//remove card from hand based on index
	public void remove(int index) {
		hand.remove(index);
	}
	
	//remove object from hand
	public void remove(Object o) {
		hand.remove(o);
	}
	
	//attempt pair move (player 1)
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
	public Boolean steal(String card, PlayerInteractive target) {
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

	//adds player data to memory
	public void memorize(String card) {
		boolean duplicate = false;
		for(int i = 0; i<memory.size(); i++) {
			if(card.equals(memory.get(i))){
				duplicate = true;
			}
		}
		if(!duplicate) {
			memory.add(card);
		}
	}
	
	//removes player data from memory
	public void forget(String card) {
		memory.remove(card);
	}
	
	//Calculates AI's 'best' move
	public String AIMove() {
		String card = null;
		String play = null;
		for(int i = 0; i<hand.size(); i++) {
			card = hand.get(i);
			for(int j = 0; j<memory.size(); j++) {
				if(card.equals(memory.get(j))) {
					play = memory.get(j);
					memory.remove(j);
					j = memory.size();
					i = hand.size();
				}
			}
		}
		
		if(play == null) {
			play = hand.get((int)(Math.random() * hand.size()));
		}
		return play;
	}
	
	//Auto-pairs AI hand
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
