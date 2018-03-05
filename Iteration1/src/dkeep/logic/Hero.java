package dkeep.logic;
/**
 * @class Hero 
 * @extends Person
 * @brief Abstraction of the hero of the game. It allows to move the hero on the map and to know if the player won or lost. 
 */
public class Hero extends Person {
	
	private boolean win;

	/**
	 * @brief Class constructor
	 * @param x_pos Initial x coordinate of the hero on the map
	 * @param y_pos Initial y coordinate of the hero on the map
	 * @param symbol The initial symbol that represents the hero on the map
	 */
	Hero (int x_pos, int y_pos, char symbol) {
		super (x_pos, y_pos, symbol);
		this.win = false;
	}

	@Override
	public boolean move_person (char key, Map map) {
		
		if (super.move_person(key, map)) {
			
			if (map.get_letter(get_x_pos(), get_y_pos()) == 'k') {
				map.open_doors();
			}
			
			this.win = check_win(map);

			return true;
		}
		return false;
	}

	/**
	 * @brief Checks if the player has won, meaning if the hero has reached the exit doors
	 * @return true, if the player won; false, otherwise
	 */
	private boolean check_win (Map map) {
		return (map.get_letter(get_x_pos(), get_y_pos()) == 'S');
	}
	
	public boolean check_win () {
		return this.win;
	}
	
	

}
