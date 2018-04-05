package dkeep.gui;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dkeep.logic.Game;
import dkeep.logic.GameState;
import dkeep.logic.Map;
import dkeep.serialization.SaveLoad;

public class DkeepGUI {
	
	Game game;
	GameFrame window;
	char[][] level2;
	HashMap <Character, Image> images;
	
	DkeepGUI (GameFrame window) {
		this.window = window;
		this.game = null;
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
	
	public HashMap<Character, Image> get_images() {
		return images;
	}
	
	public void start_new_game (Game new_game) {
		this.game = new_game;
		window.start_game(new_game.get_map().get_matrix());
	}

	public void set_map (char[][] map) {
		level2 = new char[map.length] [map[0].length];
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				level2[i][j] = map[i][j];
			}
		}
		
	}
	
	public char[][] get_level2_map () {
		return level2;
	}
	
	public void play (char hero_key) {
		if (game != null) {
			game.play(hero_key); 
			window.update_map(game.get_map().get_matrix());

			switch (game.get_game_status()) {
			
			case LOST: 	window.set_label("You lost");
						window.enable_buttons(false);
						break;
						
			case WON:	window.set_label("You won!");
						window.enable_buttons(false);
						break;
			
			case PLAYING: window.set_label("Playing level " + game.get_current_level());break;
			
			}
		}
	}
	
	public Game get_game() {
		return game;
	}

	public JLabel get_label() {
		return window.get_label();
	}
	
	public Map get_map () {
		return game.get_map();
	}
}
