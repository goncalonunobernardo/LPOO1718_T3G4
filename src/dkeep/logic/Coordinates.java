package dkeep.logic;

import java.io.Serializable;


@SuppressWarnings("serial")
/**
 * Abstraction of 2 dimensional Coordinates of all levels
 * @class Coordinates
 */
public class Coordinates implements Serializable {
	private int x;			/** x coordinate */
	private int y;			/** y coordinate */

	/**
	 * Constructor for all coordinates of the map
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public Coordinates (int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Copy constructor
	 * @param coord Coordinates to which this will be equal to
	 */
	public Coordinates(Coordinates coord) {
		this.x = coord.get_x();
		this.y = coord.get_y();
	}

	/**
	 * Gets x coordinate of the map
	 * @return x coordinate
	 */
	public int get_x() {
		return x;
	}

	/**
	 * Gets y coordinate of the map
	 * @return y coordinate
	 */
	public int get_y() {
		return y;
	}

	/**
	 * Sets x and y position of map
	 * @param x X coordinate to which it will be changed
	 * @param y Y coordinate to which it will be changed
	 */
	public void set_pos (int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets x and y position of objects in map
	 * @param new_coord Coordinates to which this will be equal to
	 */
	public void set_pos (Coordinates new_coord) {
		this.x = new_coord.get_x();
		this.y = new_coord.get_y();
	}

	/**
	 * Increments y coordinate
	 * @param adding The value to be incremented
	 */
	public void add_y (int adding) {
		this.y += adding;
	}

	/**
	 * Increments x coordinate
	 * @param adding The value to be incremented
	 */
	public void add_x (int adding) {
		this.x += adding;
	}

	/**
	 * Tests if objects are in same column
	 * @param coord Coordinates that will be tested with this object
	 * @return true if objects are in same column, false otherwise
	 */
	public boolean same_column (Coordinates coord) {
		return coord.get_y() == this.y;
	}

	/**
	 * Tests if x coordinates are in same line (are the same)
	 * @param coord Coordinates that will be tested with this object
	 * @return true if x coordinates are the same, false otherwise
	 */
	public boolean same_line (Coordinates coord) {
		return coord.get_x() == this.x;
	}

	/**
	 * Tests if Objects are equal
	 * @param obj The Object to which this will be compared to 
	 * @return true is objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		
		return true;
	}
	
	
}
