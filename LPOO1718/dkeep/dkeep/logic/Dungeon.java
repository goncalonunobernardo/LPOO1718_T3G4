package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * @class Dungeon
 * @brief Abstraction of the Dungeon level in game: 
 * It allows to move the hero, the guard, to pick a type of guard or for it to be randomly picked and to check the game's status
 */
@SuppressWarnings("serial")
public class Dungeon implements GameLogic, Serializable {
	/** @brief Default movement of the guard in this level */
	public static final String DEFAULT_MOVEMENT = "assssaaaaaasdddddddwwwww";
	/** @brief Default maximum number the guard can walk in the same direction, stay awake, stay asleep.*/
	public static final int DEFAULT_MAX_BOUND = 10;
	/** @brief Default symbols for the characters of the level */
	public static final char HERO_SYMBOL = 'H', GUARD_SYMBOL = 'G', HERO_WKEY_SYMBOL = 'K';
	
	private Map map;				/** @brief Map of this level */
	private Hero hero;			/** @brief Hero of this level */
	private Guard guard;			/** @brief Guard of this level */

	/**
	 * @brief Constructor for the Dungeon level: randomly picks the type of guard (rookie, suspicious, drunken)
	 * @param matrix The char matrix representing the level's map
	 */
	public Dungeon (char[][] matrix) {
		this.map = new Map (matrix);

		Coordinates hero_coord = map.search_char(HERO_SYMBOL);
		Coordinates guard_coord = map.search_char (GUARD_SYMBOL);

		this.hero = new Hero (hero_coord, HERO_SYMBOL, HERO_WKEY_SYMBOL);

		Random r = new Random();

		switch (r.nextInt(3)) {

		case 0: this.guard = new Guard (guard_coord, GUARD_SYMBOL, DEFAULT_MOVEMENT);
				break;

		case 1: this.guard = new Suspicious (guard_coord, GUARD_SYMBOL, DEFAULT_MOVEMENT, DEFAULT_MAX_BOUND);
				break;

		case 2: this.guard = new Drunken (guard_coord, GUARD_SYMBOL, DEFAULT_MOVEMENT, DEFAULT_MAX_BOUND);
				break;

		}
	}

	/**
	 * @brief generic Dungeon Level
	 */
	Dungeon () {
		this (default_matrix());
	}

	/**
	 * @brief generic map dependent on the Guard
	 * @param guard_type The type of guard that will be in this level: rookie, suspicious or drunken
	 */
	public Dungeon (String guard_type) {
		this.map = new Map (default_matrix());
		
		guard_type = guard_type.toLowerCase();

		Coordinates hero_coord = map.search_char(HERO_SYMBOL);
		Coordinates guard_coord = map.search_char (GUARD_SYMBOL);

		this.hero = new Hero (hero_coord, HERO_SYMBOL, HERO_WKEY_SYMBOL);

		switch (guard_type) {

		case "rookie": this.guard = new Guard (guard_coord, GUARD_SYMBOL, DEFAULT_MOVEMENT);
		break;

		case "suspicious": this.guard = new Suspicious (guard_coord, GUARD_SYMBOL, DEFAULT_MOVEMENT, 10);
		break;

		case "drunken": this.guard = new Drunken (guard_coord, GUARD_SYMBOL, DEFAULT_MOVEMENT, 10);
		break;

		}
	}
	
	/**
	 * @brief To store the default matrix of the level's map
	 * @return The default matrix
	 */
	public static char [][] default_matrix() {
		return new char [][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
	}

	/**
	 * @return The hero of this game
	 */
	public Hero get_hero() {
		return hero;
	}

	/**
	 * @return The Guard of this game
	 */
	public Guard get_guard() {
		return guard;
	}

	/**
	 * @return The map of the current game
	 */
	public Map get_map() {
		return this.map;
	}

	/**
	 * @brief Moves the hero, the guard or the ogre and the club of the game, by calling move_person and checks if the game is over
	 * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @return true, if the game ended; false, otherwise
	 */
	public void move (char key) {

		move_hero(key);

		move_guard();

		map.draw_key();
	}

	/**
	 * @brief Takes care of the game state while under the application
	 * @return game state whether it's playing, lost or won
	 */
	public GameState get_status () {
		if (guard.check_near(get_hero()))
			return GameState.LOST;

		if (hero.check_win())
			return GameState.WON;

		return GameState.PLAYING;
	}

	/**
	 * @brief Moves the Hero character
	 * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 */
	public void move_hero(char key) {
		map.reset_person(get_hero());

		hero.move_person(key, map);

		map.draw_hero(get_hero());
	}

	/**
	 * @brief Moves the Guard character. 
	 * There's no need for a key because the guard has a restricted movement set in its constructor
	 */
	public void move_guard () {
		map.reset_person(get_guard());

		guard.move_guard( get_map());

		map.draw_person(get_guard());
	}
}
