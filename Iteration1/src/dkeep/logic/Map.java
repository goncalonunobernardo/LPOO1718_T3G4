package dkeep.logic;

/**
 * 
 * @class Map
 * @brief Abstraction of a map. It saves the letters of each position of the map, the hero and the guard of the game
 * It allows to change some character's positions, to move characters and to print the map.
 */
public class Map {
    private char matrix[][];		/** @brief Where the letters of each position of the map are stored*/
//    private int level;
	/**
	 * @brief Depending on the level, the constructor gives different values to the matrix, hero and guard
	 * @param level 1 to play with the guard, 2 to play with the ogre
	 */

//	Map (int level) {
//		
//		this.level = level;
//		
//	    switch (level) {
//
//		case 1: {
//			this.matrix = new char [][] {
//				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
//				{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
//				{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
//				{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
//				{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
//				{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
//				{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
//				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
//			};
//			break;
//		}
//
//
//		case 2: {
//			this.matrix = new char [][] {
//				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
//				{'I', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'k', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
//			};
//            break;
//		}
//		}
//	}
	
	public Map (char map[] []) {
		this.matrix = map;
	}

	/**
	 * @param x x coordinate of the letter to be returned
	 * @param y y coordinate of the letter to be returned
	 * @return The letter on the given positions of the matrix
	 */
	public char get_letter (Coordinates coord) {
		return matrix[coord.get_y()][coord.get_x()];
	}

	public char[][] get_matrix() {
		return this.matrix;
	}

	/**
	 * @param x x coordinate of the letter that will be changed
	 * @param y y coordinate of the letter that will be changed
	 * @param new_letter The char to replace the letter
	 */
	public void set_letter (Coordinates coord, char new_letter) {
		
		matrix[coord.get_y()][coord.get_x()] = new_letter;
	}
	
	/**
	 * @brief Prints the matrix on the console
	 */
	public void print () {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
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
			if (get_letter (new Coordinates (0, i)) == 'I')
				set_letter (new Coordinates (0, i), 'S');
		}
	}

    /**
     * @return The level of this map
     */
//    public int get_level () {
//        return this.level;
//    }
//    public void set_level (int level) {
//
//        this.level = level;
//        
//	    switch (level) {
//
//		case 1: {
//			this.matrix = new char [][] {
//				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
//				{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
//				{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
//				{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
//				{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
//				{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
//				{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
//				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
//			};
//			break;
//		}
//
//
//		case 2: {
//			this.matrix = new char [][] {
//				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
//				{'I', ' ', ' ', ' ', 'O', '*', ' ', ' ', 'k', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
//				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
//			};
//            break;
//		}
//
//		}
//    }




}
