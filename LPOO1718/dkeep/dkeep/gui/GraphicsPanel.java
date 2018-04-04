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
		
		this.images = new HashMap <Character, Image> ();
		
		try {
			images.put('H', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/hero_down.png")));
			images.put('A', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/hero_armed.png")));
			images.put('G', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/guard_down.png")));
			images.put('g', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/guard_asleep.png")));
			images.put('$', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/ogre_with_key.png")));
			images.put('8', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/ogre_stunned.png")));
			images.put('O', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/ogre_down.png")));
			images.put('*', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/club.png")));
			images.put('k', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/key.png")));
			images.put('K', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/hero_with_key.png")));
			images.put('X', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/wall.png")));
			images.put(' ', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/free_space.png")));
			images.put('S', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/free_space.png")));
			images.put('I', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/closed_door.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
