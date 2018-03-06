package dkeep.logic;
import java.util.Random;

public class Ogre extends Person {
	private Random r;
	private Club club;
	
	Ogre (int x_pos, int y_pos, char symbol) {
		super (x_pos, y_pos, symbol);
		r = new Random ();
		club = new Club (0, 0, '*');
	}
	
	public Club  getClub() {
		return club;
	}
	
	
	@Override
	public void move_person (char key, Map map) {
		String movements = "asdw";
		int old_x = this.get_x_pos();
		int old_y = this.get_y_pos();
		
		super.move_person(movements.charAt(r.nextInt(4)), map);
		
		if (map.get_letter(get_x_pos(), get_y_pos()) != ' ') {
			this.set_pos(old_x, old_y);
		}
			
		club.set_pos(get_x_pos(), get_y_pos());
		club.move_person(movements.charAt(r.nextInt(4)), map);
			
	}
	
	@Override
	public boolean check_near (Person person) {
		return super.check_near(person) || club.check_near(person);
		
	}
	
	@Override
	public void reset_person (Map map) {
		club.reset_person(map);
		super.reset_person(map);
	}
}
