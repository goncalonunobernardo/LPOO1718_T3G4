package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * @class Suspicious
 * @extends Guard
 * @brief Abstraction of the Suspicious Guard of the game.
 * It allows for the guard to randomly change its direction
 */
@SuppressWarnings("serial")
public class Suspicious extends Guard implements Serializable{
	private Random r;
	private String reverse_mov;					/** @brief Reverse movement that the guard will have to make if its direction is changed*/
	private int plays_to_reverse, bound;			/** @brief Maximum value of plays that will go with no change in the state of the guard (asleep or awake) and its movement direction */

	/**
	 * @brief Constructor of the Suspicious object.
	 * It computes the reverse movement that the guard will have to make if its direction is changed
	 * @param coord coordinates of Suspicious
	 * @param symbol of Suspicious on the map
	 * @param movement String where movement that the guard will repeat its stored
	 * @param bound Maximum value of plays that will go with no change in the state of the guard (asleep or awake) and its movement direction
	 */
	public Suspicious (Coordinates coord, char symbol, String movement, int bound) {
		super (coord, symbol, movement);
		this.r = new Random ();
		this.reverse_mov = "";
		this.plays_to_reverse = r.nextInt(bound);
		this.bound = bound;
		
		for (int i = movement.length() - 1; i >= 0; i--) {
			
			switch (movement.charAt(i)) {
			
			case 'a': reverse_mov += 'd'; break;
			case 'd': reverse_mov += 'a'; break;
			case 'w': reverse_mov += 's'; break;
			case 's': reverse_mov += 'w'; break;
			
			}
		}
	}

	/**
	 * @brief Allows the guard to move within a map: it may or not change direction -> it's random
	 * @param key Irrevelant in this case because it follows the guard movement but allows to override the super class function
	 * @param map where it's stored
	 */
	@Override
	public void move_person (char key, Map map) {
		
		if (this.plays_to_reverse == 0) {
			//randomly saves the number of plays until the next change of direction
			// + 1 -> so it won't be 0
			this.plays_to_reverse = r.nextInt(this.bound) + 1;
			
			// exchanges the movement with the reversed one
			String aux = this.reverse_mov;
			this.reverse_mov = this.movement;
			this.movement = aux;

			//the movement will start from a different place because they're simmetric to each other
			//so that the count_string only has to be incremented
			count_string = movement.length() - count_string;

			//if the count_string was at 0, it moves to the last movement of the reversed movement
			if (count_string >= movement.length())
				count_string = movement.length() - 1;
		}
		else {
			this.plays_to_reverse--;
		}
		
		super.move_person(movement.charAt(count_string), map);
	}
}
