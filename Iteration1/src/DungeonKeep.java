import java.util.Scanner;

public class DungeonKeep {
	
	public static void play (Scanner input, int level) {
		
		Map map = new Map(level);
		boolean playing = true;
		
		while (playing) {
			map.print();
			char user_command = input.next().charAt(0);
			playing = map.move(user_command);
		}
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		play (input, 1);
		
		System.out.println("LEVEL 2");
		
		play (input, 2);
		
		input.close();

  }
}
