package gofish;

import java.util.ArrayList;

public class Deck {
	private ArrayList<String> deck;

	public Deck(){
		deck = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i<4; i++) {
			for(int j = 2; j<=14; j++) {
				if(j == 11) {
					temp = "J";
				}
				else if(j == 12) {
					temp = "Q";
				}
				else if(j == 13) {
					temp = "K";
				}
				else if(j == 14) {
					temp = "A";
				}
				else {
					temp = ""+j+"";
				}
				deck.add(temp);
			}
		}
	}
	
	public void print() {
		for(int i = 0; i<deck.size(); i++) {
			if(i == 0) {
				System.out.print("["+deck.get(i)+", ");
			}
			else if(i == deck.size()-1) {
				System.out.print(deck.get(i)+"]");
			}
			else {
				System.out.print(deck.get(i)+", ");
			}
		}
	}
	
	public void shuffle() {	
		ArrayList<String> temp = new ArrayList<String>();
		while(deck.size() != 0){
			int index = (int)(Math.random() * (deck.size()));
			String value = deck.remove(index);
			temp.add(value);			
		}
		deck = temp;
	}
	
	public String draw() {
		String value = "";
		if(deck.size()>0) {
			value = deck.remove(0);
		}
		else {
			value = null;
		}
		return value;
	}
	
	public int size() {
		return deck.size();
	}
}

