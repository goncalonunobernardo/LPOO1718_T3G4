package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;

public class DungeonKeep {

	public static boolean play (Scanner input, Game game) {
		boolean playing = true;

		while (playing) {
			game.get_map().print();
			char user_command = input.next().charAt(0);

			playing = !game.move(user_command);
		}
		game.get_map().print();
		
		return game.check_win();
	}

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		Game game = new Game();
		
		do {
			game.set_win(play (input, game));
		}
		while (game.level_setup());

		input.close();
	}
}