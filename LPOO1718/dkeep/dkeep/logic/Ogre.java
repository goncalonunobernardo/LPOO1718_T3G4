package dkeep.logic;
import java.io.Serializable;
import java.util.Random;

/**
 * @class Ogre
 * @extends Person
 * @brief Abstraction of the Ogre of the game.
 */
@SuppressWarnings("serial")
public class Ogre extends Person implements Serializable{
	private Random r;
	private Club club;
	private int stunned;

	/**
	 * @brief Constructor of the Ogre object
	 * @param coord coordinates of Ogre
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
	 * @brief Obtains Club object
	 * @return Club
	 */
	public Club  getClub() {
		return club;
	}

	/**
	 * @brief Ogre's movement with the stun setting changing his appearance
	 * @param key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @param map Map where the character is stored
	 */
	@Override
	public void move_person (char key, Map map) {
		String movements = "asdw";
		
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		if (stunned == 0) {
			
			if (this.get_symbol() == '8')
				this.set_symbol('O');
			
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
	 * @brief Checks if Hero is close to Ogre so that he can stun him, or maybe attack the Hero
	 * @param person Character that makes the player lose - club, ogre or guard depending on the level
	 * @return true if near
	 */
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
}
