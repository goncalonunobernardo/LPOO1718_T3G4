package dkeep.logic;
/**
 * @class Person
 * @brief Abstraction of a character of the game (ex: hero, guard). It saves a character's position and its symbol on the map. 
 * It allows to change its position according to a key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
 */
public class Person {
	
	private int x_pos, y_pos;			/** @brief Position of the character on the map. It varies from 0 to 9*/
	private char symbol;					/** @brief Symbol of the character on the map.*/
	
	/**
	 * @brief Class' constructor
	 * @param x_pos The x coordinate of the character on the map. It varies from 0 to 9
	 * @param y_pos The y coordinate of the character on the map. It varies from 0 to 9
	 * @param symbol The symbol of the character on the map
	 */
	Person (int x_pos, int y_pos, char symbol) {
		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.symbol = symbol;
	}
	
	/**
	 * @return The x coordinate of the character on the map. It varies from 0 to 9
	 */
	public int get_x_pos () {
		return x_pos;
	}
	
	/**
	 * @return The y coordinate of the character on the map. It varies from 0 to 9
	 */
	public int get_y_pos () {
		return y_pos;
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
	public void set_pos (int x, int y) {
		this.x_pos = x;
		this.y_pos = y;
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
			this.y_pos -= 1;
			break;
			
		case 'd':
			this.x_pos += 1;
			break;

		case 'a':
			this.x_pos -= 1;
			break;

		case 's':
			this.y_pos += 1;
			break;

		}		
	}
	
	/**
     * @brief Checks if the player has lost, meaning if the hero is on one of the adjacent squares (except diagonals) to the person (guard, ogre or club)
     * @param person Character that makes the player lose - club, ogre or guard depending on the level
     * @param map The map of the game
     * @return true, if the player is near the person; false, otherwise
     */
    
    public boolean check_near (Person person) {

        if (person != null) {
            int person_x = person.get_x_pos();
            int person_y = person.get_y_pos();

            //mesma coluna, mesma posição ou nas 2 posições adjacentes
            if (this.get_y_pos() == person_y && ((person_x - 1) <= this.get_x_pos() && this.get_x_pos() <= (person_x + 1))) {
                return true;
            }
            //mesma linha, mesma posição ou nas 2 posições adjacentes
            else if (this.get_x_pos() == person_x && ((person_y - 1) <= this.get_y_pos() && this.get_y_pos() <= (person_y + 1))) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    
    public void reset_person (Map map) {
    		map.set_letter(this.x_pos, this.y_pos, ' ');
    }
}