package spaceGame;

import java.awt.EventQueue;
import javax.swing.JFrame;


/*
 * 
 * TO:DO
 * database support for leader board tracking
 * screen size?
 * dry runs? bullets = 0 survive as long as you can?
 * timer at the top?
 * Bullet UI / AMMO tracking easier
 * power ups?
 * explosion animation is kind of awkward, maybe remove hitbox after a hit/remove aoe on explosion
 * can still fire while exploding for like .1 second //eh not a huge deal but still not polished
 * 
 * 
 * 
 */

public class CollisionEx extends JFrame {

    public CollisionEx() {
        initUI();
    }
    
    private void initUI() {
        add(new Board());
        setResizable(false);
        pack();
        setTitle("Collision");
        //setLocationRelativeTo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            CollisionEx ex = new CollisionEx();
            ex.setVisible(true);
        });
    }
} 