package dkeep.logic;

public interface GameLogic {
	
	public Hero get_hero();

	public Map get_map();

	public void move (char key);
	
	public GameState get_status ();
}
