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
	public void move_person (char key, Map map) {
		
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		super.move_person(key, map);
		
		char current = map.get_letter(this.get_coordinates());
		
		if (current == 'k') {
			map.open_doors();
			this.set_symbol('K');
		}
		else if (current == 'S') {
			this.win = true;
		}
		else if (current != ' ') {
			this.set_pos(initial_coord);
		}
	}
	
	public boolean check_win () {
		return this.win;
	}
}
