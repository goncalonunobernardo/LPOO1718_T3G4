package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;

public class DungeonKeep {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		Game game = new Game();
		
		do {
			System.out.println(game.get_map());
			char user_command = input.next().charAt(0);
			game.play(user_command);
			game.updateLevel();
		} while (!game.check_game_over());

		input.close();
	}
}