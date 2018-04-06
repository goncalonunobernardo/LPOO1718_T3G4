package dkeep.logic;
import java.io.Serializable;
import java.util.Random;


@SuppressWarnings("serial")
/**
 * @class Club
 * @extends Person
 * Abstraction of the Club of the game: allows to create a club and to move it at a random direction within a map
 */
public class Club extends Person implements Serializable {
	Random r;					/** To allow for the movement of the club to be random*/

	/**
	 * Constructor for the Ogre's Club
	 * @param coord Initial coordinates of the club
	 * @param club_symbol Symbol that represents the club when the game is being printed on the console
	 * @param key_symbol Symbol that is drawn instead of the club's symbol when it's above the key
	 */
	public Club(Coordinates coord, char club_symbol, char key_symbol) {
		super (coord, club_symbol, key_symbol);
		this.r = new Random();
	}

	/**
	 * Allows the club to move within a map in a random direction: 
	 * it makes sure it does not stay in the same place and does not move to a space already occupied
	 * @param key Irrelevant in this case because it is random but allows to override the super class function
	 * @param map Map where the character is drawn
	 */
	@Override 
	public void move_person (char key, Map map) {
		String movements = "asdw";
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		while (map.not_empty(get_coordinates()) || (initial_coord.equals(get_coordinates()))) {
			this.set_pos(initial_coord);
			super.move_person(movements.charAt(r.nextInt(4)), map);
		}
	}
}
