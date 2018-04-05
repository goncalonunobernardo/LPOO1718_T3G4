package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * @class Drunken
 * @extends Guard
 * @brief Abstraction of the Drunken Guard of the game.
 */
@SuppressWarnings("serial")
public class Drunken extends Guard implements Serializable {
	private Random r;							/** @brief Allows to randmly stay awake or asleep, to walk in a direction or another */
	private int stop_plays;						/** @brief Number of plays the guard will remain asleep (stopped). 
													It's randomly computed and it changes every time he falls asleep  */
	private int walk_plays;						/** @brief Number of plays the guard will remain awake (playing). 
													It's randomly computed and it changes every time he awakes  */
	private int bound;							/** @brief Reverse movement that the guard will have to make if its direction is changed*/
	private boolean asleep;						/** @brief Saves the state of the guard = awake or asleep */
	private String reverse_mov;					/** @brief Reverse movement that the guard will have to make if its direction is changed*/

	/**
	 * @brief Constructor for Drunken type of Guard: computes the reverse movement so he can change direction
	 * @param coord Initial coordinates of the guard
	 * @param symbol Symbol that will represent it
	 * @param movement Movement that the guard will repeat
	 * @param bound Maximum value of plays that will go with no change in the state of the guard (asleep or awake) and its movement direction
	 */
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

	/**
	 * @brief Allows the guard to move within a map: stays in the same place when asleep (symbol: g), 
	 * moves according to current movement when awake (symbol: G), may change direction after waking up
	 * @param key Irrevelant in this case because it follows the guard movement but allows to override the super class function
	 * @param map where it's stored
	 */
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

	/**
	 * @brief Applies random-direction so that the guard can change the way: 
	 * after randomly deciding exchanges the movement and its reverse, or not.
	 * Computes where the string should be counted from now one
	 */
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
