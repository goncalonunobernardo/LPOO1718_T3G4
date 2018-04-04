package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

import java.util.HashMap;

public class GraphicsPanel extends JPanel implements KeyListener {
	
	private DkeepGUI gui;
	private char[][] map;
	private HashMap <Character, Image> images;
	
	GraphicsPanel (DkeepGUI gui) {
		this.gui = gui;
		this.addKeyListener(this);
		this.map = new char [][] {
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
		};
		
		this.images = gui.get_images();
	}
	
	public void set_map (char[][] map) {
		this.map = map;
	}
	
	public char key_to_letter (int key) {
		switch (key) {
		
		case KeyEvent.VK_UP: return 'w';
		
		case KeyEvent.VK_DOWN: return 's';
		
		case KeyEvent.VK_LEFT: return 'a';
		
		case KeyEvent.VK_RIGHT: return 'd';
		
		}
		
		return ' ';
	}

	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);

		int x = 0, y = 0;
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				
				x = j * 32;
				y = i * 32;
		
			g.drawImage(images.get(map[i][j]), x, y, this);
				
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int hero_key = e.getKeyCode();
		char hero_mov = key_to_letter(hero_key);
		
		if (hero_mov != ' ') {
			gui.play(hero_mov);		
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	
}
