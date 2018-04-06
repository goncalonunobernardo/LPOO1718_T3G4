package dkeep.logic;

import java.io.Serializable;
import java.util.Random;

/**
 * @Class Guard_2_ways
 * @brief Abstraction of a guard that can randomly change direction 
 */
@SuppressWarnings("serial")
public abstract class Guard_2_ways extends Guard implements Serializable{
	protected Random r;							/** @brief Allows to randomly change direction */
	private String reverse_mov;					/** @brief Reverse movement that the guard will have to make if its direction is changed*/

	/**
	 * @brief Constructor of the Guard_2_ways object.
	 * It computes the reverse movement that the guard will have to make if its direction is changed
	 * @param coord coordinates of the Guard
	 * @param symbol of the Guard
	 * @param movement String where movement that the guard will repeat its stored
	 * @param bound Maximum value of plays that will go with no change in its movement direction
	 */
	public Guard_2_ways(Coordinates coord, char symbol, String movement) {
		super(coord, symbol, movement);
		
		this.reverse_mov = new String();
		this.reverse_mov = "";
		this.r = new Random ();
		
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

}
