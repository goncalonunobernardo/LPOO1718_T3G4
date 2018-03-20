package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Coordinates;
import dkeep.logic.Dungeon;
import dkeep.logic.Game;
import dkeep.logic.GameState;
import dkeep.logic.Keep;
import dkeep.logic.Person;

public class TestDungeonGameLogic {
	
	char[][] map_test1 = {	{'X', 'X', 'X', 'X', 'X'},
							{'X', 'H', ' ', 'G', 'X'},
							{'I', ' ', ' ', ' ', 'X'},
							{'I', 'k', ' ', ' ', 'X'},
							{'X', 'X', 'X', 'X', 'X'}};
	
	char [][] map_test2 = {	{ 'X', 'X', 'X', 'X', 'X' },
							{ 'X', 'A', ' ', '*', 'X' },
							{ 'I', ' ', ' ', 'O', 'X' }, 
							{ 'I', 'k', ' ', ' ', 'X' },
							{ 'X', 'X', 'X', 'X', 'X' }};
	
	char [][] map_test3 = {	{ 'X', 'X', 'X', 'X', 'X', 'X' },
							{ 'X', 'A', ' ', ' ', ' ', 'X'},
							{ 'X', ' ', ' ', 'O', '*', 'X' },
							{ 'I', ' ', ' ', ' ', ' ', 'X' }, 
							{ 'I', 'k', ' ', ' ', ' ', 'X' },
							{ 'X', 'X', 'X', 'X', ' ', 'X' }};

	@Test
	public void testHeroMoveIntoToFreeCell() {
		Game game = new Game(new Dungeon (map_test1));
		
		assertEquals ('H', game.get_hero().get_symbol());
		assertEquals(game.get_map().get_letter(new Coordinates (1, 1)), game.get_hero().get_symbol());
		game.play('s');
		assertEquals(game.get_map().get_letter(new Coordinates (1, 2)), game.get_hero().get_symbol());
	}

	@Test
	public void testHeroAgaisntWall() {
		Game game = new Game( new Dungeon (map_test1));
		
		assertEquals(game.get_map().get_letter(new Coordinates (1, 1)), game.get_hero().get_symbol());
		game.play('w');
		assertFalse(game.get_map().get_letter(new Coordinates (0, 1)) == game.get_hero().get_symbol());
		assertTrue(game.get_map().get_letter(new Coordinates (0, 1)) == 'X');
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		Game game = new Game( new Dungeon (map_test1));
		assertFalse (game.check_game_over());
		game.play('d');
		assertEquals (GameState.LOST, game.get_game_status());
		assertTrue(game.check_game_over());
	}
	
	@Test
	public void testHeroClosedDoor() {
		Game game = new Game( new Dungeon (map_test1));
		Coordinates hero_coord = new Coordinates (0, 3);

		game.play('s');
		game.play('a');
		assertFalse(game.get_map().get_letter(hero_coord) == game.get_hero().get_symbol());
		assertEquals(game.get_map().get_letter(hero_coord), 'I');
		assertFalse (GameState.LOST == game.get_game_status());
		assertFalse (GameState.WON == game.get_game_status());
		assertFalse("Test Game Over", game.check_game_over());
	}
	
	@Test
	public void testHeroOpensDoor() {
		Game game = new Game(new Dungeon (map_test1));

		game.play('s');
		game.play('s');
		assertEquals(game.get_map().get_letter(new Coordinates (0, 2)), 'S');
		assertEquals(game.get_map().get_letter(new Coordinates (0, 3)), 'S');
	}
	
	@Test
	public void testHeroWinsLevelDungeon() {
		Game game = new Game( new Dungeon (map_test1));

		game.play('s');
		game.play('s');
		assertEquals(game.get_map().get_letter( new Coordinates (0, 2)), 'S');
		assertEquals(game.get_map().get_letter(new Coordinates (0, 3)), 'S');
		assertEquals(game.get_map().get_letter( new Coordinates (1, 3)), game.get_hero().get_symbol());

		game.play('a');
		assertEquals (GameState.WON, game.get_game_status());
		assertTrue(game.check_game_over());
	}
	

	@Test
	public void testHeroIsCapturedByOgre() {
		Keep level = new Keep (map_test2);
		Game game = new Game (level);
		
		assertFalse (game.check_game_over());
		level.move_hero('d');
		assertEquals (GameState.LOST, game.get_game_status());
		assertTrue (game.check_game_over());
		
	}
	
	@Test
	public void testHeroGrabbingKey() {
		Keep level = new Keep (map_test2);
		Game game = new Game (level);

		assertEquals("Test hero with symbol A",'A', game.get_hero().get_symbol());
		assertFalse ("Test game not over", game.check_game_over());
		level.move_hero('s');
		level.move_hero('s');
		assertEquals("Test hero with symbol K: ",'K', game.get_hero().get_symbol());
	}
	
	@Test
	public void testOpenDoorWithoutKey () {
		Keep level = new Keep (map_test2);
		Game game = new Game (level);
		Coordinates door_coord = new Coordinates (0, 2);
		
		assertFalse ("Test game not over", game.check_game_over());
		level.move_hero('s');
		level.move_hero('a');
		assertFalse ("Test game not over", game.check_game_over());
		assertEquals ("Door still closed", 'I', game.get_map().get_letter(door_coord));
	}
	
	@Test
	public void testOpenDoorWithKey () {
		Keep level = new Keep (map_test2);
		Game game = new Game ( level);
		Coordinates door_coord = new Coordinates (0, 3);
	
		assertFalse ("Test game not over", game.check_game_over());
		level.move_hero('s');
		level.move_hero('s');
		level.move_hero('a');
		assertEquals ("Door opened", game.get_hero().get_symbol(), game.get_map().get_letter(door_coord));
	}
	
	@Test
	public void testOpenDoorAndWin () {
		Keep level = new Keep (map_test2);
		Game game = new Game ( level);
		
		assertFalse ("Test game not over", game.check_game_over());
		game.play('s');
		game.play('s');
		game.play('a');
		assertTrue ("Test game over", game.check_game_over());
		assertEquals (GameState.WON, game.get_game_status());
	}
	
	@Test (timeout = 1000)
	public void testSomeRandomBehaviour() {
		Keep level = new Keep (map_test3);
		Game game = new Game (level);
		
		Person ogre = level.get_ogres().elementAt(0);
		
		boolean move_down = false, move_up = false, move_right = false, move_left = false;
		

		assertFalse ("Test game not over", game.check_game_over());
		
		while (!move_down || !move_up || !move_right || !move_left) {
			
			Coordinates initial = new Coordinates (ogre.get_coordinates());
			
			game.play('a');
			
			if (ogre.get_coordinates().equals(new Coordinates (initial.get_x() + 1, initial.get_y())))
				move_right = true;
			
			else if (ogre.get_coordinates().equals(new Coordinates (initial.get_x() - 1, initial.get_y())))
				move_left = true;
			
			else if (ogre.get_coordinates().equals(new Coordinates (initial.get_x(), initial.get_y() + 1)))
				move_down = true;
			
			else if (ogre.get_coordinates().equals(new Coordinates (initial.get_x(), initial.get_y() - 1)))
				move_up = true;
			
			else
				fail ("Behaviour not expected");
			
		}
	}
	
}

	
	

		
		
		

