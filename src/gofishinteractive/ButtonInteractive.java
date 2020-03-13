package gofishinteractive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ButtonInteractive extends JButton {
	private LineBorder line;
	private EmptyBorder margin;
	private CompoundBorder compound;
	
	//instantiate
	ButtonInteractive(){
		super();
	}

	//instantiate - basically the same as a JButton but created with all the settings I want
	ButtonInteractive(String text){
		super(text);
		
		line = new LineBorder(Color.BLACK);
		margin = new EmptyBorder(0, 0, 0, 0);
		compound = new CompoundBorder(line, margin);
	
		super.setFocusable(false);
		super.setPreferredSize(new Dimension(35,40));
		super.setFont(new Font("algerian", Font.PLAIN, 14));
		super.setBackground(Color.LIGHT_GRAY);
		super.setForeground(Color.BLACK);
		super.setBorder(compound);			
		super.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	GameInteractive.input = getText();
            	GameInteractive.unlock();
            }
        });
	}
}