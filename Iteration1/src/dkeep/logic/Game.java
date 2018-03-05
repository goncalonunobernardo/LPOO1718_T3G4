package dkeep.logic;

public class Game {
    private Hero hero;			/** @brief Hero of the game */
    private Guard guard;			/** @brief Guard of the game */
    private Ogre ogre;
    private Club club;
    private boolean win, lose;	/** @brief Boolean variables to know if the game ended or not*/
    private Map map;

    public Game () {
    		this.win = false;
    		this.lose = false;
    		this.map = new Map(1);

    		if (map.get_level() == 1) {
    			this.hero = new Hero (1, 1, 'H');
    			this.guard = new Suspicious (8, 1, 'G', "assssaaaaaasdddddddwwwww", 10);
    		} else
    			if (map.get_level() == 2) {
    				this.hero = new Hero (1, 8, 'H');
    				this.ogre = new Ogre (4, 1, 'O');
    				this.club = new Club (5, 1, '*');
    			}
    }

    public boolean level_setup() {
	    	if (check_win()) {
	    		if (this.map.get_level() == 1) {
	    			System.out.println("LEVEL 2");
	    			this.set_level(2);
	    			return true;
	    		}
	    		else {
	    			System.out.println("You win!!");
	    			return false;
	    		}
	    	}
	    	else {
	    		System.out.println("You lose!!");
	    		return false;
	    	}
    }
    
    public void set_level(int level) {
    		this.map.set_level(level);
    		
    		if (map.get_level() == 1) {
    			this.hero.set_pos(1, 1);
    			this.hero.set_symbol('H');
    			this.guard.set_pos(8, 1);
    			this.guard.set_symbol('G');
    			this.ogre = null;
    			this.club = null;
    		} else
    			if (map.get_level() == 2) {
        			this.hero.set_pos(1, 8);
        			this.hero.set_symbol('H');
        			this.guard = null;
    				this.ogre = new Ogre (4, 1, 'O');
    				this.club = new Club (5, 1, '*');
    			}
    }


    /**
     * @return The guard of this game
     */
    public Guard get_guard () {
        return guard;
    }

    /**
     * @return The hero of this game
     */
    public Hero get_hero() {
        return hero;
    }

    /**
     * @return The ogre of this game
     */
    public Ogre get_ogre() {
        return this.ogre;
    }

    /**
     * @return The club of this game
     */
    public Club get_club() {
        return this.club;
    }

    public Map get_map() {
        return this.map;
    }

    /**
     * @return The value of the attribute lose
     */
    public boolean check_win() {
        return this.win;
    }
    
    public boolean check_lose() {
    		return this.lose;
    }
    
    public void set_win (boolean win) {
    		if (win) {
    			this.win = true;
    		}
    		else {
    			this.lose = true;
    		}
    }


    /**
     * @brief Moves the hero, the guard or the ogre and the club of the game, by calling move_person and checks if the game is over
     * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
     * @return true, if the game ended; false, otherwise
     */
    public boolean move (char key) {

    		move_person (get_hero(), key);
    	
	    	if (get_guard() != null) {
	    		// the key won't be used so it doesn't matter
	    		move_person (get_guard(), ' ');
	    		this.lose = get_guard().check_near(get_hero());
	    	}
	    	else {
	    		// the key won't be used so it doesn't matter
	    		move_person (get_ogre(), ' ');
	    		move_person (get_club(), ' ');
	    		this.lose = get_ogre().check_near(get_hero()) || get_club().check_near(get_hero());
	    	}
	
	    	this.win = get_hero().check_win();
	
	    	return check_win() || check_lose();
    }

    /**
     * @brief Moves an entity according to a key
     * @param person The entity to be moved
     * @param key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
     */
    public void move_person (Person person, char key) {
        int old_x = person.get_x_pos();
        int old_y = person.get_y_pos();

        if (person.move_person(key, map)) {
            //to make sure the character
//            if (map.get_letter(old_x, old_y) == person.get_symbol()) {
                map.set_letter(old_x, old_y, ' ');
//            }
            map.set_letter(person.get_x_pos(), person.get_y_pos(), person.get_symbol());

        }
        else {
            person.set_pos(old_x, old_y);
        }
    }
}
