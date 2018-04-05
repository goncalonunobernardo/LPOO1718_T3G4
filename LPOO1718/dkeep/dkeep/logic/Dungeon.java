package dkeep.logic;

import com.sun.corba.se.impl.util.PackagePrefixChecker;

import java.io.Serializable;
import java.util.Random;

/**
 * @class Dungeon
 * @brief Abstraction of the Dungeon level in game.
 */
@SuppressWarnings("serial")
public class Dungeon implements GameLogic, Serializable {
	
	private Map map;
	private Hero hero;
	private Guard guard;

	/**
	 * @brief Constructor for the Dungeon level and dependent of the type of Guard
	 * @param matrix of char representing the level
	 */
	public Dungeon (char[][] matrix) {
		this.map = new Map (matrix);

		Coordinates hero_coord = map.search_char('H');
		Coordinates guard_coord = map.search_char ('G');

		this.hero = new Hero (hero_coord, 'H', 'K');

		Random r = new Random();

		switch (r.nextInt(3)) {

		case 0: this.guard = new Guard (guard_coord, 'G', "assssaaaaaasdddddddwwwww");
				break;

		case 1: this.guard = new Suspicious (guard_coord, 'G', "assssaaaaaasdddddddwwwww", 10);
				break;

		case 2: this.guard = new Drunken (guard_coord, 'G', "assssaaaaaasdddddddwwwww", 10);
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
	 * @param guard_type
	 */
	public Dungeon (String guard_type) {
		this.map = new Map (default_matrix());
		
		guard_type = guard_type.toLowerCase();

		Coordinates hero_coord = map.search_char('H');
		Coordinates guard_coord = map.search_char ('G');

		this.hero = new Hero (hero_coord, 'H', 'K');

		switch (guard_type) {

		case "rookie": this.guard = new Guard (guard_coord, 'G', "assssaaaaaasdddddddwwwww");
		break;

		case "suspicious": this.guard = new Suspicious (guard_coord, 'G', "assssaaaaaasdddddddwwwww", 10);
		break;

		case "drunken": this.guard = new Drunken (guard_coord, 'G', "assssaaaaaasdddddddwwwww", 10);
		break;

		}
	}
	
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
	 * @param key to move
	 */
	public void move_hero(char key) {
		map.reset_person(get_hero());

		hero.move_person(key, map);

		map.draw_hero(get_hero());
	}

	/**
	 * @brief Moves the Guard character
	 */
	public void move_guard () {
		map.reset_person(get_guard());

		guard.move_person(' ', get_map());

		map.draw_person(get_guard());
	}
}
