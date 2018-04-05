package dkeep.logic;

/**
 * @interface GameLogic
 * @brief Abstraction that allows for the game's class to save the levels 
 * without knowing the specifics of each one. It only contains the methods that needed in the game
 */
public interface GameLogic {
	
	public Hero get_hero();

	public Map get_map();

	public void move (char key);
	
	public GameState get_status ();
}
