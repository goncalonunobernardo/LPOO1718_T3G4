package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Coordinates;
import dkeep.logic.Drunken;
import dkeep.logic.Dungeon;
import dkeep.logic.Game;
import dkeep.logic.GameLogic;
import dkeep.logic.GameState;
import dkeep.logic.Guard;
import dkeep.logic.Keep;
import dkeep.logic.Map;
import dkeep.logic.Ogre;
import dkeep.logic.Person;
import dkeep.logic.Suspicious;

public class TestDungeonGameLogic {
	
	char[][] map_test = {	{ 'A', 'B'}, 
							{ 'C', 'D'}};
	
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
							{ 'X', 'X', 'X', 'X', 'X', 'X' }};
	
	char[][] map_test4 = {	{'X', 'X', 'X', 'X', 'X'},
							{'X', ' ', ' ', 'G', 'X'},
							{'I', ' ', ' ', ' ', 'X'},
							{'I', ' ', ' ', ' ', 'X'},
							{'X', 'X', 'X', 'X', 'X'}};
	
	char [][] map_test5 = {	{ 'X', 'X', 'X', 'X', 'X', 'X' },
							{ 'X', ' ', ' ', ' ', ' ', 'X'},
							{ 'X', ' ', ' ', 'O', '*', 'X' },
							{ 'I', ' ', ' ', 'k', ' ', 'X' }, 
							{ 'I', ' ', ' ', ' ', ' ', 'X' },
							{ 'X', 'X', 'X', 'X', 'X', 'X' }};

	@Test
	public void testPrintMap() {
		Map map = new Map (map_test);
		
		assertEquals ("A B \nC D \n", map.toString());
		assertEquals (map_test, map.get_matrix());
	}
	
	@Test
	public void testMap () {
		Map map = new Map (map_test1);
		
		Coordinates guard_coord = map.search_char('G');
		Coordinates hero_coord = map.search_char('H');
		
		assertEquals (guard_coord, new Coordinates (3, 1));
		assertEquals (hero_coord, new Coordinates (1, 1));
		assertEquals (map.search_char('A'), null);
		
		Person guard = new Person (guard_coord, 'G', ' ');
		Person hero = new Person (hero_coord, 'H', 'K');
		
		map.reset_person(guard);
		assertEquals (map.get_letter(guard.get_coordinates()), ' ');
		
		map.reset_person(hero);
		assertEquals (map.get_letter(hero.get_coordinates()), ' ');
		
		map.draw_person(guard);
		assertEquals (map.get_letter(guard.get_coordinates()), guard.get_symbol());
		
		hero_coord.add_y(1);
		map.draw_person(hero);
		assertEquals (map.get_letter(hero.get_coordinates()), 'H');
		
		
	}
	
	@Test
	public void testDefaultGame () {
		Game game = new Game (); 
		
		assertEquals (game.get_game_status(), GameState.PLAYING);
		assertEquals (game.get_hero().get_coordinates(), new Coordinates (1, 1));
		
		game.updateLevel();
		
		assertEquals (game.get_game_status(), GameState.PLAYING);

	}
	
	@Test
	public void testGUIDefault () {
		Dungeon level1 = new Dungeon ("rookie");
		Dungeon level2 = new Dungeon ("suspicious");
		Dungeon level3 = new Dungeon ("drunken");
		
		
	}
	
	@Test
	public void testHeroMoveIntoToFreeCell() {
		Game game = new Game(new GameLogic [] {new Dungeon (map_test1)});
		
		assertEquals (1, game.get_current_level());
		assertEquals ('H', game.get_hero().get_symbol());
		assertEquals(game.get_map().get_letter(new Coordinates (1, 1)), game.get_hero().get_symbol());
		game.play('s');
		assertEquals(game.get_map().get_letter(new Coordinates (1, 2)), game.get_hero().get_symbol());
	}

	@Test
	public void testHeroAgainstWall() {
		Game game = new Game(new GameLogic [] {new Dungeon (map_test1)});
		
		assertEquals(game.get_map().get_letter(new Coordinates (1, 1)), game.get_hero().get_symbol());
		game.play('w');
		assertFalse(game.get_map().get_letter(new Coordinates (0, 1)) == game.get_hero().get_symbol());
		assertTrue(game.get_map().get_letter(new Coordinates (0, 1)) == 'X');
	}
	
	@Test
	public void testHeroIsCapturedByGuard() {
		Game game = new Game(new GameLogic [] {new Dungeon (map_test1)});
		assertFalse (game.check_game_over());
		game.play('d');
		assertEquals (GameState.LOST, game.get_game_status());
		assertTrue(game.check_game_over());
	}
	
	@Test
	public void testHeroClosedDoor() {
		Dungeon level = new Dungeon (map_test1);
		Game game = new Game(new GameLogic [] {level});
		Coordinates hero_coord = new Coordinates (0, 3);

		level.move_hero('s');
		level.move_hero('a');
		assertFalse(game.get_map().get_letter(hero_coord) == game.get_hero().get_symbol());
		assertEquals(game.get_map().get_letter(hero_coord), 'I');
		assertFalse (GameState.LOST == game.get_game_status());
		assertFalse (GameState.WON == game.get_game_status());
		assertFalse("Test Game Over", game.check_game_over());
	}
	
	@Test
	public void testHeroOpensDoor() {
		Game game = new Game(new GameLogic [] {new Dungeon (map_test1)});

		game.play('s');
		game.play('s');
		assertEquals(game.get_map().get_letter(new Coordinates (0, 2)), 'S');
		assertEquals(game.get_map().get_letter(new Coordinates (0, 3)), 'S');
	}
	
	@Test
	public void testHeroWinsLevelDungeon() {
		Game game = new Game(new GameLogic [] {new Dungeon (map_test1)});

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
	public void testGuard () {
		Map map = new Map (map_test4);
		Coordinates guard_coord = map.search_char('G');
		Guard guard = new Guard (guard_coord, 'G', "aasddw");
		
		map.reset_person(guard);
		assertEquals (guard_coord, new Coordinates (3, 1));
		guard.move_person(' ', map);
		assertEquals (guard_coord, new Coordinates (2, 1));
		guard.move_person(' ', map);
		assertEquals (guard_coord, new Coordinates (1, 1));
		guard.move_person(' ', map);
		assertEquals (guard_coord, new Coordinates (1, 2));
		guard.move_person(' ', map);
		assertEquals (guard_coord, new Coordinates (2, 2));
		guard.move_person(' ', map);
		assertEquals (guard_coord, new Coordinates (3, 2));
		guard.move_person(' ', map);
		assertEquals (guard_coord, new Coordinates (3, 1));
	}
	
	@Test (timeout = 1000)
	public void testDrunkenGuard () {
		boolean asleep = false;
		
		Map map = new Map (map_test4);
		
		Coordinates guard_coord = map.search_char('G');
		Coordinates aux = new Coordinates (guard_coord);
		
		Drunken guard = new Drunken (guard_coord, 'G', "dd", 1);
		
		while (!asleep) {
			
			aux.set_pos(guard.get_coordinates());
			
			while (guard.get_symbol() == 'G') {
				aux.set_pos(guard.get_coordinates());
				guard.move_person(' ', map);
			}
			
			while (guard.get_symbol() == 'g') {
				assertEquals (aux, guard.get_coordinates());
				asleep = true;
				guard.move_person(' ', map);
			}
		}
	}
	
	@Test (timeout = 1000)
	public void testSuspiciousGuard () {
		boolean change_direction = false;
		
		Map map = new Map (map_test4);
		
		Coordinates guard_coord = map.search_char('G');
		Coordinates aux = new Coordinates (guard_coord);
		
		Suspicious guard = new Suspicious (guard_coord, 'G', "dd", 2);
		
		while (!change_direction) {
			
			aux.set_pos(guard.get_coordinates());
			aux.add_x(-1);
			guard.move_person(' ', map);
			
			if (guard.get_coordinates().equals(aux)) {
				change_direction = true;
			}
		}
	}
	
	
	

	@Test
	public void testHeroIsCapturedByOgre() {
		Keep level = new Keep (map_test2);
		Game game = new Game (new GameLogic [] {level});
		
		assertFalse (game.check_game_over());
		level.move_hero('d');
		game.update_game_status();
		assertEquals (GameState.LOST, game.get_game_status());
		assertTrue (game.check_game_over());
		
	}
	
	@Test
	public void testHeroGrabbingKey() {
		Keep level = new Keep (map_test2);
		Game game = new Game (new GameLogic [] {level});

		assertEquals("Test hero with symbol A",'A', game.get_hero().get_symbol());
		assertFalse ("Test game not over", game.check_game_over());
		level.move_hero('s');
		level.move_hero('s');
		assertEquals("Test hero with symbol K: ",'K', game.get_hero().get_symbol());
	}
	
	@Test
	public void testOpenDoorWithoutKey () {
		Keep level = new Keep (map_test2);
		Game game = new Game (new GameLogic [] {level});
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
		Game game = new Game (new GameLogic [] {level});
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
		Game game = new Game (new GameLogic [] {level});
		
		assertFalse ("Test game not over", game.check_game_over());
		level.move_hero('s');
		level.move_hero('s');
		level.move_hero('a');
		game.update_game_status();
		assertTrue ("Test game over", game.check_game_over());
		assertEquals (GameState.WON, game.get_game_status());
	}
	
	@Test
	public void testStunnedOgre () {
		Keep level = new Keep (map_test2);
		Game game = new Game (new GameLogic [] {level});
		
		Person ogre = level.get_ogres().elementAt(0);
		
		assertFalse ("Test game not over", game.check_game_over());
		level.move_hero('s');
		level.move_hero('d');
		ogre.check_near(level.get_hero());
		assertFalse("Test game not over", game.check_game_over());
		assertEquals ("Stunned Ogre", '8', ogre.get_symbol());
	}
	
	@Test (timeout = 1000)
	public void testSomeRandomBehaviour() {
		Keep level = new Keep (map_test3);
		Game game = new Game (new GameLogic [] {level});
		
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
	
	@Test (timeout = 1000)
	public void testOgreAboveKey () {
		boolean ogAboveKey = false;
		Map map = new Map (map_test5);
		
		Coordinates ogre_coord = map.search_char('O');
		Coordinates club_coord = map.search_char('*');
		Coordinates key_coord = map.search_char('k');
		
		Ogre ogre = new Ogre (ogre_coord, club_coord, 'O', '$','*', '$');
		

		Coordinates initial_ogre = new Coordinates (ogre_coord);
		
		while (!ogAboveKey) {
			ogre.set_pos(initial_ogre);
			ogre.move_person(' ', map);
			
			if (key_coord.equals(ogre.get_coordinates())) {
				assertEquals(ogre.get_symbol(), '$');
				ogAboveKey = true;
				ogre.move_person(' ', map);
				assertEquals (ogre.get_symbol(), 'O');
			}
		}
		
		assertEquals(map.get_letter(key_coord), 'k');
		
	}
	
	
	@Test (timeout = 1000)
	public void testClubRandomBehaviour() {
		Keep level = new Keep (map_test3);
		Game game = new Game (new GameLogic [] {level});
		

		Coordinates ogre_coord =  level.get_ogres().elementAt(0).get_coordinates();
		Coordinates club_coord = level.get_ogres().elementAt(0).getClub().get_coordinates();
		
		boolean move_down = false, move_up = false, move_right = false, move_left = false;
		

		assertFalse ("Test game not over", game.check_game_over());
		
		while (!move_down || !move_up || !move_right || !move_left) {
			
			game.play('a');
			
			if (club_coord.equals(new Coordinates (ogre_coord.get_x() - 1, ogre_coord.get_y())))
				move_right = true;
			
			else if (club_coord.equals(new Coordinates (ogre_coord.get_x() + 1, ogre_coord.get_y())))
				move_left = true;
			
			else if (club_coord.equals(new Coordinates (ogre_coord.get_x(), ogre_coord.get_y() + 1)))
				move_down = true;
			
			else if (club_coord.equals(new Coordinates (ogre_coord.get_x(), ogre_coord.get_y() - 1)))
				move_up = true;
			
			else
				fail ("Behaviour not expected");
		}
	}
}

	
	

		
		
		

