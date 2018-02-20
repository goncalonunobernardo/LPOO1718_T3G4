import java.util.Scanner;

public class DungeonKeep {
	
	public static void main(String[] args) {

		Map map = new Map();
		Scanner input = new Scanner (System.in);
		Boolean playing = true;

		
		while (playing) {
			map.print();
			char user_command = input.next().charAt(0);
			playing = map.change_hero(user_command);
		}
		map.print();
		input.close();

  }
}
