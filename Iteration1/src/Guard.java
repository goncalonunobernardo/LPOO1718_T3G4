/**
 * @class Hero 
 * @extends Person
 * @brief Abstraction of the hero of the game. It allows to move the guard on the map in a planed route. 
 */
public class Guard extends Person {
	
	private String movement;					/**@brief The route of the guard on the map*/
	private int count_string;				/**@brief To know in which key of the movement the guard currently is*/
	
	/**
	 * @brief Class constructor
	 * @param x_pos Initial x coordinate of the guard on the map
	 * @param y_pos Initial y coordinate of the guard on the map
	 * @param symbol The initial symbol that represents the guard on the map
	 */
	Guard (int x_pos, int y_pos, char symbol) {
		super (x_pos, y_pos, symbol);
		this.movement = "assssaaaaaasdddddddwwwww";
	}
	
	@Override
	public boolean move_person (char key, Map map) {
		
		if (super.move_person(movement.charAt(count_string), map)) {
			if (map.get_letter(get_x_pos(), get_y_pos()) == 'S')
				return false;
			
			if (count_string == this.movement.length() - 1) 
				count_string = 0;
			else count_string++;
			
			return true;
		}
		
		return false;
		
	}
}
