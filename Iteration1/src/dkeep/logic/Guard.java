package dkeep.logic;
/**
 * @class Hero 
 * @extends Person
 * @brief Abstraction of the hero of the game. It allows to move the guard on the map in a planed route. 
 */
public class Guard extends Person {

	protected String movement;					/**@brief The route of the guard on the map*/
	protected int count_string;				/**@brief To know in which key of the movement the guard currently is*/

	/**
	 * @brief Class constructor
	 * @param x_pos Initial x coordinate of the guard on the map
	 * @param y_pos Initial y coordinate of the guard on the map
	 * @param symbol The initial symbol that represents the guard on the map
	 */
	Guard (int x_pos, int y_pos, char symbol, String movement) {
		super (x_pos, y_pos, symbol);
		this.movement = movement;
	}

	
	@Override
	public void move_person (char key, Map map) {
		int old_x = this.get_x_pos();
		int old_y = this.get_y_pos();
		
		super.move_person(movement.charAt(count_string), map);
		
		if (map.get_letter(get_x_pos(), get_y_pos()) != ' ') {
			this.set_pos(old_x, old_y);
		}

		if (count_string == this.movement.length() - 1)
			count_string = 0;
		else count_string++;
	}
}
