package dkeep.logic;

import java.util.Iterator;
import java.util.Vector;

public class Game {
	private Hero hero;			/** @brief Hero of the game */
	private Vector <Person> enemies;
	private boolean win, lose;	/** @brief Boolean variables to know if the game ended or not*/
	private Map map;

//	public Game () {
//		this.win = false;
//		this.lose = false;
//		this.map = new Map(2);
//		this.enemies = new Vector <Person> ();
//
//		char [][] matrix = map.get_matrix();
//
//		for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < matrix[i].length; j++) {
//				if (matrix[i][j] == 'H') {
//					this.hero = new Hero (j, i, 'H');
//				}
//				else if (matrix[i][j] == 'A') {
//					this.hero = new Hero (j, i, 'A');
//				}
//				else if (matrix[i][j] == 'G') {
//					this.enemies.add(new Guard (j, i, 'G', "assssaaaaaasdddddddwwwww"));
//				}
//				else if (matrix[i][j] == 'O') {
//					enemies.add(new Ogre (j, i, 'O', j+1, i, '*'));
//				}
//			}
//		}
//	}

	public Game (Map map) {
		this.win = false;
		this.lose = false;
		this.map = map;
		this.enemies = new Vector <Person> ();

		char [][] matrix = map.get_matrix();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 'H') {
					this.hero = new Hero (j, i, 'H');
				}
				else if (matrix[i][j] == 'A') {
					this.hero = new Hero (j, i, 'A');
				}
				else if (matrix[i][j] == 'G') {
					this.enemies.add(new Guard (j, i, 'G', "assssaaaaaasdddddddwwwww"));
				}
				else if (matrix[i][j] == 'O') {
					enemies.add(new Ogre (j, i, 'O', j, i, '*'));
				}
			}
		}
	}

//	public boolean level_setup() {
//		if (check_win()) {
//			if (this.map.get_level() == 1) {
//				System.out.println("LEVEL 2");
//				this.set_level(2);
//				return true;
//			}
//			else {
//				System.out.println("You win!!");
//				return false;
//			}
//		}
//		else {
//			System.out.println("You lose!!");
//			return false;
//		}
//	}

//	public void set_level(int level) {
//		this.map.set_level(level);
//
//		if (map.get_level() == 1) {
//			this.hero.set_pos(1, 1);
//			this.hero.set_symbol('H');
//			this.enemies.clear();
//			this.enemies.add(new Guard (8, 1, 'G', "assssaaaaaasdddddddwwwww"));
//
//		} else
//			if (map.get_level() == 2) {
//				this.hero.set_pos(1, 8);
//				this.hero.set_symbol('H');
//				this.enemies.clear();
//				this.enemies.add(new Ogre (4, 1, 'O', 5, 1, '*'));
//			}
//		this.win = false;
//		this.lose = false;
//	}

	/**
	 * @return The hero of this game
	 */
	public Hero get_hero() {
		return hero;
	}

	public Map get_map() {
		return this.map;
	}

	/**
	 * @return The value of the attribute lose
	 */
	public boolean check_win() {
		this.win = get_hero().check_win();
		return this.win;
	}

	public boolean check_lose() {
		for (Iterator <Person> it = enemies.iterator(); it.hasNext(); ) {
			this.lose |= it.next().check_near(get_hero());
		}
		return this.lose;
	}

	public boolean check_game_over() {
		return (check_lose() || check_win());
	}

	/**
	 * @brief Moves the hero, the guard or the ogre and the club of the game, by calling move_person and checks if the game is over
	 * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @return true, if the game ended; false, otherwise
	 */
	public boolean move (char key) {

		move_hero(key);

		move_enemies();
		
		return check_win() || check_lose();
	}

	public void move_hero(char key) {
		get_hero().reset_person(map);
		get_hero().move_person(key, map);
		get_hero().draw_person(map);
	}

	public void move_enemies () {
		reset_enemies();

		for (Iterator <Person> it = enemies.iterator(); it.hasNext(); ) {
			Person current = it.next();
			current.move_person(' ', get_map());
			current.draw_person(map);
		}
	}

	public void reset_enemies () {

		for (Iterator <Person> it = enemies.iterator(); it.hasNext(); ) {
			it.next().reset_person(map);
		}    		
	}

	public void draw_person (Person person_drawing) {
		map.set_letter(person_drawing.get_coordinates(), person_drawing.get_symbol());
	}
}
