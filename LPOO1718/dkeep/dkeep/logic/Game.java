package dkeep.logic;

import java.io.Serializable;

/**
 * @class Game
 * @brief Abstraction of the whole game and the settings around it
 */
public class Game implements Serializable{
	
	GameLogic [] levels;
	int current_level;
	GameState status;

	/**
	 * @brief Constructor for the game
	 * @param levels of game
	 */
	public Game (GameLogic [] levels) {
		this.levels = levels;
		this.current_level = 0;
		this.status = GameState.PLAYING;
	}

	/**
	 * @brief Constructor for the major elements such as the Dungeon and Keep levels
	 */
	public Game () {
		this(new GameLogic [] {new Dungeon(), new Keep()});
	}

	/**
	 * @brief Checks if game is over
	 * @return true if game is in fact over
	 */
	public boolean check_game_over() {
		return !this.get_game_status().equals(GameState.PLAYING);
	}

	/**
	 * @brief Checks game status
	 * @return game status
	 */
	public GameState get_game_status () {
		return status;
	}

	/**
	 * @brief Updates the status of the current game in action
	 */
	public void update_game_status () {
		status = levels[current_level].get_status();
	}

	/**
	 * @brief Obtains Hero
	 * @return Hero object from current level
	 */
	public Hero get_hero() {
		return levels[current_level].get_hero();
	}

	/**
	 * @brief Obtains map
	 * @return Current map object
	 */
	public Map get_map() {
		return levels[current_level].get_map();
	}

	/**
	 * @brief Handler for all of the game logic use
	 * @param key
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
	 * @brief Updates the level if completed the previous
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
	 * @brief Obtains current level
	 * @return Current level
	 */
	public int get_current_level() {
		return current_level + 1;
	}
}
