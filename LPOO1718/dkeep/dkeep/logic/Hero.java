package dkeep.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * @class Hero 
 * @extends Person
 * Abstraction of the hero of the game. It allows to move the hero on the map and to know if the player won or lost. 
 */
public class Hero extends Person implements Serializable{
	
	private boolean win; 			/**@brief Bool if wins game*/

	/**
	 * Constructor for Hero object
	 * @param coord coordinates of Hero
	 * @param symbol Char symbol of Hero on the map
	 * @param key_symbol Symbol that will be drawn on the map when the hero is above the key's map
	 */
	Hero (Coordinates coord, char symbol, char key_symbol) {
		super (coord, symbol, key_symbol);
		this.win = false;
	}

	/**
	 * Moves Hero in the current level and enables passage to next level
	 * @param key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @param map Map where the character is stored
	 */
	@Override
	public void move_person (char key, Map map) {
		
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		super.move_person(key, map);
		
		char current = map.get_letter(this.get_coordinates());
		
		if (current == 'S') {
			this.win = true;
		}
		else if (map.not_empty(get_coordinates())) {
			this.set_pos(initial_coord);
		}
	}

	/**
	 * Checks if game is won
	 * @return win- bool in which confirms if game is won or not
	 */
	public boolean check_win () {
		return this.win;
	}
}
