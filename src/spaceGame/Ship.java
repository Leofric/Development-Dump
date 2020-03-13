package spaceGame;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

public class Ship extends Sprite {
    private int dx;
    private int dy;
    private int ammo;
    private List<Missile> missiles;

    public Ship(double x, double y) {
        super(x, y);
        initCraft();
    }

    private void initCraft() {
    	ammo = 5;
        missiles = new ArrayList<>();
        loadImage("src/spaceGame/ship4.png");
        getImageDimensions();
    }
    
    public void explode(int i) {
    	if (i == 1) {
    		loadImage("src/spaceGame/Explosion1.jpg");
    		getImageDimensions();
    	}
    	else if (i == 3) {
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
    	else if ( i == 13){
        	loadImage("src/spaceGame/Explosion7.png");
        	getImageDimensions();
    	}
    	else if (i == 15) {
    		loadImage("src/spaceGame/Explosion8.png");
    		x = 500;
    		y = 500;
    	}
    }

    public void move() {
        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }
        
        if (x > 365) {
            x = 365;
        }
        
        if (y > 285) {
            y = 285;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
        	if(ammo > 0 ) {
        		ammo--;
        		fire();
        	}
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height));
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    } 
    
    public Area getBounds() {
    	Polygon hitbox = new Polygon();
    	hitbox.addPoint((int)x, (int)y);
    	hitbox.addPoint((int)x+35, (int)y);
    	hitbox.addPoint((int)x+35, (int)y+15);
    	hitbox.addPoint((int)x, (int)y+15);
    	return new Area(hitbox);
    }
    
    public void reload() {
    	if(ammo<5) {
    		ammo++;
    	}
    }
    
    public int getAmmo() {
    	return ammo;
    }
}