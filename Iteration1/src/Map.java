
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
	private char matrix2 [] []=  new char [][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'I', ' ', ' ', ' ', '0', ' ', ' ', ' ', 'k', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
	};

	/* PARA O MAPA2
	private int hero_hpos = 8;
	private int hero_vpos = 1;
	private int ogre_hpos = 1;
	private int ogre_vpos = 4;
	private int win_door_hpos = 1;
	private int win_door_vpos = 0;
	 */
	private int hero_hpos = 1;
	private int hero_vpos = 1;
	private int guard_hpos = 8;
	private int guard_vpos = 1;
	private int win_door_hpos = 0;

	private int count_string = 0;
	private String guard_movement = "assssaaaaaasdddddddwwwww";
	
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
		if ( x == win_door_hpos + 1 && (y == 5 || y == 6) && matrix[y][win_door_hpos] == 'S') {
			return true;
		}
		return false;
	}

	//Queria modular mais isto
	Boolean change_guard (char movement_key) {
		int guard_h_aux = guard_hpos, guard_v_aux = guard_vpos;

		switch (movement_key) {

			case 'w':
				guard_v_aux -= 1;
				break;

			case 'a':
				guard_h_aux -= 1;
				break;

			case 's':
				guard_v_aux += 1;
				break;

			case 'd':
				guard_h_aux += 1;
				break;

		}

		if (matrix[guard_v_aux][guard_h_aux] == ' ') {
			matrix[guard_vpos][guard_hpos] = ' ';
			matrix[guard_v_aux][guard_h_aux] = 'G';
			guard_vpos = guard_v_aux;
			guard_hpos = guard_h_aux;
		}

		return true;
	}

	Boolean change_hero (char key) {

		int h_aux = hero_hpos, v_aux = hero_vpos;


			switch (key) {

				case 'w':
					v_aux -= 1;
					break;

				case 'a':
					h_aux -= 1;
					break;

				case 's':
					v_aux += 1;
					break;

				case 'd':
					h_aux += 1;
					break;

			}

			change_guard(guard_movement.charAt(count_string));
			if(count_string == guard_movement.length() - 1) count_string = 0;
			else count_string++;

			if (matrix[v_aux][h_aux] == 'k') {
				open_doors();
			} else if (matrix[v_aux][h_aux] == ' ') {
				matrix[hero_vpos][hero_hpos] = ' ';
				matrix[v_aux][h_aux] = 'H';
				hero_vpos = v_aux;
				hero_hpos = h_aux;
			} else if(matrix[v_aux][h_aux] == 'S') {
				matrix[hero_vpos][hero_hpos] = ' ';
				matrix[v_aux][h_aux] = 'H';
				hero_vpos = v_aux;
				hero_hpos = h_aux;
			}

			if (near_guard(h_aux, v_aux)) {
				System.out.println("\n\n\tYou lose!");
				return false;
			}

			if (check_win(h_aux, v_aux)) {
				System.out.println("\n\n\tYou win!");
				return false;
			}

		return true;
	}

}
