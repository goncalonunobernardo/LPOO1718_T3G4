package dkeep.logic;

import java.io.Serializable;

public class Coordinates implements Serializable {
	private int x;
	private int y;
	
	public Coordinates (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinates(Coordinates coord) {
		this.x = coord.get_x();
		this.y = coord.get_y();
	}

	public int get_x() {
		return x;
	}

	public int get_y() {
		return y;
	}

	
	public void set_pos (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void set_pos (Coordinates new_coord) {
		this.x = new_coord.get_x();
		this.y = new_coord.get_y();
	}
	
	public void add_y (int adding) {
		this.y += adding;
	}
	
	public void add_x (int adding) {
		this.x += adding;
	}
	
	public boolean same_column (Coordinates coord) {
		return coord.get_y() == this.y;
	}
	
	public boolean same_line (Coordinates coord) {
		return coord.get_x() == this.x;
	}

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
