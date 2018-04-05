package dkeep.logic;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * @class Keep
 * @brief Abstraction of the Keep level in game. 
 * It allows to move the hero, the ogres, to randomly pick the number of ogres or to choose it
 */
@SuppressWarnings("serial")
public class Keep implements GameLogic, Serializable{
	
	private Map map;
	private Hero hero;
	private Vector <Ogre> ogres;

	/**
	 * @brief Constructor for Keep level
	 * @param matrix Matrix of the map of this level
	 */
	public Keep (char[][] matrix) {
		this.map = new Map (matrix);
		this.hero = new Hero (map.search_char('A'), 'A', 'K');
		
		this.ogres = new Vector<Ogre> ();
		Random r = new Random ();
		
		int bound = r.nextInt(3) + 1;
		
		for (int i = 0; i < bound; i++) {
			ogres.add(new Ogre (map.search_char('O'), map.search_char('*'), 'O', '$', '*', '$'));
		}
	}

	/**
	 * @brief Default constructor of the level: default matrix and random number of ogres
	 */
	Keep () {
		this (default_matrix());
	}

	/**
	 * @brief Constructor for all the settings in the Keep level
	 * @param matrix Matrix of the map of this level
	 * @param nr_ogres Number of ogres that the level will have
	 */
	public Keep (char [][] matrix, int nr_ogres) {
		char[][] temp  = new char[matrix.length] [matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				temp[i][j] = matrix[i][j];
			}
		}
		
		this.map = new Map (temp);
		this.hero = new Hero (map.search_char('A'), 'A', 'K');
		
		this.ogres = new Vector<Ogre> ();
		
		for (int i = 0; i < nr_ogres; i++) {
			ogres.add(new Ogre (map.search_char('O'), map.search_char('*'), 'O', '$', '*', '$'));
		}
	}

	/**
	 * @brief Constructor for keep level in accordance to the number of ogres
	 * @param nr_ogres Number of ogres that the level will have
	 */
	public Keep (int nr_ogres) {
		this (default_matrix(), nr_ogres);
	}
	
	public static char [][] default_matrix() {
		return new char [][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'I', ' ', ' ', ' ', 'O', '*', ' ', ' ', 'k', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
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
	 * @return vector of all ogres
	 */
	public Vector <Ogre> get_ogres() {
		return this.ogres;
	}

	/**
	 * @return Map obtained
	 */
	@Override
	public Map get_map() {
		return this.map;
	}

	/**
	 * @brief Moves the Hero and the Ogres
	 * @param key To move the hero in a certain direction: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 */
	@Override
	public void move (char key) {
		
		move_hero(key);

		move_ogres();
		
		map.draw_key();
	}

	/**
	 * @brief Checks status of game depending on the coordinates of Hero and Ogres and if they are in adjacent cells
	 * @return The current state of the game: LOST, WON, PLAYING
	 */
	public GameState get_status () {
		boolean lost = false;
		
		for (Iterator <Ogre> it = ogres.iterator(); it.hasNext(); ) {
			lost |= it.next().check_near(get_hero());
		}
		
		if (lost) 
			return GameState.LOST;
		
		if (hero.check_win())
			return GameState.WON;
		
		return GameState.PLAYING;
		
	}

	/**
	 * @brief Moves hero
	 * @param key
	 */
	public void move_hero(char key) {
		map.reset_person(get_hero());
		
		hero.move_person(key, map);
		
		map.draw_hero(get_hero());
	}

	/**
	 * @brief Moves all ogres in Keep level
	 */
	public void move_ogres () {
		
		for (Iterator <Ogre> it = ogres.iterator(); it.hasNext(); ) {
			Ogre current = it.next();
			
			map.reset_person(current);
			map.reset_person(current.getClub());
			current.move_person(' ', map);
			map.draw_person(current);
			map.draw_person(current.getClub());
		}
	}
}
