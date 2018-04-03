package dkeep.gui;

import dkeep.logic.Game;

public class DkeepGUI {
	
	Game game;
	GameFrame window;
	
	DkeepGUI (GameFrame window) {
		this.window = window;
		this.game = null;
	}
	
	public void start_new_game (Game new_game) {
		this.game = new_game;
		window.start_game(new_game.get_map().toString());
	}
	
	public void set_label (String text) {
		window.set_label(text);
	}
	
	public void play (char hero_key) {
		if (game != null) {
			game.play(hero_key); 
			window.set_text(game.get_map().toString());
			
			switch (game.get_game_status()) {
			
			case LOST: 	window.set_label("You lost");
						window.enable_buttons(false);
						break;
			
			case WON_LEVEL: window.set_label("Next level! Press a button to continue!");
							game.updateLevel();
							break;
						
			case WON:	window.set_label("You won!");
						window.enable_buttons(false);
						break;
			
			case PLAYING: window.set_label("Playing level " + game.get_current_level());break;
			
			}
		}
	}
}
