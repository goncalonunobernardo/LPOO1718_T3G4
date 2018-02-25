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
	
	/**
	 * @brief Depending on the level, the constructor gives different values to the matrix, hero and guard
	 * @param level 1 to play with the guard, 2 to play with the ogre
	 */
	Map (int level) {
		
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
	
	public Ogre get_ogre() {
		return this.ogre;
	}
	
	public Club get_club() {
		return this.club;
	}
	
	public int get_level () {
		return this.level;
	}
	
	/**
	 * 
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
	 * @brief Changes the exit doors from I to S
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
			//to make sure the club does not delete the k
			if (this.get_letter(old_x, old_y) != 'k') {
				this.set_letter(old_x, old_y, ' ');
			}
			this.set_letter(person.get_x_pos(), person.get_y_pos(), person.get_symbol());
		}
		else {
			person.set_pos(old_x, old_y);
		}
	}
	
	/**
	 * @brief Moves the hero of the game, by calling move_person and checks if the game is over
	 * @param key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @return false, if the game ended; true, otherwise
	 */
	public boolean move (char key) {
		char old_symbol;
		
		if (guard != null) {
			// the key won't be used so it doesn't matter
			move_person (get_guard(), ' ');
		}
		else {
			// the key won't be used so it doesn't matter
			move_person (get_ogre(), ' ');
			
			old_symbol = get_club().get_symbol();
			
			move_person (get_club(), ' ');
			
			// if the condition is true, it means it is above the k
			if (old_symbol == '*' && get_club().get_symbol() == '$') {
				this.set_letter(get_club().get_x_pos(), get_club().get_y_pos(), 'k');
			}
		}

		move_person (get_hero(), key);
	
		return get_hero().check_playing(this);
	}
	
	
//
//	/* PARA O MAPA2
//	private int hero_hpos = 8;
//	private int hero_vpos = 1;
//	private int ogre_hpos = 1;
//	private int ogre_vpos = 4;
//	private int win_door_hpos = 1;
//	private int win_door_vpos = 0;
//	 */
//	private int hero_hpos = 1;
//	private int hero_vpos = 1;
//	private int guard_hpos = 8;
//	private int guard_vpos = 1;
//	private int win_door_hpos = 0;
//
//	private int count_string = 0;
//	private String guard_movement = "assssaaaaaasdddddddwwwww";
//		
//	Boolean near_guard (int x, int y) {
//		
//		//mesma coluna do guarda e nas 3 posicoes "perto"
//		if (guard_hpos - 1 <= x && x <= guard_hpos + 1  && y == guard_vpos) {
//			return true;
//		}
//		//mesma linha do guarda e nas 3 posicoes "perto"
//		else if (guard_vpos - 1 <= y && y <= guard_vpos + 1  && x == guard_hpos) {
//			return true;
//		}
//		
//		return false;
//	}
//
//	Boolean check_win (int x, int y) {
//		if ( x == win_door_hpos + 1 && (y == 5 || y == 6) && matrix[y][win_door_hpos] == 'S') {
//			return true;
//		}
//		return false;
//	}

//	Boolean change_guard (char movement_key) {
//		int guard_h_aux = guard_hpos, guard_v_aux = guard_vpos;
//
//		switch (movement_key) {
//
//			case 'w':
//				guard_v_aux -= 1;
//				break;
//
//			case 'a':
//				guard_h_aux -= 1;
//				break;
//
//			case 's':
//				guard_v_aux += 1;
//				break;
//
//			case 'd':
//				guard_h_aux += 1;
//				break;
//
//		}
//
//		if (matrix[guard_v_aux][guard_h_aux] == ' ') {
//			matrix[guard_vpos][guard_hpos] = ' ';
//			matrix[guard_v_aux][guard_h_aux] = 'G';
//			guard_vpos = guard_v_aux;
//			guard_hpos = guard_h_aux;
//		}
//
//		return true;
//	}
//
//	Boolean change_hero (char key) {
//
//		int h_aux = hero_hpos, v_aux = hero_vpos;
//
//
//			switch (key) {
//
//				case 'w':
//					v_aux -= 1;
//					break;
//
//				case 'a':
//					h_aux -= 1;
//					break;
//
//				case 's':
//					v_aux += 1;
//					break;
//
//				case 'd':
//					h_aux += 1;
//					break;
//
//			}
//
//			change_guard(guard_movement.charAt(count_string));
//			if(count_string == guard_movement.length() - 1) count_string = 0;
//			else count_string++;
//
//			if (matrix[v_aux][h_aux] == 'k') {
//				open_doors();
//			} else if (matrix[v_aux][h_aux] == ' ') {
//				matrix[hero_vpos][hero_hpos] = ' ';
//				matrix[v_aux][h_aux] = 'H';
//				hero_vpos = v_aux;
//				hero_hpos = h_aux;
//			} else if(matrix[v_aux][h_aux] == 'S') {
//				matrix[hero_vpos][hero_hpos] = ' ';
//				matrix[v_aux][h_aux] = 'H';
//				hero_vpos = v_aux;
//				hero_hpos = h_aux;
//			}
//
//			if (near_guard(h_aux, v_aux)) {
//				System.out.println("\n\n\tYou lose!");
//				return false;
//			}
//
//			if (check_win(h_aux, v_aux)) {
//				System.out.println("\n\n\tYou win!");
//				return false;
//			}
//
//		return true;
//	}

}
