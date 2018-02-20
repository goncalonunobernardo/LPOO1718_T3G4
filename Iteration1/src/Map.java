
public class Map {
	
	private char matrix [] []=  new char [][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};
	
	private int hero_hpos = 1;
	private int hero_vpos = 1;
	private int guard_hpos = 8;
	private int guard_vpos = 1;
	
	void print () {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	void open_doors () {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (matrix[i][j] == 'I')
					matrix[i][j] = 'S';
			}
		}
	}
	
	Boolean near_guard (int x, int y) {
		
		//mesma coluna do guarda e nas 3 posicoes "perto"
		if (guard_hpos - 1 <= x && x <= guard_hpos + 1  && y == guard_vpos) {
			return true;
		}
		//mesma linha do guarda e nas 3 posicoes "perto"
		else if (guard_vpos - 1 <= y && y <= guard_vpos + 1  && x == guard_hpos) {
			return true;
		}
		
		return false;
	}
	
	Boolean check_win (int x, int y) {
		return (x == 1 && (y == 5 || y == 6) && matrix[y][x] == 'S');
	}
	
	Boolean change_hero (char key) {
		
		int h_aux = hero_hpos, v_aux = hero_vpos;
		
		switch (key) {
		
		case 'w': v_aux -= 1; break;
		
		case 'a': h_aux -= 1; break;
			
		case 's': v_aux += 1; break;
			
		case 'd': h_aux += 1; break;
		
		}
		
		
		if (matrix [v_aux][h_aux] == 'k') {
			open_doors();
		}
		else if (matrix [v_aux][h_aux] == ' '){
			matrix[hero_vpos][hero_hpos] = ' ';
			matrix[v_aux][h_aux] = 'H';
			hero_vpos = v_aux;
			hero_hpos = h_aux;
		}
		
		if (near_guard (h_aux, v_aux)) {
			System.out.println("You lose!");
			return false;
		}
		
		if (check_win(h_aux, v_aux)) {
			System.out.println("You win!");
			return false;
		}
		
		return true;
	}
	

}
