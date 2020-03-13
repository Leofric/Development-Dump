package spaceGame;

import java.awt.Point;


public class Background extends Sprite {
	private final int loop = 799;
 	    
    public Background(int x, int y) {
        super(x, y);
        initBackground();
    }
    
    private void initBackground() {
        loadImage("src/spaceGame/Background.jpg");
        getImageDimensions();
    }

    public void move() {    
    	if(x < -800) {
    		x = loop;
    	}
        x--;
    }
}
