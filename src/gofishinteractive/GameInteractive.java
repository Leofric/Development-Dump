package gofishinteractive;

//screen flicker

import gofish.Deck;

/**
 * This class runs the main game loop for this project. Go Fish. 
 * Combines player/GUI/and custom button classes into runnable game
 * @author alexberthon
 *
 */
public class GameInteractive {
	//global variable to communicate user input across classes
	public static String input;
	
	//global variables to facilitate process locking/pausing
	private static boolean go = true;
	private static Object lock = new Object();
	
	/**
	 * Main game loop, contains the logic and structure of the program.
	 * @param args default java arguments
	 * @throws InterruptedException thread manipulation, lock/release/sleep for game play
	 */
	public static void main(String[] args) throws InterruptedException {		
		//Initialize		
		PlayerInteractive player1 = new PlayerInteractive();
		PlayerInteractive player2 = new PlayerInteractive();
		GUIInteractive ui = new GUIInteractive();
		Deck deck = new Deck();
		boolean turn = true; 	
		deck.shuffle();			
		
		ui.tutorial();
		
		//deal 7 cards to each player
		for(int i = 0; i<7; i++) {
			player1.draw(deck);
			player2.draw(deck);
		}
		
		ui.refresh(player1.getHand(), player1.getPairs(), player2.getHand(), player2.getPairs(), true);
		
		//Game loop: game ends when one player runs out of cards
		while(player1.handSize()>0 && player2.handSize()>0) {
			//Player turn loop: continue until player goes fish or wins
			while(turn && player1.handSize()>0) {
				ui.refresh(player1.getHand(), player1.getPairs(), player2.getHand(), player2.getPairs(), true);
				
				//Wait for player input
				GameInteractive.lock();

				//didn't click on a card
				if(input == null) {
					ui.error(true);
				}
				//makes a pair
				else if(player1.pair(input)) {
					ui.error(false);
					player2.forget(input);
				}
				//steal successful
				else if(player1.steal(input, player2)) {
					ui.error(false);
					ui.steal("player1", input);
					ui.hasCard("player2");
					player2.forget(input);
				}
				//steal failure, go fish
				else {
					ui.error(false);
					ui.steal("player1", input);
					ui.goFish("player2");
					player2.memorize(input);
					player1.draw(deck);
					turn = false;
				}
				ui.clear();
				input = null;
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
				ui.refresh(player1.getHand(), player1.getPairs(), player2.getHand(), player2.getPairs(), false);				
				//attempt auto pair
				if(player2.AIPair() == null) {
					//attempt steal if auto pair fails
					input = player2.AIMove();
					ui.steal("player2",input);

					//check player 1 hand for selected card
					if(!player2.steal(input, player1)) {
						ui.goFish("player1");
						player2.draw(deck);
						turn = false;
					}
					else {
						ui.hasCard("player1");
						player2.forget(input);
					}
					ui.clear();
					input = null;
				}
			}
			//end player 2 turn, start player 1 turn again.
			turn = true;
		}
		ui.refresh(player1.getHand(), player1.getPairs(), player2.getHand(), player2.getPairs(), false);
		ui.win(player1.pairSize(), player2.pairSize());
	}
	
	/**
	 * Locks the process, pausing game until unlock is called
	 */
	public static void lock() {
		go = false;
		while(!go) {
	        try {
	    		synchronized (lock) {
	    			lock.wait();
	    		}
	        } catch (InterruptedException e) {}
	    }
	}

	/**
	 * unlocks the process, allowing game to continue
	 */
	public static void unlock() {
		go = true;
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	
}