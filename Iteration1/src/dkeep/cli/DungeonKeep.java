package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;
import dkeep.logic.Map;

public class DungeonKeep {

	private static char [][] map_1 = new char [][] {
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
	
	private static char[][] map_2 = new char [][] {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'I', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'k', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	private static char[][] map_3 = new char [][] {
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
		{'I', ' ', ' ', 'k', 'O', 'k', ' ', ' ', 'k', 'X'},
		{'X', ' ', ' ', ' ', 'k', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
		{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	public static boolean play (Scanner input, Map map) {
		Game game = new Game(map);

		while (!game.check_game_over()) {
			game.get_map().print();
			char user_command = input.next().charAt(0);

			game.move(user_command);
		}
		
		game.get_map().print();
		
		return game.check_win();
	}

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		if (play (input, new Map (map_3))) {
			System.out.println("LEVEL 2");
			
			if (play (input, new Map (map_2))) 
				System.out.println("You win!!");
			else 
				System.out.println("You lose!!");
			
		}
		else
			System.out.println("You lose!!");
		

		input.close();
	}
}