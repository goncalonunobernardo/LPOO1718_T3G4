package dkeep.logic;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Abstraction of a character of the game (ex: hero, guard). It saves a character's position and its symbol on the map. 
 * It allows to change its position according to a key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
 * @class Person
 */
public class Person implements Serializable{
	
	private Coordinates coord;			/** Person's coordinates */
	private char symbol;					/** Symbol of the character on the map.*/
	private char key_symbol;				/** Symbol drawn on the map when the character has the same coordinates of the key*/
	
	/**
	 * Constructs an Person given its coordinates, its symbol on the map and its symbol when above the key
	 * @param coord Coordinate of Person
	 * @param symbol The symbol of the character on the map
	 * @param key_symbol Character when above the key
	 */
	public Person (Coordinates coord, char symbol, char key_symbol) {
		this.coord = coord;
		this.symbol = symbol;
		this.key_symbol = key_symbol;
	}

	/**
	 * Obtains current coordinates of a Person
	 * @return Coordinates
	 */
	public Coordinates get_coordinates() {
		return this.coord;
	}

	/**
	 * Obtains corresponding symbol of the desired Person
	 * @return symbol of the Person
	 */
	public char get_symbol() {
		return symbol;
	}

	/**
	 * Obtains symbol drawn in the map when the character has the same coordinates of the key
	 * @return Character of Person when above the key
	 */
	public char get_key_symbol() {
		return key_symbol;
	}

	/**
	 * Changes the symbol of the character
	 * @param symbol New symbol applied
	 */
	public void set_symbol (char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Changes the position of the character by changing the its coordinates
	 * @param new_coord New coordinates of the character
	 */
	public void set_pos (Coordinates new_coord) {
		this.coord.set_pos(new_coord);
	}
	
	/**
	 * Moves the character by changing the character's coordinates
	 * @param key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @param map Map where the character is stored
	 */
	public void move_person(char key, Map map) {
		
		switch (key) {

		case 'w':
			this.coord.add_y(-1);
			break;
			
		case 'd':
			this.coord.add_x(1);
			break;

		case 'a':
			this.coord.add_x(-1);
			break;

		case 's':
			this.coord.add_y(1);
			break;

		}		
	}
	
	/**
     * Checks if the player has lost, meaning if the hero is on one of the adjacent squares (except diagonals) to the person (guard, ogre or club)
     * @param person Character that makes the player lose - club, ogre or guard depending on the level
     * @return true, if the player is near the person; false, otherwise
     */
    public boolean check_near (Hero person) {
    	
    		Coordinates this_coord = this.get_coordinates();
    		Coordinates person_coord = person.get_coordinates();

    		if (person != null) {
    			if (this_coord.same_column (person_coord) && ((person_coord.get_x() - 1) <= this_coord.get_x() && this_coord.get_x() <= (person_coord.get_x() + 1))) {
    				return true;
    			}
    			else if (this_coord.same_line (person_coord) && ((person_coord.get_y() - 1) <= this_coord.get_y() && this_coord.get_y() <= (person_coord.get_y() + 1))) {
    				return true;
    			}
    		}

        return false;
    }
}