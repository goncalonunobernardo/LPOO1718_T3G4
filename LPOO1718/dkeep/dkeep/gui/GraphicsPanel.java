package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dkeep.logic.Map;

import java.util.HashMap;

public class GraphicsPanel extends JPanel {
	
	private char[][] map;
	private HashMap <Character, Image> images;
	
	GraphicsPanel () {
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
			images.put('A', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/hero_down.png")));
			images.put('G', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/guard_down.png")));
			images.put('O', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/ogre_down.png")));
			images.put('*', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/club.png")));
			images.put('k', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/key.png")));
			images.put('X', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/wall.png")));
			images.put(' ', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/free_space.png")));
			images.put('I', ImageIO.read(GraphicsPanel.class.getResource("/dkeep/res/free_space.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void set_map (char[][] map) {
		this.map = map;
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
}
