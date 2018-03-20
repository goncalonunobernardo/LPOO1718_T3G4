package dkeep.logic;
/**
 * @class Person
 * @brief Abstraction of a character of the game (ex: hero, guard). It saves a character's position and its symbol on the map. 
 * It allows to change its position according to a key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
 */
public class Person {
	
	private Coordinates coord;
	private char symbol;					/** @brief Symbol of the character on the map.*/
	
	/**
	 * @brief Class' constructor
	 * @param x_pos The x coordinate of the character on the map. It varies from 0 to 9
	 * @param y_pos The y coordinate of the character on the map. It varies from 0 to 9
	 * @param symbol The symbol of the character on the map
	 */
	Person (int x_pos, int y_pos, char symbol) {
		this.coord = new Coordinates (x_pos, y_pos);
		this.symbol = symbol;
	}
	
	Person (Coordinates coord, char symbol) {
		this.coord = coord;
		this.symbol = symbol;
	}
	
	public Coordinates get_coordinates() {
		return this.coord;
	}
	
	public char get_symbol() {
		return symbol;
	}
	
	public void set_symbol (char symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * @brief Changes the position of the character by changing the character's attributes
	 * @param x The new x coordinate of the character on the map. It varies from 0 to 9
	 * @param y The new y coordinate of the character on the map. It varies from 0 to 9
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
			this.coord.dec_y();
			break;
			
		case 'd':
			this.coord.inc_x();
			break;

		case 'a':
			this.coord.dec_x();
			break;

		case 's':
			this.coord.inc_y();
			break;

		}		
	}
	
	/**
     * @brief Checks if the player has lost, meaning if the hero is on one of the adjacent squares (except diagonals) to the person (guard, ogre or club)
     * @param person Character that makes the player lose - club, ogre or guard depending on the level
     * @param map The map of the game
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