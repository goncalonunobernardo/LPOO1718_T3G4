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
		this (new Coordinates (x_pos, y_pos), symbol, movement);
	}
	
	Guard (Coordinates coord, char symbol, String movement) {
		super (coord, symbol);
		this.movement = movement;
		this.count_string = 0;
	}


	
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
