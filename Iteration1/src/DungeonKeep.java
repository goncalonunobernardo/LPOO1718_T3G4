import java.util.Scanner;

public class DungeonKeep {
	
	public static boolean play (Scanner input, int level) {
		
		Map map = new Map(level);
		boolean playing = true;
		
		while (playing) {
			map.print();
			char user_command = input.next().charAt(0);
			playing = !map.move(user_command);
		}
		
		map.print();
		
		if (map.check_win()) {
			if (level == 1) {
				System.out.println("LEVEL 2");
			}
			else {
				System.out.println("You win!!");
			}
			
			return true;
		}
		else {
			System.out.println("You lose!!");
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		if (play (input, 1)) {
			play (input, 2);
		}
		
		input.close();
  }
}
