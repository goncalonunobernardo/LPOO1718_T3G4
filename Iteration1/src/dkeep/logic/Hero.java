package dkeep.logic;
/**
 * @class Hero 
 * @extends Person
 * @brief Abstraction of the hero of the game. It allows to move the hero on the map and to know if the player won or lost. 
 */
public class Hero extends Person {

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
			
			if (check_near (map.get_guard(), map) || check_near (map.get_ogre(), map) || check_near(map.get_club(), map)) {
				map.set_lose();
			}
			else if (check_win(map)) {
				map.set_win();
			}
			
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
	 * @brief Checks if the player has lost, meaning if the hero is on one of the adjacent squares (except diagonals) to the enemy (guard, ogre or club)
	 * @param enemy Character that makes the player lose - club, ogre or guard depending on the level
	 * @param map The map of the game
	 * @return true, if the player is near the enemy; false, otherwise
	 */
	public boolean check_near (Person enemy, Map map) {
		
		if (enemy != null) {
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
		return false;
	}
}
