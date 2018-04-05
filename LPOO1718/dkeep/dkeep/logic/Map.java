package dkeep.logic;

import java.io.Serializable;

/**
 * 
 * @class Map
 * @brief Abstraction of a map. It saves the letters of each position of the map, the hero and the guard of the game
 * It allows to change some character's positions, to move characters and to print the map.
 */
public class Map implements Serializable {
	private Coordinates key;
	private boolean key_catched;
    private char matrix[][];		/** @brief Where the letters of each position of the map are stored*/

    /**
	 * @brief Depending on the level, the constructor gives different values to the matrix, hero and guard
	 * @param level 1 to play with the guard, 2 to play with the ogre
	 */
	public Map (char map[] []) {
		this.matrix = map;
		this.key_catched = false;
		key = this.search_char('k');
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
	
	public Coordinates get_key_coord (Coordinates coord) {
		return key;
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
	
	public void draw_hero (Hero draw) {
		if (!key_catched && draw.get_coordinates().equals(key)) {
			draw.set_symbol(draw.get_key_symbol());
			open_doors();
			key_catched = true;
		}
		this.set_letter(draw.get_coordinates(), draw.get_symbol());
	}
	
	public void draw_person (Person draw) {
		
		if (!key_catched && draw.get_coordinates().equals(key) ) 
			this.set_letter(draw.get_coordinates(), draw.get_key_symbol());
		else 
			this.set_letter(draw.get_coordinates(), draw.get_symbol());
	}
	
	public void draw_key () {
		if (!key_catched && get_letter(key) == ' ') {
			this.set_letter(key, 'k');
		}
	}
	
	public boolean not_empty (Coordinates coord) {
		return ! (get_letter(coord) == ' ' || get_letter(coord) == 'k');
	}
	
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
