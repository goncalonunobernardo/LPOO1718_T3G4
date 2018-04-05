package dkeep.logic;

import java.io.Serializable;

/**
 * @class Guard
 * @extends Person
 * @brief Abstraction of the Guard of the game. It allows to move the guard on the map in a planed route.
 */
@SuppressWarnings("serial")
public class Guard extends Person implements Serializable{

	protected String movement;					/**@brief The route of the guard on the map*/
	protected int count_string;					/**@brief To know in which key of the movement the guard currently is*/

	/**
	 * @brief Constructor of the Guard object
	 * @param coord coordinates of Guard
	 * @param symbol Char that represents the guard on the map
	 * @param movement String where the guard's movement is stored: 
	 * only contains the chars 'a' (1 to the left), 'd' (1 to the right), 's' (1 down), 'w' ( 1 up)
	 */
	public Guard (Coordinates coord, char symbol, String movement) {
		super (coord, symbol, ' ');
		this.movement = movement;
		this.count_string = 0;
	}


	/**
	 * @brief moves the Guard as it extends himself as a Person
	 * @param key The key is irrelevant because the guard will be moved according to the string specified on the constructor
	 * Allows to override the move method
	 * @param map Map where the character is stored
	 */
	@Override
	public void move_person (char key, Map map) {
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		if (this.movement != "") {
			super.move_person(movement.charAt(count_string), map);

			if (map.get_letter(this.get_coordinates()) != ' ') {
				this.set_pos(initial_coord);
			}

			if (count_string == this.movement.length() - 1)
				count_string = 0;
			else count_string++;
		}
	}
}
