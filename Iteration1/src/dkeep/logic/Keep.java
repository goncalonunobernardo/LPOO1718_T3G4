package dkeep.logic;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class Keep implements GameLogic {
	
	private Map map;
	private Hero hero;
	private Vector <Ogre> ogres;
	
	public Keep (char[][] matrix) {
		this.map = new Map (matrix);
		this.hero = new Hero (map.search_char('H'), 'H');
		this.ogres = new Vector<Ogre> ();
		ogres.add(new Ogre (map.search_char('O'), map.search_char('*'), 'O', '*'));
	}
	
	Keep () {
		
		char[][] map_2 = new char [][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'I', ' ', ' ', ' ', 'O', '*', ' ', ' ', 'k', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
		
		this.map = new Map (map_2);
		
		this.hero = new Hero (1, 8, 'H');
		
		this.ogres = new Vector<Ogre> ();
				
		Random r = new Random ();
		
		int bound = r.nextInt(3) + 1;
		
		for (int i = 0; i < bound; i++) {
			ogres.add(new Ogre (4, 1, 'O', 5, 1, '*'));
		}
		
	}
	
	/**
	 * @return The hero of this game
	 */
	public Hero get_hero() {
		return hero;
	}
	
	public Vector <Ogre> get_ogres() {
		return this.ogres;
	}

	@Override
	public Map get_map() {
		return this.map;
	}
	
	@Override
	public GameState move (char key) {
		boolean lost = false;
		
		move_hero(key);

		move_ogres();
		
		for (Iterator <Ogre> it = ogres.iterator(); it.hasNext(); ) {
			lost |= it.next().check_near(get_hero());
		}
		
		if (lost) 
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

	public void move_ogres () {
		
		for (Iterator <Ogre> it = ogres.iterator(); it.hasNext(); ) {
			Ogre current = it.next();
			
			map.reset_person(current);
			map.reset_person(current.getClub());
			current.move_person(' ', map);
			map.draw_person(current);
			map.draw_person(current.getClub());
		}
	}
	
	@Override
	public GameLogic updateLevel () {
		return null;
	}

}
