package dkeep.logic;

import java.io.Serializable;

/**
 * @class Person
 * @brief Abstraction of a character of the game (ex: hero, guard). It saves a character's position and its symbol on the map. 
 * It allows to change its position according to a key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
 */
public class Person implements Serializable{
	
	private Coordinates coord;
	private char symbol;					/** @brief Symbol of the character on the map.*/
	private char key_symbol;
	
	/**
	 * @brief Class' constructor
	 * @param coord Coordinate of Person
	 * @param symbol The symbol of the character on the map
	 * @param key_symbol Character when stepping in a key
	 */
	public Person (Coordinates coord, char symbol, char key_symbol) {
		this.coord = coord;
		this.symbol = symbol;
		this.key_symbol = key_symbol;
	}

	/**
	 * @brief Obtains current coordinates of a Person
	 * @return Coordinates
	 */
	public Coordinates get_coordinates() {
		return this.coord;
	}

	/**
	 * @brief Obtains corresponding symbol of the desired Person
	 * @return symbol of the Person
	 */
	public char get_symbol() {
		return symbol;
	}

	/**
	 * @brief Obtains symbol when stepping on a key
	 * @return Character of Person with key
	 */
	public char get_key_symbol() {
		return key_symbol;
	}

	/**
	 * @brief Sets the next symbol for the Person if it changed her attributes
	 * @param symbol new symbol applied
	 */
	public void set_symbol (char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * @brief Changes the position of the character by changing the character's attributes
	 * @param new_coord New coordinates of the character
	 */
	public void set_pos (Coordinates new_coord) {
		this.coord.set_pos(new_coord);
	}
	
	/**
	 * @brief Moves the character by changing the character's attributes
	 * @param key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @param map Map where the character is stored
	 * @return true, if the new position is valid (not a wall or a close door); false, otherwise
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
     * @brief Checks if the player has lost, meaning if the hero is on one of the adjacent squares (except diagonals) to the person (guard, ogre or club)
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