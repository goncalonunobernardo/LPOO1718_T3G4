package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

@SuppressWarnings("serial")
public class Drunken extends Guard implements Serializable {
	private Random r;
	private int stop_plays, walk_plays, bound;
	private boolean asleep;
	private String reverse_mov;
	
	public Drunken (Coordinates coord, char symbol, String movement, int bound) {
		super (coord, symbol, movement);
		this.r = new Random ();
		this.stop_plays = r.nextInt(bound) + 1;
		this.walk_plays = r.nextInt(bound) + 1;
		this.bound = bound;
		this.reverse_mov = "";
		this.asleep = false;
		
		for (int i = movement.length() - 1; i >= 0; i--) {
			
			switch (movement.charAt(i)) {
			
			case 'a': reverse_mov += 'd'; break;
			case 'd': reverse_mov += 'a'; break;
			case 'w': reverse_mov += 's'; break;
			case 's': reverse_mov += 'w'; break;
			
			}
		}
	}
	
	@Override
	public void move_person (char key, Map map) {
		
		if (stop_plays == 0) {
			this.set_symbol('G');
			this.stop_plays = r.nextInt(this.bound) + 1;
			
			random_direction();
			
			this.asleep = false;
		}
		
		else if (walk_plays == 0) {
			this.set_symbol('g');
			this.walk_plays = r.nextInt(this.bound) + 1;
			this.asleep = true;
		}
		
		if (this.asleep) {
			stop_plays--;
		}
		else {
			walk_plays--;
			super.move_person(movement.charAt(count_string), map);
		}
	}
	
	public void random_direction () {
		if (r.nextBoolean()) {

			String aux = this.reverse_mov;
			this.reverse_mov = this.movement;
			this.movement = aux;

			count_string = movement.length() - count_string;
			
			if (count_string >= movement.length())
				count_string = movement.length() - 1;
		}
	}
	
	@Override
	public boolean check_near (Hero person) {
		if (this.asleep) 
			return false;
		else
			return super.check_near(person);
	}
}
