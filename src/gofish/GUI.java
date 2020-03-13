package gofish;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUI {
	private Scanner delay;
	private JFrame frame;
	private GridLayout layout;
	private JTextField player2Hand;
	private JTextField player2Pairs;
	private JTextField player2Dialogue;
	private JTextField console;
	private JTextField player1Dialogue;
	private JTextField player1Pairs;
	private JTextField player1Hand;
	
	
	public GUI() {
		delay = new Scanner(System.in);
		
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		layout = new GridLayout(7, 0);
		frame.setLayout(layout);
		
		player2Hand = new JTextField();
		player2Hand.setEditable(false);
		player2Hand.setHorizontalAlignment(JTextField.CENTER);
		
		player2Pairs = new JTextField();
		player2Pairs.setEditable(false);
		player2Pairs.setHorizontalAlignment(JTextField.CENTER);

		player2Dialogue = new JTextField();
		player2Dialogue.setFont(new Font("consolas", Font.PLAIN, 12));
		player2Dialogue.setHorizontalAlignment(JTextField.CENTER);
		
		console = new JTextField();
		console.setFont(new Font("consolas", Font.PLAIN, 12));
		console.setHorizontalAlignment(JTextField.CENTER);
		
		player1Dialogue = new JTextField();
		player1Dialogue.setFont(new Font("consolas", Font.PLAIN, 12));
		player1Dialogue.setHorizontalAlignment(JTextField.CENTER);

		player1Pairs = new JTextField();
		player1Pairs.setEditable(false);
		player1Pairs.setHorizontalAlignment(JTextField.CENTER);
		
		player1Hand = new JTextField();
		player1Hand.setHorizontalAlignment(JTextField.CENTER);
		player1Hand.setEditable(false);
		
		frame.add(player2Hand);
		frame.add(player2Pairs);
		frame.add(player2Dialogue);
		frame.add(console);
		frame.add(player1Dialogue);
		frame.add(player1Pairs);
		frame.add(player1Hand);
		frame.setVisible(true);
	}
	
	//update frame information
	public void refresh(String p1Hand, String p1Pairs, String p2Hand, String p2Pairs){
		player1Hand.setText(p1Hand);
		player1Pairs.setText(p1Pairs);
		player2Hand.setText(p2Hand);
		player2Pairs.setText(p2Pairs);
	}
	
	//display user input error
	public void error(){
		console.setText("Invalid selection. Please try again.");
		delay.nextLine();
		console.setText(null);
	}
	
	//display steal move
	public void steal(String player, String input){
		if(player.equals("player1")) {
			player1Dialogue.setText("Do you have any "+input+"'s ?"); 
		}
		else if(player.equals("player2")) {
			player2Dialogue.setText("Do you have any "+input+"'s ?"); 
		}
		else {
			System.err.println("unrecognized 'String player' input for GUI Method steal(String player, String input)");
		}
		delay.nextLine();
	}
	
	//display player go fish
	public void goFish(String player){
		if(player.equals("player1")) {
			player1Dialogue.setText("Go fish!");
			delay.nextLine();
		}
		else if(player.equals("player2")) {
			player2Dialogue.setText("Go fish!");
			delay.nextLine();
		}
		else {
			System.err.println("unrecognized input for GUI Method goFish(String player)");
		}
		player2Dialogue.setText(null);
		player1Dialogue.setText(null);
	}
	
	//display player has card
	public void hasCard(String player) {
		if(player.equals("player1")) {
			player1Dialogue.setText("Yes..");
			delay.nextLine();
		}
		else if(player.equals("player2")) {
			player2Dialogue.setText("Yes..");
			delay.nextLine();
		}
		else {
			System.err.println("unrecognized input for GUI Method hasCard(String player)");
		}
		player2Dialogue.setText(null);
		player1Dialogue.setText(null);
	}
	
	//clear dialogue
	public void clear() {
		//delay.nextLine();
		player2Dialogue.setText(null);
		player1Dialogue.setText(null);	
	}
	
	//display who won
	public void win(int player1, int player2) {
		if(player1 > player2) {			
			console.setText("You win!");
		}
		else if(player1 < player2) {
			console.setText("You Lose!");
		}
		else {
			console.setText("It's a draw!");
		}
		player1Hand.setText(null);
		player1Dialogue.setText(null);
		player2Hand.setText(null);
		player2Dialogue.setText(null);
	}
	
	//display tutorial information
	public void tutorial() {	
		console.setText("Welcome to Go-Fish!");
		delay.nextLine();
		console.setText("Instructions: Type a card from your hand to steal");
		delay.nextLine();
		console.setText("Instructions: To match, type 'pair' + a card");
		delay.nextLine();
		console.setText("Instructions: The player with the most pairs wins!");
		delay.nextLine();
		console.setText("Drawing Cards...");
		delay.nextLine();
		console.setText(null);
	}
	
	//close the scanner
	public void close() {
		delay.close();
	}
	
}

