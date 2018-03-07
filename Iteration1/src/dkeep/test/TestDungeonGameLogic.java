package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.Map;
import dkeep.logic.Hero;

public class TestDungeonGameLogic {
	
	char[][] map = {{'X', 'X', 'X', 'X', 'X'},
				{'X', 'H', ' ', 'G', 'X'},
				{'I', ' ', ' ', ' ', 'X'},
				{'I', 'k', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X'}};
	
	@Test
	public void testHeroMoveIntoToFreeCell() {
		Map map_test = new Map(map);
		Game game = new Game(map_test);
		
		assertEquals(map_test.get_letter(1, 1), game.get_hero().get_symbol());
		game.move_hero('s');
		assertEquals(map_test.get_letter(1, 2), game.get_hero().get_symbol());
	}

	@Test
	public void testHeroAgaisntWall() {
		Map map_test = new Map(map);
		Game game = new Game(map_test);
		
		assertEquals(map_test.get_letter(1, 1), game.get_hero().get_symbol());
		game.move_hero('w');
		assertFalse(map_test.get_letter(0, 1) == game.get_hero().get_symbol());
		assertTrue(map_test.get_letter(0, 1) == 'X');
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		Map map_test = new Map(map);
		Game game = new Game(map_test);
		
		assertFalse(game.check_lose());
		game.move_hero('d');
		assertTrue(game.check_lose());
		assertTrue(game.check_game_over());
	}
	
	@Test
	public void testHeroClosedDoor() {
		Map map_test = new Map(map);
		Game game = new Game(map_test);

		game.move_hero('s');
		game.move_hero('a');
		assertFalse(map_test.get_letter(2, 0) == game.get_hero().get_symbol());
		assertEquals(map_test.get_letter(0, 2), 'I');
		assertEquals(map_test.get_letter(0, 3), 'I');
		assertFalse(game.check_lose());
		assertFalse(game.check_game_over());
	}
	
	@Test
	public void testHeroOpensDoor() {
		Map map_test = new Map(map);
		Game game = new Game(map_test);

		game.move_hero('s');
		game.move_hero('s');
		assertEquals(map_test.get_letter(0, 2), 'S');
		assertEquals(map_test.get_letter(0, 3), 'S');
	}
	
	@Test
	public void testHeroWinsLevelDungeon() {
		Map map_test = new Map(map);
		Game game = new Game(map_test);

		game.move_hero('s');
		game.move_hero('s');
		assertEquals(map_test.get_letter(0, 2), 'S');
		assertEquals(map_test.get_letter(0, 3), 'S');
		assertEquals(map_test.get_letter(1, 3), game.get_hero().get_symbol());

		game.move_hero('a');
		assertTrue(!game.check_lose());
		assertTrue(game.get_hero().check_win());
		//assertTrue(game.check_game_over());
	}
}
