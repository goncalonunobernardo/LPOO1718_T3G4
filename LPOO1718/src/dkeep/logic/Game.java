package dkeep.logic;

public class Game {
	
	GameLogic [] levels;
	int current_level;
	GameState status;
	
	public Game (GameLogic [] levels) {
		this.levels = levels;
		this.current_level = 0;
		this.status = GameState.PLAYING;
	}
	
	public Game () {
		this(new GameLogic [] {new Dungeon(), new Keep()});
	}
	
	public boolean check_game_over() {
		return !this.get_game_status().equals(GameState.PLAYING);
	}
	
	public GameState get_game_status () {
		status = levels[current_level].get_status();
		return status;
	}
	
	public Hero get_hero() {
		return levels[current_level].get_hero();
	}
	
	public Map get_map() {
		return levels[current_level].get_map();
	}
	
	public void play (char key) {
		
		if (status == GameState.PLAYING) {
			levels[current_level].move(key);
		}
	}
	
	public void updateLevel () {
		
		if (status != GameState.PLAYING) {
			System.out.print (this.get_map());
		}
		
		if (status == GameState.LOST)
			System.out.println("You lose!");

		else if (status == GameState.WON) {
			
			current_level++;
			
			if (current_level < levels.length) {
				System.out.println ("Next level!");
				status = GameState.PLAYING;
			}
			else
				System.out.println ("You win!");
		}
	}
}
