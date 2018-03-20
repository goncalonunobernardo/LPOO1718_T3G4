package dkeep.logic;

import java.util.Random;

public class Dungeon implements GameLogic {
	
	private Map map;
	private Hero hero;
	private Guard guard;
	private GameLogic nextLevel;
	
	public Dungeon (char[][] matrix) {
		this.map = new Map (matrix);
		this.hero = new Hero (map.search_char('H'), 'H');
		this.guard = new Guard (map.search_char('G'), 'G', "");
		this.nextLevel = null;
	}
	
	Dungeon () {
		
		char [][] map_1 = new char [][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
		
		this.map = new Map (map_1);
		
		this.hero = new Hero (1, 1, 'H');
		
		this.nextLevel = new Keep();
		
		Random r = new Random();
		
		switch (r.nextInt(3)) {
		
		case 0: this.guard = new Guard (8, 1, 'G', "assssaaaaaasdddddddwwwww");
				break;
				
		case 1: this.guard = new Suspicious (8, 1, 'G', "assssaaaaaasdddddddwwwww", 10);
				break;
				
		case 2: this.guard = new Drunken (8, 1, 'G', "assssaaaaaasdddddddwwwww", 10);
				break;
			
		}
	}

	/**
	 * @return The hero of this game
	 */
	public Hero get_hero() {
		return hero;
	}
	
	public Guard get_guard() {
		return guard;
	}

	public Map get_map() {
		return this.map;
	}
	
	/**
	 * @brief Moves the hero, the guard or the ogre and the club of the game, by calling move_person and checks if the game is over
	 * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @return true, if the game ended; false, otherwise
	 */
	public void move (char key) {

		move_hero(key);
		

		move_guard();
		
	}
	
	public GameState get_status () {
		if (guard.check_near(get_hero())) 
			return GameState.LOST;
		
		
		if (hero.check_win())
			return GameState.WON;
		
		return GameState.PLAYING;
	}

	public void move_hero(char key) {
		map.reset_person(get_hero());
		
		hero.move_person(key, map);
		
		map.draw_person(get_hero());
	}

	public void move_guard () {
		map.reset_person(get_guard());
		
		guard.move_person(' ', get_map());
		
		map.draw_person(get_guard());
	}
	
	public GameLogic updateLevel () {
		return this.nextLevel;
	}
}
