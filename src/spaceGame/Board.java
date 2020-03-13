package spaceGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    
    private Timer testTimer;
    
    private Ship spaceship;
    private Background background1;
    private Background background2;
    
    private String[][] highscore;

    private List<Alien> aliens;
    private boolean ingame;			 //boolean controlling game running vs not running?
    private final int ICRAFT_X = 40; //starting position of ship? do I really need a private variable for this?
    private final int ICRAFT_Y = 60; //starting position of ship?
    private final int B_WIDTH = 400;  //board width
    private final int B_HEIGHT = 300;  //board height
    private final int DELAY = 20;  //millisecond delay for timer probably
    
    private int dead = 0; //death animation
    private int missileDead = 0; //temp missle death animation
    private Missile deadMissile = null;
    
    private Random r = new Random();
    
    private int score = 0;
    
    private boolean displayScoreBoard = false;

    //spawn locations
    private Point spawn0 = new Point(50, -50); 
    private Point spawn1 = new Point(150, -50);
    private Point spawn2 = new Point(250, -50);
    private Point spawn3 = new Point(450, 50);
    private Point spawn4 = new Point(450, 150);
    private Point spawn5 = new Point(450, 250);
    private Point spawn6 = new Point(250, 350);
    private Point spawn7 = new Point(150, 350);
    private Point spawn8 = new Point(50, 350);
    private Point spawn9 = new Point(-50, 250);
    private Point spawn10 = new Point(-50, 150);
    private Point spawn11 = new Point(-50, 50);
     
    private Point[] spawnPoint = {spawn0, spawn1, spawn2, spawn3, spawn4, spawn5, spawn6, spawn7, spawn8, spawn9, spawn10, spawn11};
    
    private int test = 0; //slow down spawn rate

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
        ingame = true;
        
        highscore = new String[2][5];
//        for(int i = 0; i<5; i++) {
//        	highscore[0][i] = pullfromdatabase;
//        	highscore[1][i] = pullfromdatabase;
//        }
        
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        spaceship = new Ship(ICRAFT_X, ICRAFT_Y);
        background1 = new Background(0, 0);
        background2 = new Background(800, 0);
        initAliens();
        timer = new Timer(DELAY, this);
        timer.start();        
    }

    public void initAliens() {
        aliens = new ArrayList<>();         
        ///////////////////////testing
//        aliens.add(new Alien(new Point(250, 100)));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawObjects(g);
        //commenting this out, lets the asteroids continue while you are dead. Kind of a cool background when the game is over
        //do not leave game over screen running too long though, asteroids will increase infinitely with the new increased difficulty setting.
        if (ingame) {
            drawObjects(g);
        }
        else if(displayScoreBoard) {
        	drawGameOver(g);
        }
        else {
        	  ActionListener scoreboard = new ActionListener() {
        	      public void actionPerformed(ActionEvent evt) {
        	    	  displayScoreBoard = true;
        	      }
        	  };
        	  testTimer = new Timer(1000, scoreboard);
              testTimer.setRepeats(false);
        	  testTimer.start();
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
    	
        if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), (int)spaceship.getX(), (int)spaceship.getY(), this);
        }

        List<Missile> ms = spaceship.getMissiles();

        for (Missile missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), (int)missile.getX(), (int)missile.getY(), this);
            }
        }

        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), (int)alien.getX(), (int)alien.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 5, 15);
        g.drawString("Ammo: " + spaceship.getAmmo(), 100, 15);
    }
    
    private void drawBackground(Graphics g) {
        g.drawImage(background1.getImage(), (int)background1.getX(), (int)background1.getY(), this);
        g.drawImage(background2.getImage(), (int)background2.getX(), (int)background2.getY(), this);
    }

    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.fillRect(B_WIDTH/2-45, B_HEIGHT/2+15,88, 100);
        g.setColor(Color.BLACK);
        g.drawString("HIGHSCORE", ((B_WIDTH - fm.stringWidth(msg)) / 2)-3, (B_HEIGHT / 2)+30);
        //for(int i = 0; i<)
        //g.drawString(highscore[0][0]+" "+highscore[1][0], (B_WIDTH - fm.stringWidth(msg)) / 2, (B_HEIGHT / 2)+50);
        g.drawString("Alex: 107", (B_WIDTH - fm.stringWidth(msg)) / 2, (B_HEIGHT / 2)+70);
        g.drawString("Alex: 89", (B_WIDTH - fm.stringWidth(msg)) / 2, (B_HEIGHT / 2)+90);
        g.drawString("Alex: 54", (B_WIDTH - fm.stringWidth(msg)) / 2, (B_HEIGHT / 2)+110);
//      g.drawString("Alex: 41", (B_WIDTH - fm.stringWidth(msg)) / 2, (B_HEIGHT / 2)+110);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        updateBackground();
        updateShip();
        updateMissiles();
        updateAliens();
        checkCollisions();
        repaint();
    }

    private void inGame() {
        if (!ingame) {
            //timer.stop(); //??? keep going?
        }
    }

    private void updateShip() {
        if (spaceship.isVisible()) {
            spaceship.move();
        }
    }
    
    private void updateBackground() {
    	background1.move();
    	background2.move();
    }

    private void updateMissiles() {
        List<Missile> ms = spaceship.getMissiles();

        for (int i = 0; i < ms.size(); i++) {
            Missile m = ms.get(i);
            if (m.isVisible()) {
            	if(m.equals(deadMissile)) {
            	}
            	else {
            		m.move();
            	}
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateAliens() {
    	test++;
    	if(test == 25) {
    		spaceship.reload();
    	}
    	if(test>100) {
    		aliens.add(new Alien(spawnPoint[r.nextInt(12)]));
    		aliens.add(new Alien(spawnPoint[r.nextInt((5-3) +1) +3]));
    		test = 0;
    	}
    	//update current Aliens
        for (int i = 0; i < aliens.size(); i++) {
            Alien a = aliens.get(i);        
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }

    public void checkCollisions() {
    	Area r3 = spaceship.getBounds();
        
        if(dead>0) {
        	spaceship.explode(dead);
        	dead++;
        	if(dead>15) {
        		spaceship.setVisible(false); 
        		ingame = false;
        	}
        }
        
        if(missileDead>0) {
        	missileDead++;
        	deadMissile.explode(missileDead);

        	if(missileDead>15) {
        		deadMissile.setVisible(false);
        		missileDead = 0;
        	}
        }
        
        for (Alien alien : aliens) {
        	r3 = spaceship.getBounds();
        	Area r2 = alien.getBounds();
        	r3.intersect(r2);
            if (!r3.isEmpty()) {
                alien.setVisible(false);
                dead++;
            }
        }
        
        List<Missile> ms = spaceship.getMissiles();
      
        for (Missile m : ms) {
        	Area r1 = m.getBounds();
            for (Alien alien : aliens) {
            	r1 = m.getBounds();
            	Area r2 = alien.getBounds();
            	r1.intersect(r2);
                if (!r1.isEmpty()) {
                	deadMissile = m;
                	missileDead++;
                    alien.setVisible(false);
                    
                    //testing //give ammo back if you kill something? and one every few seconds. prevents full spam but if you are playing normally you never run out. 
                    spaceship.reload();
                    
                    score++;
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceship.keyPressed(e);
        }
    }
}