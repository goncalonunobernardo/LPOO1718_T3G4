package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * @class Map
 * @brief Abstraction of a map. It saves the letters of each position of the map, the hero and the guard of the game
 * It allows to change some character's positions, to move characters and to print the map.
 */
@SuppressWarnings("serial")
public class Map implements Serializable {
	private Coordinates key;
	private boolean key_catched;
    private char matrix[][];		/** @brief Where the letters of each position of the map are stored*/

	/**
	 * @brief Constructor of the Map object
	 * @param map Matrix of the map
	 */
	public Map (char map[] []) {
		this.matrix = map;
		this.key_catched = false;
		key = this.search_char('k');
	}

	/**
	 * @brief Convert the map into a string, concatenating the chars separating every line with a new line
	 * @return the map in string format
	 */
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
	 * @brief Obtains the current letter in the given coordinate
	 * @param coord coordinate given in matrix
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

	/**
	 * @briefs Obtains the coordinates of the key
	 * @return Coordinates of the key
	 */
	public Coordinates get_key_coord () {
		return key;
	}

	/**
	 * @return The matrix of the map
	 */
	public char[][] get_matrix() {
		return this.matrix;
	}

	/**
	 * @brief Sets a new character to the given coordinates
	 * @param coord coordinates given
	 * @param new_letter The char to replace the letter
	 */
	public void set_letter (Coordinates coord, char new_letter) {
		
		matrix[coord.get_y()][coord.get_x()] = new_letter;
	}
	
	/**
	 * @brief Opens the doors by changing the doors on the left wall of the map from I to S
	 */
	public void open_doors () {
		for (int i = 0; i < matrix[0].length; i++) {
			if (get_letter (new Coordinates (0, i)) == 'I')
				set_letter (new Coordinates (0, i), 'S');
		}
	}

	/**
	 * @brief Resets the position of the person by replacing its symbol by a clear space
	 * @param reset The person whose symbol will be cleared
	 */
	public void reset_person (Person reset) {
		this.set_letter(reset.get_coordinates(), ' ');
	}

	/**
	 * @brief Opens doors if the hero has caught key and changes him to a new character: its key symbol
	 * @param draw The Hero to be drawn
	 */
	public void draw_hero (Hero draw) {
		if (!key_catched && draw.get_coordinates().equals(key)) {
			draw.set_symbol(draw.get_key_symbol());
			open_doors();
			key_catched = true;
		}
		this.set_letter(draw.get_coordinates(), draw.get_symbol());
	}

	/**
	 * @brief Draws the person according to its coordinates and if it is above the key or not
	 * @param draw The person to be drawn on the map
	 */
	public void draw_person (Person draw) {
		
		if (!key_catched && draw.get_coordinates().equals(key) ) 
			this.set_letter(draw.get_coordinates(), draw.get_key_symbol());
		else 
			this.set_letter(draw.get_coordinates(), draw.get_symbol());
	}

	/**
	 * @brief Draws the key of the map
	 */
	public void draw_key () {
		if (!key_catched && get_letter(key) == ' ') {
			this.set_letter(key, 'k');
		}
	}

	/**
	 * @brief Checks if a given coordinates is empty so that a person can know if it's a valid position or not
	 * @param coord The coordinates to test if are empty or not
	 * @return true if not empty, false otherwise
	 */
	public boolean not_empty (Coordinates coord) {
		return ! (get_letter(coord) == ' ' || get_letter(coord) == 'k');
	}

	/**
	 * @brief Obtains the coordinates of a character 
	 * @param symbol The char to search for in the map
	 * @return coordinates of pretended character
	 */
	public Coordinates search_char (char symbol) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == symbol)
					return new Coordinates (j, i);
			}
		}
		return null;
	}
}
