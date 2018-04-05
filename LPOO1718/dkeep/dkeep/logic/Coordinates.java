package dkeep.logic;

import java.io.Serializable;

/**
 * @class Coordinates
 * @brief Abstraction of the Coordinates of all levels
 */
public class Coordinates implements Serializable {
	private int x;
	private int y;

	/**
	 * @brief Constructor for all coordinates of the map
	 * @param x
	 * @param y
	 */
	public Coordinates (int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @brief Constructor for updated coordinates of objects
	 * @param coord
	 */
	public Coordinates(Coordinates coord) {
		this.x = coord.get_x();
		this.y = coord.get_y();
	}

	/**
	 * @brief Gets x coordinate of the map
	 * @return x coordinate
	 */
	public int get_x() {
		return x;
	}

	/**
	 * @brief Gets y coordinate of the map
	 * @return y coordinate
	 */
	public int get_y() {
		return y;
	}

	/**
	 * @brief Sets x and y position of map
	 * @param x
	 * @param y
	 */
	public void set_pos (int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @brief Sets x and y position of objects in map
	 * @param new_coord
	 */
	public void set_pos (Coordinates new_coord) {
		this.x = new_coord.get_x();
		this.y = new_coord.get_y();
	}

	/**
	 * @brief Increments y coordinate
	 * @param adding
	 */
	public void add_y (int adding) {
		this.y += adding;
	}

	/**
	 * @brief Increments x coordinate
	 * @param adding
	 */
	public void add_x (int adding) {
		this.x += adding;
	}

	/**
	 * @brief Tests if objects are in same column
	 * @param coord
	 * @return true if objects are in same column
	 */
	public boolean same_column (Coordinates coord) {
		return coord.get_y() == this.y;
	}

	/**
	 * @brief Tests if x coordinates are in same line (are the same)
	 * @param coord
	 * @return true if x coordinates are the same
	 */
	public boolean same_line (Coordinates coord) {
		return coord.get_x() == this.x;
	}

	/**
	 * @brief Tests if Objects are equal
	 * @param obj
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
