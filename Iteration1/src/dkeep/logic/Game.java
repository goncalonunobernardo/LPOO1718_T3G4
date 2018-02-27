package dkeep.logic;
import java.util.Scanner;


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

        if(map.get_level() == 1) {
        this.hero = new Hero (1, 1, 'H');
        this.guard = new Guard (8, 1, 'G');
        } else
            if(map.get_level() == 2) {
                this.hero = new Hero (1, 8, 'H');
                this.ogre = new Ogre (4, 1, 'O');
                this.club = new Club (5, 1, '*');
            }
    }

    public boolean level_setup() {
        if (hero.check_win(get_map())) {
            if (this.map.get_level() == 1) {
                System.out.println("LEVEL 2");
                this.map.set_level(2);}
                else {
                    System.out.println("You win!!");
                }
                return true;
        }
            else {
                System.out.println("You lose!!");
                return false;
            }
        }


    /**
     * @return The guard of this map
     */
    public Guard get_guard () {
        return guard;
    }

    /**
     * @return The hero of this map
     */
    public Hero get_hero() {
        return hero;
    }

    /**
     * @return The ogre of this map
     */
    public Ogre get_ogre() {
        return this.ogre;
    }

    /**
     * @return The club of this map
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
    public boolean check_lose() {
        return this.lose;
    }

    /**
     * @brief Sets the attribute win to true
     */
    public void set_win() {
        this.win = true;
    }

    /**
     * @brief Sets the attribute lose to true
     */
    public void set_lose() {
        this.lose = true;
    }


    /**
     * @brief Moves the hero, the guard or the ogre and the club of the game, by calling move_person and checks if the game is over
     * @param key Key that expresses the hero movement: a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
     * @return true, if the game ended; false, otherwise
     */
    public boolean move (char key) {

        if (get_guard() != null) {
            // the key won't be used so it doesn't matter
            move_person (get_guard(), ' ');
        }
        else {
            // the key won't be used so it doesn't matter
            move_person (get_ogre(), ' ');
            get_club().set_pos(get_ogre().get_x_pos(), get_ogre().get_y_pos());
            move_person (get_club(), ' ');
        }

       return move_person (get_hero(), key);
        //|| check_lose();
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
            if (map.get_letter(old_x, old_y) == person.get_symbol()) {
                map.set_letter(old_x, old_y, ' ');
            }
            map.set_letter(person.get_x_pos(), person.get_y_pos(), person.get_symbol());

        }
        else {
            person.set_pos(old_x, old_y);
        }
    }

    /**
     * @brief Checks if the player has lost, meaning if the hero is on one of the adjacent squares (except diagonals) to the enemy (guard, ogre or club)
     * @param enemy Character that makes the player lose - club, ogre or guard depending on the level
     * @param map The map of the game
     * @return true, if the player is near the enemy; false, otherwise
     */
    /*
    public boolean check_near () {

        if (this.enemy != null) {
            int enemy_x = enemy.get_x_pos();
            int enemy_y = enemy.get_y_pos();

            //mesma coluna do guarda e nas 3 posicoes "perto"
            if (this.get_y_pos() == enemy_y && ((enemy_x - 1) <= this.get_x_pos() && this.get_x_pos() <= (enemy_x + 1))) {
                return true;
            }
            //mesma linha do guarda e nas 3 posicoes "perto"
            else if (this.get_x_pos() == enemy_x && ((enemy_y - 1) <= this.get_y_pos() && this.get_y_pos() <= (enemy_y + 1))) {
                return true;
            }
            return false;
        }
        return false;
    }
    */



}
