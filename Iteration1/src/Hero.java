/**
 * @class Hero 
 * @extends Person
 * @brief Abstraction of the hero of the game. It allows to move the hero on the map and to know if the player won or lost. 
 */
public class Hero extends Person {
	
	boolean win = false, lose = false;			/** @brief Boolean variables to know if the game ended or not*/

	/**
	 * @brief Class constructor
	 * @param x_pos Initial x coordinate of the hero on the map
	 * @param y_pos Initial y coordinate of the hero on the map
	 * @param symbol The initial symbol that represents the hero on the map
	 */
	Hero (int x_pos, int y_pos, char symbol) {
		super (x_pos, y_pos, symbol);
	}
	

	@Override
	public boolean move_person (char key, Map map) {
		
		if (super.move_person(key, map)) {
			
			if (map.get_letter(get_x_pos(), get_y_pos()) == 'k') {
				map.open_doors();
			}
			
			if (map.get_level() == 1) {
				lose = check_near (map.get_guard(), map);
			}
			else {
				lose = check_near (map.get_ogre(), map);
			}
			
			win = check_win(map);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * @brief Checks if the player has won, meaning if the hero has reached the exit doors
	 * @param map The map of the game
	 * @return true, if the player won; false, otherwise
	 */
	public boolean check_win (Map map) {
		return (map.get_letter(get_x_pos(), get_y_pos()) == 'S');
	}
	
	/**
	 * @brief Checks if the player has lost, meaning if the hero is on one of the adjacent squares to the guard or to the ogre (except diagonals)
	 * @param enemy Character that makes the player lose - ogre or guard depending on the level
	 * @param map The map of the game
	 * @return true, if the player is near the guard/ogre; false, otherwise
	 */
	public boolean check_near (Person enemy, Map map) {
		int enemy_x = enemy.get_x_pos();
		int enemy_y = enemy.get_y_pos();
		
		//mesma coluna do guarda e nas 3 posicoes "perto"
		if (this.get_y_pos() == enemy_y && ((enemy_x - 1) <= this.get_x_pos() && this.get_x_pos() <= (enemy_x + 1))) {
			return true;
		}
		//mesma linha do guarda e nas 3 posicoes "perto"
		else if (this.get_x_pos() == enemy_x && ((enemy_y - 1) <= this.get_y_pos() && this.get_y_pos() <= (enemy_y + 1))) {
			return true;
		}
		return false;
	}
	
	/**
	 * @brief Checks if the player has either won or lost, and prints a different message depending on the case
	 * @param map The map of the game
	 * @return true, if the game should continue; false, if the player lost or won, therefore the game must stop
	 */
	public boolean check_playing (Map map) {
		if (lose) {
			map.print();
			System.out.println("You lose!!");
			return false;
		}
		else if (win && map.get_level() == 1) {
			return false;
		}
		else if (win && map.get_level() == 2) {
			map.print();
			System.out.println("You win!!");
			return false;
		}
		
		return true;
	}
}
