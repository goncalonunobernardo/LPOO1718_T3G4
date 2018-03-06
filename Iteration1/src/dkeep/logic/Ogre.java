package dkeep.logic;
import java.util.Random;

public class Ogre extends Person {
	private Random r;
	private Club club;
	private int stunned;
	
	Ogre (int x_pos, int y_pos, char symbol, int club_x, int club_y, char club_symbol) {
		super (x_pos, y_pos, symbol);
		r = new Random ();
		club = new Club (club_x, club_y, club_symbol);
		this.stunned = 0;
	}
	
	public Club  getClub() {
		return club;
	}
	
	
	@Override
	public void move_person (char key, Map map) {
		String movements = "asdw";
		int old_x = this.get_x_pos();
		int old_y = this.get_y_pos();
		
		if (stunned == 0) {
			this.set_symbol('O');
			super.move_person(movements.charAt(r.nextInt(4)), map);

			if (map.get_letter(get_x_pos(), get_y_pos()) != ' ') {
				this.set_pos(old_x, old_y);
			}
		}
		else
			stunned--;
			
		club.set_pos(get_x_pos(), get_y_pos());
		club.move_person(movements.charAt(r.nextInt(4)), map);
			
	}
	
	@Override
	public boolean check_near (Hero person) {
		
		if (super.check_near(person) && (person.get_symbol() == 'A' || person.get_symbol() == 'K')) {
			this.set_symbol('8');
			this.stunned = 2;
			return club.check_near(person);
		}
		else {
			return super.check_near(person) || club.check_near(person);
		}
		
	}
	
	@Override
	public void reset_person (Map map) {
		club.reset_person(map);
		super.reset_person(map);
	}
	
	@Override
	public void draw_person (Map map) {
		club.draw_person(map);
		super.draw_person(map);
	}
}
