package dkeep.logic;
import java.util.Random;

public class Ogre extends Person {
	private Random r;
	
	Ogre (int x_pos, int y_pos, char symbol) {
		super (x_pos, y_pos, symbol);
		r = new Random ();
	}
	
	@Override
	public boolean move_person (char key, Map map) {
		String movements = "asdw";
		
		if (super.move_person(movements.charAt(r.nextInt(4)), map)) {
			if (map.get_letter(get_x_pos(), get_y_pos()) == 'S')
				return false;
			
			return true;
		}
		
		return false;
	}
}
