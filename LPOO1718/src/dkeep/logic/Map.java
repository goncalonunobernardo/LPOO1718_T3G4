package dkeep.logic;

/**
 * 
 * @class Map
 * @brief Abstraction of a map. It saves the letters of each position of the map, the hero and the guard of the game
 * It allows to change some character's positions, to move characters and to print the map.
 */
public class Map {
    private char matrix[][];		/** @brief Where the letters of each position of the map are stored*/

    /**
	 * @brief Depending on the level, the constructor gives different values to the matrix, hero and guard
	 * @param level 1 to play with the guard, 2 to play with the ogre
	 */
	public Map (char map[] []) {
		this.matrix = map;
	}

	@Override
	public String toString() {
		String map = "";
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
					map += matrix[i][j] + " ";
			}
			map += "\n";
		}
		
		return map;
	}

	/**
	 * @param x x coordinate of the letter to be returned
	 * @param y y coordinate of the letter to be returned
	 * @return The letter on the given positions of the matrix
	 */
	public char get_letter (Coordinates coord) {

		try {
			return matrix[coord.get_y()][coord.get_x()];
		}
		catch (IndexOutOfBoundsException a) {
			return 'X';
		}
		
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
	 * @brief Changes the doors on the left wall of the map from I to S
	 */
	public void open_doors () {
		for (int i = 0; i < matrix[0].length; i++) {
			if (get_letter (new Coordinates (0, i)) == 'I')
				set_letter (new Coordinates (0, i), 'S');
		}
	}
	
	public void reset_person (Person reset) {
		this.set_letter(reset.get_coordinates(), ' ');
	}
	
	public void draw_person (Person draw) {
		this.set_letter(draw.get_coordinates(), draw.get_symbol());
	}
	
	public Coordinates search_char (char symbol) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[j][i] == symbol)
					return new Coordinates (i, j);
			}
		}
		return null;
	}
}
