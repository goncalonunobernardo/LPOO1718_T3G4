package dkeep.logic;
/**
 * @class Hero 
 * @extends Person
 * @brief Abstraction of the hero of the game. It allows to move the hero on the map and to know if the player won or lost. 
 */
public class Hero extends Person {
	
	private boolean win;
	
	Hero (Coordinates coord, char symbol, char key_symbol) {
		super (coord, symbol, key_symbol);
		this.win = false;
	}

	@Override
	public void move_person (char key, Map map) {
		
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		super.move_person(key, map);
		
		char current = map.get_letter(this.get_coordinates());
		
		if (current == 'S') {
			this.win = true;
		}
		else if (map.not_empty(get_coordinates())) {
			this.set_pos(initial_coord);
		}
	}
	
	public boolean check_win () {
		return this.win;
	}
}
