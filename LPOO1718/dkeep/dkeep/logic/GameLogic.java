package dkeep.logic;


/**
 * Abstraction that allows for the game's class to save the levels 
 * without knowing the specifics of each one. It only contains the methods that needed in the game
 */
public interface GameLogic {
	
	/**
	 * @return The hero of this game
	 */
	public Hero get_hero();

	/**
	 * @return The map of this game
	 */
	public Map get_map();

	/**
	 * Moves the characters of the game, by calling move_person
	 * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 */
	public void move (char key);
	
	public GameState get_status ();
}
