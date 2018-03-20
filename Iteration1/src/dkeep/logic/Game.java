package dkeep.logic;

public class Game {
	
	GameLogic currentLevel;
	GameState status;

	
	public Game () {
		this.currentLevel = new Dungeon();
		this.status = GameState.PLAYING;
	}
	
	public Game (GameLogic initial) {
		this.currentLevel = initial;
		this.status = GameState.PLAYING;
	}
	
	public boolean check_game_over() {
		return !this.get_game_status().equals(GameState.PLAYING);
	}
	
	public GameState get_game_status () {
		status = currentLevel.get_status();
		return status;
	}
	
	public Hero get_hero() {
		return currentLevel.get_hero();
	}
	
	public Map get_map() {
		return currentLevel.get_map();
	}
	
	public void play (char key) {
		
		currentLevel.move(key);
	}
	
	public void updateLevel () {
		
		if (status == GameState.LOST)
			System.out.println("You lose!");

		else if (status == GameState.WON) {
			
			GameLogic aux = currentLevel.updateLevel();
			
			if (aux != null) {
				System.out.println ("Next level!");
				status = GameState.PLAYING;
				currentLevel = aux;
			}
			else
				System.out.println ("You win!");
		}
	}
	
	public void print_map () {
		currentLevel.get_map().print();
	}
}
