package spaceGame;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Alien extends Sprite {
    private double vx = 0;			//Velocity in the x direction
    private double vy = 0;			//Velocity in the y direction
    private int spawnPointX = 0;	//Initial x location 
    private int spawnPointY = 0;	//Initial y location
    
    private Random r = new Random();	//random number generator
    
    //Random number formula used in calculations below
	//double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();    
    
    public Alien(Point p) {
        super(p.x, p.y);
        spawnPointX=p.x;
        spawnPointY=p.y;
        
        //Assign random trajectory within a ranged determined by the spawn location (11 possible spawn locations)
        if(p.x == 50 && p.y == -50) {
        	vx = 0 + (1 - 0) * r.nextDouble();
        	vy = 1;
        }
        else if(p.x == 150 && p.y == -50) {
        	vx = -1 + (1 - -1) * r.nextDouble();
        	vy = 1;
        }
		else if(p.x == 250 && p.y == -50) {
			vx = -1 + (0 - -1) * r.nextDouble();
        	vy = 1;
		}
		else if(p.x == 450 && p.y == 50) {
			vx = -1;
        	vy = 0 + (1 - 0) * r.nextDouble();	
		}
		else if(p.x == 450 && p.y == 150) {
			vx = -1;
        	vy = -1 + (1 - -1) * r.nextDouble();	
		}
		else if(p.x == 450 && p.y == 250) {
			vx = -1;
        	vy = -1 + (0 - -1) * r.nextDouble();	
		}
		else if(p.x == 250 && p.y == 350) {
			vx = -1 + (0 - -1) * r.nextDouble();
        	vy = -1;	
		}
		else if(p.x == 150 && p.y == 350) {
			vx = -1 + (1 - -1) * r.nextDouble();
        	vy = -1;	
		}
		else if(p.x == 50 && p.y == 350) {
			vx = 0 + (1 - 0) * r.nextDouble();
        	vy = -1;	
		}
		else if(p.x == -50 && p.y == 250) {
			vx = 1;
        	vy = -1 + (0 - -1) * r.nextDouble();	
		}
		else if(p.x == -50 && p.y == 150) {
			vx = 1;
        	vy = -1 + (1 - -1) * r.nextDouble();	
		}
		else if(p.x == -50 && p.y == 50) {
			vx = 1;
        	vy = 0 + (1 - 0) * r.nextDouble();
		}
		else {
			vx = 0;
			vy = 0;
		}
        initAlien();
    }
    
    private void initAlien() {
        loadImage("src/spaceGame/ship.png");
        getImageDimensions();
    }
   
    public Area getBounds() {
    	Polygon hitbox = new Polygon();
    	hitbox.addPoint((int)x+40, (int)y);
    	hitbox.addPoint((int)x+50, (int)y+6);
    	hitbox.addPoint((int)x+55, (int)y+12);
    	hitbox.addPoint((int)x+80, (int)y+24);
    	hitbox.addPoint((int)x+40, (int)y+30);
    	hitbox.addPoint((int)x, (int)y+24);
    	hitbox.addPoint((int)x+25, (int)y+12);
    	hitbox.addPoint((int)x+30, (int)y+6);
    	return new Area(hitbox);
    }

    public void move() {    
//this if statement recycles asteroids making the game increasingly difficult
    	if(Math.abs(x) > 600 || Math.abs(y) > 500) {
    		super.x = spawnPointX;
    		super.y = spawnPointY;
    	}
    	
//this if statement deletes extra asteroids when they go off screen. consistent difficult level
//    	if(Math.abs(x) > 600 || Math.abs(y) > 500) {
//    		super.setVisible(false);
//    	}
        x += vx;
        y += vy;
    }
}