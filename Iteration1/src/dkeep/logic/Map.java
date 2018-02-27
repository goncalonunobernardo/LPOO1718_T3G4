package dkeep.logic;

/**
 * 
 * @class Map
 * @brief Abstraction of a map. It saves the letters of each position of the map, the hero and the guard of the game
 * It allows to change some character's positions, to move characters and to print the map.
 */
public class Map {
	
	private char matrix[][];		/** @brief Where the letters of each position of the map are stored*/
	private Hero hero;			/** @brief Hero of the game */
	private Guard guard;			/** @brief Guard of the game */
	private Ogre ogre;
	private Club club;
	private int level;
	private boolean win, lose;	/** @brief Boolean variables to know if the game ended or not*/
	
	/**
	 * @brief Depending on the level, the constructor gives different values to the matrix, hero and guard
	 * @param level 1 to play with the guard, 2 to play with the ogre
	 */
	Map (int level) {
		
		this.win = false;
		this.lose = false;
		
		switch (level) {
		
		case 1: { 
			this.matrix = new char [][] {
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
			this.level = 1;
			this.hero = new Hero (1, 1, 'H');
			this.guard = new Guard (8, 1, 'G');
			break;
		}
		
		
		case 2: {
			this.matrix = new char [][] {
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'I', ' ', ' ', ' ', 'O', '*', ' ', ' ', 'k', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
			};
			this.level = 2;
			this.hero = new Hero (1, 8, 'H');
			this.ogre = new Ogre (4, 1, 'O');
			this.club = new Club (5, 1, '*');
			break;
		}
		
		}
	}
	
	/**
	 * @param x x coordinate of the letter to be returned
	 * @param y y coordinate of the letter to be returned
	 * @return The letter on the given positions of the matrix
	 */
	public char get_letter (int x, int y) {
		return matrix[y][x];
	}
	
	/**
	 * @return The guard of this map
	 */
	public Guard get_guard () {
		return guard;
	}
	
	/**
	 * @return The hero of this map
	 */
	public Hero get_hero() {
		return hero;
	}
	
	/**
	 * @return The ogre of this map
	 */
	public Ogre get_ogre() {
		return this.ogre;
	}
	
	/**
	 * @return The club of this map
	 */
	public Club get_club() {
		return this.club;
	}
	
	/**
	 * @return The level of this map
	 */
	public int get_level () {
		return this.level;
	}
	
	/**
	 * @return The value of the attribute win
	 */
	public boolean check_win() {
		return this.win;
	}
	
	/**
	 * @return The value of the attribute lose
	 */
	public boolean check_lose() {
		return this.lose;
	}
	
	/**
	 * @brief Sets the attribute win to true
	 */
	public void set_win() {
		this.win = true;
	}
	
	/**
	 * @brief Sets the attribute lose to true
	 */
	public void set_lose() {
		this.lose = true;
	}
	
	/**
	 * @param x x coordinate of the letter that will be changed
	 * @param y y coordinate of the letter that will be changed
	 * @param new_letter The char to replace the letter
	 */
	public void set_letter (int x, int y, char new_letter) {
		
		matrix[y][x] = new_letter;
	}
	
	/**
	 * @brief Prints the matrix on the console
	 */
	public void print () {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * @brief Changes the doors on the left wall of the map from I to S
	 */
	public void open_doors () {
		for (int i = 0; i < matrix[0].length; i++) {
			if (get_letter (0, i) == 'I')
				set_letter (0, i, 'S');
		}
	}
	
	/**
	 * @brief Moves an entity according to a key
	 * @param person The entity to be moved
	 * @param key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 */
	public void move_person (Person person, char key) {
		int old_x = person.get_x_pos();
		int old_y = person.get_y_pos();
		
		if (person.move_person(key, this)) {
			//to make sure the character
			if (this.get_letter(old_x, old_y) == person.get_symbol()) {
				this.set_letter(old_x, old_y, ' ');
			}
			this.set_letter(person.get_x_pos(), person.get_y_pos(), person.get_symbol());
		}
		else {
			person.set_pos(old_x, old_y);
		}
	}
	
	/**
	 * @brief Moves the hero, the guard or the ogre and the club of the game, by calling move_person and checks if the game is over
	 * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @return true, if the game ended; false, otherwise
	 */
	public boolean move (char key) {
		
		if (get_guard() != null) {
			// the key won't be used so it doesn't matter
			move_person (get_guard(), ' ');
		}
		else {
			// the key won't be used so it doesn't matter
			move_person (get_ogre(), ' ');
			move_person (get_club(), ' ');
		}

		move_person (get_hero(), key);
	
		return check_win() || check_lose();
	}
}
