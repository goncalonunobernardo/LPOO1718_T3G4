package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;
import dkeep.logic.GameState;

public class DungeonKeep {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		Game game = new Game();
		int current_level = game.get_current_level();
		
		do {
			
			if (game.get_current_level() != current_level) {
				current_level = game.get_current_level();
				System.out.println("Nicely done! Playing level " + current_level);
			}
			
			System.out.print(game.get_map());
			char user_command = input.next().charAt(0);
			game.play(user_command);
		} while (!game.check_game_over());
		
		System.out.print(game.get_map());
		
		if (game.get_game_status() == GameState.LOST)
			System.out.println("You lost!");
		else 
			System.out.println("You won!");
			
		input.close();
	}
}