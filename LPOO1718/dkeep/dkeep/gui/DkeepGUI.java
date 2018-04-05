package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dkeep.logic.Game;
import dkeep.logic.Map;
import dkeep.serialization.SaveLoad;

public class DkeepGUI {
	
	Game game;
	GameFrame window;
	
	DkeepGUI (GameFrame window) {
		this.window = window;
		this.game = null;
	}
	
	public JFrame get_main_frame () {
		return window;
	}
	
	public void start_new_game (Game new_game) {
		this.game = new_game;
		window.start_game(new_game.get_map().get_matrix());
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

	public JLabel get_label() {
		return window.get_label();
	}
	
	public Map get_map () {
		return game.get_map();
	}
}
