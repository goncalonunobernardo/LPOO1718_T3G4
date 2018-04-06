package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * @class Suspicious
 * @extends Guard_2_ways
 * @brief Abstraction of the Suspicious Guard of the game.
 * It allows for the guard to randomly change its direction
 */
@SuppressWarnings("serial")
public class Suspicious extends Guard_2_ways implements Serializable{
	private int plays_to_reverse; 	/** @brief Number of plays that the guard will keep the same direction.
									It's randomly computed and it changes every time he changes direction */
	private int bound;				/** @brief Maximum value of plays that will go with no change in the state of the guard (asleep or awake) and its movement direction */

	/**
	 * @brief Constructor of the Suspicious object.
	 * @param coord coordinates of Suspicious
	 * @param symbol of Suspicious on the map
	 * @param movement String where movement that the guard will repeat its stored
	 * @param bound Maximum value of plays that will go with no change in its movement direction
	 */
	public Suspicious (Coordinates coord, char symbol, String movement, int bound) {
		super (coord, symbol, movement);
		this.r = new Random ();
		this.plays_to_reverse = r.nextInt(bound);
		this.bound = bound;
	}

	/**
	 * @brief Allows the guard to move within a map: it may or not change direction -> it's random
	 * @param map where it's stored
	 */
	@Override
	public void move_guard ( Map map) {
		
		if (this.plays_to_reverse == 0) {
			
			this.plays_to_reverse = r.nextInt(this.bound) + 1;
			
			random_direction();
		}
		else {
			this.plays_to_reverse--;
		}
		
		super.move_guard(map);
	}
}
