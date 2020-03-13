package gofish;

import java.util.Scanner;


public class Game {
	
	public static void main(String[] args) {		
		//Initialize
		Scanner in = new Scanner(System.in); 	//input scanner
		Player player1 = new Player();
		Player player2 = new Player();
		String[] format = null; 				//format and control user input
		Deck deck = new Deck();
		boolean turn = true; 					//flag for player turn, allows multiple player per player turn		
		String input = ""; 						//store user input
		GUI ui = new GUI();						//GUI
		
		deck.shuffle();			
		
		ui.tutorial();
		
		//deal 7 cards to each player
		for(int i = 0; i<7; i++) {
			player1.draw(deck);
			player2.draw(deck);
		}
		
		ui.refresh(player1.showHand(), player1.showPairs(), player2.AIShowHand(), player2.showPairs());

		//Game loop, ends when any player is out of cards
		while(player1.handSize()>0 && player2.handSize()>0) {
			ui.refresh(player1.showHand(), player1.showPairs(), player2.AIShowHand(), player2.showPairs());
			
			//Loop until player goes fish / provides valid input
			while(turn && player1.handSize()>0) {
				ui.refresh(player1.showHand(), player1.showPairs(), player2.AIShowHand(), player2.showPairs());					
				
				//gather user input
				input = in.nextLine();
					
				//test user input
				try {
					//pair command check validity
					if(input.contains("pair")) {
						format = input.split("pair ");
						input = format[1];	
						if(!player1.pair(input)) {
							ui.error();
						}
					}
					//steal command check validity
					else {
						if(player1.check(input)) {
							ui.steal("player1", input);

							//Attempt steal
							if(!player1.steal(input, player2)) { 
								ui.goFish("player2");
								player1.draw(deck);
								turn = false;
							}
							else {
								ui.hasCard("player2");
							}
							ui.clear();
						}
						else {
							ui.error();
						}
					}	
				}
				//Catch bad input / errors
				catch(IllegalArgumentException e) {
					ui.error();
				}
			}
			
			//check if game over
			if(player1.handSize()>0 && player2.handSize()>0) {
				turn = true;			
			}
			else {
				turn = false;
			}
			
			//start player 2 turn.
			while(turn && player2.handSize()>0) {
				ui.refresh(player1.showHand(), player1.showPairs(), player2.AIShowHand(), player2.showPairs());
				
				//attempt auto pair
				if(player2.AIPair() == null) {
					//attempt steal if auto pair fails
					input = player2.AIMove();
					ui.steal("player2",input);

					//check player 1 hand for selected card
					if(!player2.steal(input, player1)) { //if i ever get around to making more than 2 player game, this extra functionality will come in handy.
						ui.goFish("player1");
						player2.draw(deck);
						turn = false;
					}
					else {
						ui.hasCard("player1");
					}
					ui.clear();
				}
			}
			//end player 2 turn, start player 1 turn again.
			turn = true;
		}
		ui.win(player1.pairSize(), player2.pairSize());
		ui.close();
		in.close();
	}
}