package dkeep.logic;

import java.io.Serializable;


@SuppressWarnings("serial")
/**
 * @class Game
 * Abstraction of the whole game and the settings around it.
 * It allows to construct a game with unlimited number of levels, to play, to check the status of the game and to update the level
 */
public class Game implements Serializable{
	
	GameLogic [] levels;
	int current_level;
	GameState status;

	/**
	 * Constructor of the game
	 * @param levels of game
	 */
	public Game (GameLogic [] levels) {
		this.levels = levels;
		this.current_level = 0;
		this.status = GameState.PLAYING;
	}

	/**
	 * Constructor of the default levels of the game
	 */
	public Game () {
		this(new GameLogic [] {new Dungeon(), new Keep()});
	}

	/**
	 * Checks if game is over
	 * @return true if game is in fact over
	 */
	public boolean check_game_over() {
		return !this.get_game_status().equals(GameState.PLAYING);
	}

	/**
	 * Checks game status: WON, LOST or PLAYING
	 * @return game status
	 */
	public GameState get_game_status () {
		return status;
	}

	/**
	 * Updates the status of the current game in action by checking the status of the level that's being played
	 */
	public void update_game_status () {
		status = levels[current_level].get_status();
	}

	/**
	 * Obtains Hero
	 * @return Hero object from current level
	 */
	public Hero get_hero() {
		return levels[current_level].get_hero();
	}

	/**
	 * Obtains map
	 * @return Current map object
	 */
	public Map get_map() {
		return levels[current_level].get_map();
	}

	/**
	 * Handler for all of the game logic use: checks if the game is not over, if a level it's not over and updates it if necessary,
	 * makes a play by calling the GameLogic method move
	 * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 */
	public void play (char key) {
		
		if (status == GameState.PLAYING) {
			levels[current_level].move(key);
		}

		update_game_status();
		
		if (status == GameState.WON) {
			updateLevel();
		}
	}

	/**
	 * Updates the level if completed the previous
	 */
	public void updateLevel () {
		
			current_level++;
			
			if (current_level < levels.length) 
				status = GameState.PLAYING;
			else {
				current_level--;
				status = GameState.WON;
			}
		}

	/**
	 * Obtains current level
	 * @return Current level
	 */
	public int get_current_level() {
		return current_level + 1;
	}
}
