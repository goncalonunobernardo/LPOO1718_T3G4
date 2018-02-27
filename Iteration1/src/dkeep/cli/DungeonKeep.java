package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;

public class DungeonKeep {

	public static void play (Scanner input, Game game) {
		boolean playing = true;

		while (playing) {
			game.get_map().print();
			char user_command = input.next().charAt(0);
			playing = !game.move(user_command);
		}
		game.get_map().print();

	}
		public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		Game game = new Game();
		play(input, game);
		game.level_setup();
		
		input.close();
  }
}
/* public static void play (Scanner input, int level) {

    Map map = new Map(level);
    boolean playing = true;

    while (playing) {
      map.print();
      char user_command = input.next().charAt(0);
      playing = map.move(user_command);
    }
  }
  */