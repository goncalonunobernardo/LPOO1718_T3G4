package dkeep.logic;

public interface GameLogic {
	
	public Hero get_hero();

	public Map get_map();

	public GameState move (char key);
	
	public GameLogic updateLevel();
	
	
}
