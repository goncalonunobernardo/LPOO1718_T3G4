package dkeep.logic;

import java.util.Random;

public class Drunken extends Guard {
	private Random r;
	private int stop_plays, walk_plays, bound;
	private boolean asleep;
	private String reverse_mov;
	
	Drunken (int x_pos, int y_pos, char symbol, String movement, int bound) {
		super (x_pos, y_pos, symbol, movement);
		this.r = new Random ();
		this.stop_plays = r.nextInt(bound);
		this.walk_plays = r.nextInt(bound);
		this.bound = bound;
		this.asleep = false;
		this.reverse_mov = "";
		
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
			this.asleep = false;
			this.set_symbol('G');
			
			//randomly saves the number of plays he will be asleep the next time he stops
			// + 1 -> so it won't be 0
			this.stop_plays = r.nextInt(this.bound) + 1;
			
			// randomly picks to switch or not direction
			if (r.nextBoolean()) {
				// exchanges the movement with the reversed one
				String aux = this.reverse_mov;
				this.reverse_mov = this.movement;
				this.movement = aux;

				//the movement will start from a different place because they're simmetric to each other
				//so that the count_string only has to be incremented
				count_string = movement.length() - count_string;
				
				//if the count_string was at 0, it moves to the last movement of the reversed movement
				if (count_string == 24)
					count_string = 23;
			}
		}
		
		if (walk_plays == 0) {
			this.asleep = true;
			this.set_symbol('g');
			//randomly saves the number of plays he will walk the next time he's awake
			// + 1 -> so it won't be 0
			this.walk_plays = r.nextInt(this.bound) + 1;
		}
		
		if (this.asleep) {
			stop_plays--;
		}
		else {
			walk_plays--;
			super.move_person(movement.charAt(count_string), map);
		}
	}
	
	@Override
	public boolean check_near (Person person) {
		if (this.asleep) 
			return false;
		else
			return super.check_near(person);
	}
}
