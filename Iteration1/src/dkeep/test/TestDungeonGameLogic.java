package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Coordinates;
import dkeep.logic.Game;
import dkeep.logic.Map;

public class TestDungeonGameLogic {
	
	char[][] map_test1 = {	{'X', 'X', 'X', 'X', 'X'},
							{'X', 'H', ' ', 'G', 'X'},
							{'I', ' ', ' ', ' ', 'X'},
							{'I', 'k', ' ', ' ', 'X'},
							{'X', 'X', 'X', 'X', 'X'}};
	
	char [][] map_test2 = {	{ 'X', 'X', 'X', 'X', 'X' },
							{ 'X', 'A', ' ', 'O', 'X' },
							{ 'I', ' ', ' ', ' ', 'X' }, 
							{ 'I', 'k', ' ', ' ', 'X' },
							{ 'X', 'X', 'X', 'X', 'X' }};

	@Test
	public void testHeroMoveIntoToFreeCell() {
		Map map_test = new Map(map_test1);
		Game game = new Game(map_test);
		
		assertEquals ('H', game.get_hero().get_symbol());
		assertEquals(map_test.get_letter(new Coordinates (1, 1)), game.get_hero().get_symbol());
		game.move_hero('s');
		game.get_map().print();
		assertEquals(map_test.get_letter(new Coordinates (1, 2)), game.get_hero().get_symbol());
	}

	@Test
	public void testHeroAgaisntWall() {
		Map map_test = new Map(map_test1);
		Game game = new Game(map_test);
		
		assertEquals(map_test.get_letter(new Coordinates (1, 1)), game.get_hero().get_symbol());
		game.move_hero('w');
		assertFalse(map_test.get_letter(new Coordinates (0, 1)) == game.get_hero().get_symbol());
		assertTrue(map_test.get_letter(new Coordinates (0, 1)) == 'X');
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		Map map_test = new Map(map_test1);
		Game game = new Game(map_test);
		
		assertFalse(game.check_lose());
		game.move_hero('d');
		assertTrue(game.check_lose());
		assertTrue(game.check_game_over());
	}
	
	@Test
	public void testHeroClosedDoor() {
		Map map_test = new Map(map_test1);
		Game game = new Game(map_test);

		game.move_hero('s');
		game.move_hero('a');
		assertFalse(map_test.get_letter(new Coordinates (0, 3)) == game.get_hero().get_symbol());
		assertEquals(map_test.get_letter(new Coordinates (0, 3)), 'I');
		assertEquals(map_test.get_letter(new Coordinates (0, 3)), 'I');
		assertFalse(game.check_lose());
		assertFalse(game.check_game_over());
	}
	
	@Test
	public void testHeroOpensDoor() {
		Map map_test = new Map(map_test1);
		Game game = new Game(map_test);

		game.move_hero('s');
		game.move_hero('s');
		assertEquals(map_test.get_letter(new Coordinates (0, 2)), 'S');
		assertEquals(map_test.get_letter(new Coordinates (0, 3)), 'S');
	}
	
	@Test
	public void testHeroWinsLevelDungeon() {
		Map map_test = new Map(map_test1);
		Game game = new Game(map_test);

		game.move_hero('s');
		game.move_hero('s');
		assertEquals(map_test.get_letter( new Coordinates (0, 2)), 'S');
		assertEquals(map_test.get_letter(new Coordinates (0, 3)), 'S');
		assertEquals(map_test.get_letter( new Coordinates (1, 3)), game.get_hero().get_symbol());

		game.move_hero('a');
		assertTrue(!game.check_lose());
		//assertTrue(game.check_win());
		//assertTrue(game.check_game_over());
	}
	

	@Test
	public void testHeroIsCapturedByOgre() {
		Map gameMap = new Map (map_test1);
		Game game = new Game (gameMap);
		
		assertFalse (game.check_game_over());
		game.move_hero('d');
		assertTrue (game.check_lose());
		assertTrue (game.check_game_over());
		
	}
	
	@Test
	public void testHeroGrabbingKey() {
		Map gameMap = new Map (map_test2);
		Game game = new Game (gameMap);

		assertEquals("Test hero with symbol A",'A', game.get_hero().get_symbol());
		assertFalse ("Test game not over", game.check_game_over());
		game.move_hero('s');
		game.move_hero('s');
		assertEquals("Test hero with symbol K: ",'K', game.get_hero().get_symbol());
	}
	
	@Test
	public void testOpenDoorWithoutKey () {
		Map gameMap = new Map (map_test2);
		Game game = new Game (gameMap);
		
		assertFalse ("Test game not over", game.check_game_over());
		game.move_hero('s');
		game.move_hero('a');
		assertFalse ("Test game not over", game.check_game_over());
		
		game.move_hero('d');
		assertEquals ("Door still closed", 'I', game.get_map().get_letter(new Coordinates (0,2)));
	}
	
	@Test
	public void testOpenDoorWithKey () {
		Map gameMap = new Map (map_test2);
		Game game = new Game (gameMap);
		
		assertFalse ("Test game not over", game.check_game_over());
		game.move_hero('s');
		game.move_hero('s');
		game.move_hero('a');
		assertEquals ("Door opened", game.get_hero().get_symbol(), game.get_map().get_letter(new Coordinates (0, 3)));
	}
	
	@Test
	public void testOpenDoorAndWin () {
		Map gameMap = new Map (map_test2);
		Game game = new Game (gameMap);
		
		assertFalse ("Test game not over", game.check_game_over());
		game.move_hero('s');
		game.move_hero('s');
		game.move_hero('a');
		assertTrue ("Test game over", game.check_game_over());
		assertTrue ("Test win", game.check_win());
	}
	
}

	
	

		
		
		

