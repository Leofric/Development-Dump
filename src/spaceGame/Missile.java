package spaceGame;

import java.awt.Rectangle;
import java.awt.geom.Area;

public class Missile extends Sprite {

    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 10;

    public Missile(double x, double y) {
        super(x, y);
        initMissile();
    }
    
    private void initMissile() {
        loadImage("src/spaceGame/Missile.png");
        getImageDimensions();        
    }

    public void move() {
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH)
            visible = false;
    }
    
    public void explode(int i) {
    	if (i == 1) {
    		this.y = this.y-20;
    		loadImage("src/spaceGame/Explosion1.jpg");
        	getImageDimensions();
    	}
    	else if (i == 3) {
    		this.y = this.y-20;
        	loadImage("src/spaceGame/Explosion2.png");
        	getImageDimensions();
    	}
    	else if (i == 5) {
        	loadImage("src/spaceGame/Explosion3.png");
        	getImageDimensions();
    	}
    	else if (i == 7) {
        	loadImage("src/spaceGame/Explosion4.png");
        	getImageDimensions();
    	}
    	else if (i == 9) {
        	loadImage("src/spaceGame/Explosion5.png");
        	getImageDimensions();
    	}
    	else if (i == 11) {
        	loadImage("src/spaceGame/Explosion6.png");
        	getImageDimensions();
    	}
    	else if (i == 13){
        	loadImage("src/spaceGame/Explosion7.png");	
        	getImageDimensions();
    	}
    }
    
    public Area getBounds() {
    	Rectangle hitbox = new Rectangle((int)x,(int)y, width, height);
    	return new Area(hitbox);
  }
}