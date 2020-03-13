
package spaceGame;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

public class Tester {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new CustomPaintComponent());
		int frameWidth = 300;
		int frameHeight = 300;
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * To draw on the screen, it is first necessary to subclass a Component and
	 * override its paint() method. The paint() method is automatically called by
	 * the windowing system whenever component's area needs to be repainted.
	 */
	static class CustomPaintComponent extends Component {

		public void paint(Graphics g) {

			// Retrieve the graphics context; this object is used to paint shapes
			Graphics2D g2d = (Graphics2D) g;

			// Draw an oval that fills the window
			int x = 0;
			int y = 0;
			int w = getSize().width - 1;
			int h = getSize().height - 1;


//			g2d.drawLine(x, y, w, h);

			// to draw a filled oval use : g2d.fillOval(x, y, w, h) instead
//			g2d.drawOval(x, y, w, h);
			
//			Ellipse2D test = new Ellipse2D.Double(100, 100, 100, 100);
//			g2d.draw(test);
			

//			// to draw a filled rectangle use : g2d.fillRect(x, y, w, h) instead
//			g2d.drawRect(x, y, w, h);
//
//
//			int startAngle = 45;
//			int arcAngle = -60;
//			g2d.drawArc(x, y, w / 2, h / 2, startAngle, arcAngle);
//
//			// to draw a filled round rectangle use : g2d.fillRoundRect(x, y, w, h,
//			// arcWidth, arcHeight) instead
//			g2d.drawRoundRect(x, y, w, h, w / 2, h / 2);
//
//			Polygon polygon = new Polygon();
//			polygon.addPoint(w / 4, h / 2);
//			polygon.addPoint(0, h / 2);
//			polygon.addPoint(w / 4, 3 * h / 4);
//			polygon.addPoint(w / 2, 3 * h / 4);
//			// Add more points...
//
	    	Polygon hitbox = new Polygon();
	    	hitbox.addPoint((int)x+15, (int)y);
	    	hitbox.addPoint((int)x+53, (int)y);
	    	hitbox.addPoint((int)x+68, (int)y+28);
	    	hitbox.addPoint((int)x+53, (int)y+56);
	    	hitbox.addPoint((int)x+15, (int)y+56);
	    	hitbox.addPoint((int)x, (int)y+28);

			
			g2d.drawPolygon(hitbox);

		}

	}

}
