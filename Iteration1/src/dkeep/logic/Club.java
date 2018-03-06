package dkeep.logic;
import java.util.Random;

public class Club extends Person {
	Random r;					/** @brief To allow for the movement of the club to be random*/
	
	
	/**
	 * @brief Class constructor
	 * @param x_pos Initial x coordinate of the club on the map
	 * @param y_pos Initial y coordinate of the club on the map
	 * @param symbol Symbol that represents the club on the map
	 */
	Club (int x_pos, int y_pos, char symbol) {
		super (x_pos, y_pos, symbol);
		this.r = new Random ();
	}
	
	@Override 
	public void move_person (char key, Map map) {
		String movements = "asdw";
		int initial_x = get_x_pos();
		int initial_y = get_y_pos();
		
		while (map.get_letter(get_x_pos(), get_y_pos()) != ' ') {
			this.set_pos(initial_x, initial_y);
			super.move_person(movements.charAt(r.nextInt(4)), map);
		}
		
		if (map.get_letter(get_x_pos(), get_y_pos()) == 'k') {
			this.set_symbol('$');
		}
	}

}
