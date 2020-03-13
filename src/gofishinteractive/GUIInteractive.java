package gofishinteractive;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GUIInteractive {
	private JFrame frame;
	private JPanel container;
	private GridLayout frameLayout;
	private FlowLayout cardLayout;
	private JPanel player2Hand;
	private JPanel player2Pairs;
	private JTextField player2Dialogue;
	private JTextField console;
	private JTextField player1Dialogue;
	private JPanel player1Pairs;
	private JPanel player1Hand;	
	private ButtonInteractive card;
			
	//instantiate
	public GUIInteractive() {	
		//UIManager change defaults
		UIManager.put("Button.disabledText", Color.BLACK);
		
		//layouts
		frameLayout = new GridLayout(7, 0);
		cardLayout = new FlowLayout(FlowLayout.CENTER);
		
		//frame
		frame = new JFrame();
		frame.setSize(650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });
		
		//Container
		container = new JPanel();
		container.setLayout(frameLayout);
		container.setBackground(Color.DARK_GRAY);
		container.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });
		
		//p2 hand
		player2Hand = new JPanel();
		player2Hand.setLayout(cardLayout);
		player2Hand.setBackground(Color.DARK_GRAY);
		player2Hand.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });
		
		//p2 pairs
		player2Pairs = new JPanel();
		player2Pairs.setLayout(cardLayout);
		player2Pairs.setBackground(Color.DARK_GRAY);
		player2Pairs.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });

		//p2 dialogue
		player2Dialogue = new JTextField();
		player2Dialogue.setEnabled(false);
		player2Dialogue.setForeground(Color.WHITE);
		player2Dialogue.setBackground(Color.DARK_GRAY);
		player2Dialogue.setDisabledTextColor(Color.WHITE);
		player2Dialogue.setHorizontalAlignment(JTextField.CENTER);
		player2Dialogue.setFont(new Font("consolas", Font.PLAIN, 12));
		player2Dialogue.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		player2Dialogue.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });
		
		//console
		console = new JTextField();
		console.setEnabled(false);
		console.setForeground(Color.WHITE);
		console.setBackground(Color.DARK_GRAY);
		console.setDisabledTextColor(Color.WHITE);
		console.setFont(new Font("consolas", Font.PLAIN, 12));
		console.setHorizontalAlignment(JTextField.CENTER);
		console.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		console.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });
		
		//p1 dialogue
		player1Dialogue = new JTextField();
		player1Dialogue.setEnabled(false);
		player1Dialogue.setForeground(Color.WHITE);
		player1Dialogue.setBackground(Color.DARK_GRAY);
		player1Dialogue.setDisabledTextColor(Color.WHITE);
		player1Dialogue.setHorizontalAlignment(JTextField.CENTER);
		player1Dialogue.setFont(new Font("consolas", Font.PLAIN, 12));
		player1Dialogue.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		player1Dialogue.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });

		//p1 pairs
		player1Pairs = new JPanel();
		player1Pairs.setLayout(cardLayout);
		player1Pairs.setBackground(Color.DARK_GRAY);
		player1Pairs.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });
		
		//p1 hand
		player1Hand = new JPanel();
		player1Hand.setLayout(cardLayout);
		player1Hand.setBackground(Color.DARK_GRAY);
		player1Hand.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				GameInteractive.unlock();
			}
	    });

		//add to frame
		frame.add(container);
		container.add(player2Hand);
		container.add(player2Pairs);
		container.add(player2Dialogue);
		container.add(console);
		container.add(player1Dialogue);
		container.add(player1Pairs);
		container.add(player1Hand);
		frame.setVisible(true);
		container.setVisible(true);
	}
	
	//every time this is called, remove everything from hand and repaint..	
	public void refresh(ArrayList<String>p1Hand, ArrayList<String>p1Pairs, ArrayList<String>p2Hand, ArrayList<String>p2Pairs, boolean p1Turn){
		player1Hand.removeAll();
		player1Pairs.removeAll();
		player2Hand.removeAll();
		player2Pairs.removeAll();
		
		//player 1 hand
		for(int i = 0; i<p1Hand.size(); i++) {
			card = new ButtonInteractive(p1Hand.get(i));
			if(!p1Turn) {
				card.setEnabled(false);
			}
			player1Hand.add(card);
		}
		
		//player 1 pairs
		for(int i = 0; i<p1Pairs.size(); i++) {
			card = new ButtonInteractive(p1Pairs.get(i));			
			card.setEnabled(false);
			player1Pairs.add(card);
		}
		
		//computer hand
		for(int i = 0; i<p2Hand.size(); i++) {
			card = new ButtonInteractive("");
			card.setEnabled(false);
			player2Hand.add(card);
		}	
		
		//computer pairs
		for(int i = 0; i<p2Pairs.size(); i++) {
			card = new ButtonInteractive(p2Pairs.get(i));			
			card.setEnabled(false);
			player2Pairs.add(card);
		}
		
		//refresh
		container.setVisible(false);
		container.setVisible(true);
	}
		
	//display user input error
	public void error(boolean error){
		if(error) {
			console.setText("Invalid selection. Please try again.");
		}
		else {
			console.setText(null);
		}
	}
	
	//display dialogue steal move
	public void steal(String player, String input) throws InterruptedException{
		if(player.equals("player1")) {
			player1Dialogue.setText("Do you have any "+input+"'s ?");
			Thread.sleep(250);
		}
		else if(player.equals("player2")) {
			player2Dialogue.setText("Do you have any "+input+"'s ?"); 
			GameInteractive.lock();
		}
		else {
			System.err.println("unrecognized 'String player' input for GUI Method steal(String player, String input)");
		}
	}
	
	//display player dialogue go fish
	public void goFish(String player) throws InterruptedException{
		if(player.equals("player1")) {
			player1Dialogue.setText("Go fish!");
			Thread.sleep(250);
		}
		else if(player.equals("player2")) {
			Thread.sleep(250);
			player2Dialogue.setText("Go fish!");
			Thread.sleep(300);
		}
		else {
			System.err.println("unrecognized input for GUI Method goFish(String player)");
		}
		player2Dialogue.setText(null);
		player1Dialogue.setText(null);
	}
	
	//display player dialogue has card
	public void hasCard(String player) throws InterruptedException {
		if(player.equals("player1")) {
			player1Dialogue.setText("Yes..");
			Thread.sleep(250);
		}
		else if(player.equals("player2")) {
			Thread.sleep(250);
			player2Dialogue.setText("Yes..");
			Thread.sleep(250);
		}
		else {
			System.err.println("unrecognized input for GUI Method hasCard(String player)");
		}
		player2Dialogue.setText(null);
		player1Dialogue.setText(null);
	}
	
	//clear dialogue
	public void clear() {
		player2Dialogue.setText(null);
		player1Dialogue.setText(null);	
	}
	
	//display winner
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
		player1Dialogue.setText(null);
		player2Dialogue.setText(null);
	}
	
	//display tutorial information
	public void tutorial() {
		console.setText("Welcome to Go-Fish! Click anywhere to continue.");
		GameInteractive.lock();
		console.setText("Instructions: Click on a card from your hand to play it");
		GameInteractive.lock();
		console.setText("Instructions: If you have 2 of the same card you make a pair");
		GameInteractive.lock();
		console.setText("Instructions: The player with the most pairs wins!");
		GameInteractive.lock();
		console.setText("Drawing Cards...");
		GameInteractive.lock();
		console.setText(null);
	}
}