package dkeep.frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import java.util.HashMap;

@SuppressWarnings("serial")
public class GraphicsPanel extends JPanel implements KeyListener, MouseListener {
	
	private DkeepGUI gui;
	private char[][] map;
	private HashMap <Character, Image> images;
	
	GraphicsPanel (DkeepGUI gui) {
		this.gui = gui;
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.map = new char [10][10];
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = ' ';
			}
		}
		
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
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.requestFocusInWindow();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
