import java.util.Random;

public class Club extends Person {
	Random r;
	boolean just_change;
	
	Club (int x_pos, int y_pos, char symbol) {
		super (x_pos, y_pos, symbol);
		this.r = new Random ();
		this.just_change = false;
	}
	
	@Override 
	public boolean move_person (char key, Map map) {
		int ogre_x = map.get_ogre().get_x_pos();
		int ogre_y = map.get_ogre().get_y_pos();
		
		this.set_pos(ogre_x, ogre_y);
		
		String movements = "asdw";

		while ((!super.move_person(movements.charAt(r.nextInt(4)), map)) || map.get_letter(get_x_pos(), get_y_pos()) == 'S'){
		}
		
		if (map.get_letter(get_x_pos(), get_y_pos()) == 'k') {
			this.set_symbol('$');
		}

		return true;
	}

}
