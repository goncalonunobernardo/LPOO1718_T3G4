package dkeep.logic;

import java.io.Serializable;


@SuppressWarnings("serial")
/**
 * @class Drunken
 * @extends Guard_2_ways
 * Abstraction of the Drunken Guard of the game.
 */
public class Drunken extends Guard_2_ways implements Serializable {
	private static final int MIN_BOUND = 2;		/** Minimal number of plays that will go with no change*/
	private int stop_plays;						/** Number of plays the guard will remain asleep (stopped). 
													It's randomly computed and it changes every time he falls asleep  */
	private int walk_plays;						/** Number of plays the guard will remain awake (playing). 
													It's randomly computed and it changes every time he awakes  */
	private int bound;							/** Reverse movement that the guard will have to make if its direction is changed*/
	private boolean asleep;						/** Saves the state of the guard = awake or asleep */

	/**
	 * Constructor for Drunken type of Guard
	 * @param coord Initial coordinates of the guard
	 * @param symbol Symbol that will represent it
	 * @param movement Movement that the guard will repeat
	 * @param bound Maximum value of plays that will go with no change in the state of the guard (asleep or awake) and its movement direction
	 */
	public Drunken (Coordinates coord, char symbol, String movement, int bound) {
		super (coord, symbol, movement);
		this.stop_plays = r.nextInt(bound) + MIN_BOUND;
		this.walk_plays = r.nextInt(bound) + MIN_BOUND;
		this.bound = bound;
		this.asleep = false;
	}

	/**
	 * Allows the guard to move within a map: stays in the same place when asleep (symbol: g), 
	 * moves according to current movement when awake (symbol: G), may change direction after waking up
	 * @param map where it's stored
	 */
	@Override
	public void move_guard (Map map) {
		
		if (stop_plays == 0) {
			this.set_symbol('G');
			this.stop_plays = r.nextInt(this.bound) + MIN_BOUND;
			
			random_direction();
			
			this.asleep = false;
		}
		
		else if (walk_plays == 0) {
			this.set_symbol('g');
			this.walk_plays = r.nextInt(this.bound) + MIN_BOUND;
			this.asleep = true;
		}
		
		if (this.asleep) {
			stop_plays--;
		}
		else {
			walk_plays--;
			super.move_guard(map);
		}
	}

	/**
	 *
	 * @param person Hero that makes the player lose - club, ogre or guard depending on the level
	 * @return true if is in fact near
	 */
	@Override
	public boolean check_near (Hero person) {
		if (this.asleep) 
			return false;
		else
			return super.check_near(person);
	}
}
