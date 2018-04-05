package dkeep.logic;

/**
 * @interface GameLogic
 * @brief Allows communication in order to obtain Hero, Map, movements and gamestates
 */
public interface GameLogic {
	
	public Hero get_hero();

	public Map get_map();

	public void move (char key);
	
	public GameState get_status ();
}
