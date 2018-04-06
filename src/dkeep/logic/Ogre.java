package dkeep.logic;
import java.io.Serializable;
import java.util.Random;


@SuppressWarnings("serial")
/**
 * Abstraction of the Ogre of the game. 
 * It allows to randomly move the ogre, to save and move its club and to harm the ogre if the hero is near him
 * @class Ogre
 * @extends Person
 */
public class Ogre extends Person implements Serializable{
	private static final char STUNNED_SYMBOL = '8', DEFAULT_SYMBOL = 'O';
	
	private Random r;			/** Random object to randomly choose the ogre's movement*/
	private Club club;			/** Club of the ogre, that will always be next to it*/
	private int stunned;			/** Number of plays that the ogre will be stunned for*/

	/**
	 * Constructor of the Ogre object
	 * @param coord Initial coordinates of Ogre
	 * @param club_coord coordinates of Ogre's Club
	 * @param symbol of Ogre
	 * @param key_symbol of key in map
	 * @param club_symbol Character of Club
	 * @param club_key_symbol Club's character if in the same location as key
	 */
	public Ogre (Coordinates coord, Coordinates club_coord, char symbol, char key_symbol, char club_symbol, char club_key_symbol) {
		super (coord, symbol, key_symbol);
		r = new Random();
		this.club = new Club (club_coord, club_symbol, club_key_symbol);
	}

	/**
	 * Obtains Club object
	 * @return Club
	 */
	public Club  getClub() {
		return club;
	}

	/**
	 * Ogre's movement with the stun setting changing his appearance
	 * It equals the club's coordinates to the ogre's so that the club is always next to the ogre
	 * @param key Irrevelant in this case because it is random but allows to override the super class function
	 * @param map Map where the character is stored
	 */
	@Override
	public void move_person (char key, Map map) {
		String movements = "asdw";
		
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		if (stunned == 0) {
			
			if (this.get_symbol() == STUNNED_SYMBOL)
				this.set_symbol(DEFAULT_SYMBOL);
			
			while (map.not_empty(get_coordinates()) || (initial_coord.equals(get_coordinates()))) {
				this.set_pos(initial_coord);
				super.move_person(movements.charAt(r.nextInt(4)), map);
			}
		}
		else
			stunned--;
			
		club.set_pos(this.get_coordinates());
		club.move_person(' ', map);
	}

	/**
	 * Checks if Hero is in one of the adjacent cells to Ogre so that he can stun him, or maybe attack the Hero.
	 * If the hero is next to the ogre, the stunned attribute is set to 2 to make sure the Ogre stays still for 2 plays
	 * @param person Person to check if it's in one of the adjacent cells to the Ogre
	 * @return true if it is, false otherwise
	 */
	@Override
	public boolean check_near (Hero person) {
		
		if (super.check_near(person)) {
			this.set_symbol(STUNNED_SYMBOL);
			this.stunned = 2;
			return club.check_near(person);
		}
		else {
			return super.check_near(person) || club.check_near(person);
		}
		
	}
}
