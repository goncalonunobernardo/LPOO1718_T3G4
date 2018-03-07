package dkeep.logic;

public class Coordinates {
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

	public void set_x(int x) {
		this.x = x;
	}

	public int get_y() {
		return y;
	}

	public void set_y(int y) {
		this.y = y;
	}
	
	public void set_pos (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void dec_y () {
		this.y--;
	}
	
	public void dec_x () {
		this.x--;
	}
	
	public void inc_y () {
		this.y++;
	}
	
	public void inc_x () {
		this.x++;
	}
	
	public boolean same_column (Coordinates coord) {
		return coord.get_y() == this.y;
	}
	
	public boolean same_line (Coordinates coord) {
		return coord.get_x() == this.x;
	}
	
	public boolean adjacent_cells (Coordinates coord) {
		//mesma coluna, mesma posição ou nas 2 posições adjacentes
        if (same_column (coord) && ((coord.get_x() - 1) <= this.get_x() && this.get_x() <= (coord.get_x() + 1))) {
            return true;
        }
        //mesma linha, mesma posição ou nas 2 posições adjacentes
        else if (same_line (coord) && ((coord.get_y() - 1) <= this.get_y() && this.get_y() <= (coord.get_y() + 1))) {
            return true;
        }
        return false;
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
