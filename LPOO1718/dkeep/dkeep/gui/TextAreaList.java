package dkeep.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextAreaList  implements KeyListener {
	
	private DkeepGUI gui;
	
	TextAreaList (DkeepGUI gui) {
		this.gui = gui;
	}

	public char key_to_letter (int key) {
		
		switch (key) {
		
		case KeyEvent.VK_UP: return 'w';
		
		case KeyEvent.VK_DOWN: return 's';
		
		case KeyEvent.VK_LEFT: return 'a';
		
		case KeyEvent.VK_RIGHT: return 'd';
		
		}
		
		return ' ';
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
//		int hero_key = e.getKeyCode();
		char hero_mov = e.getKeyChar();
		
		if (hero_mov != ' ') {
			gui.play(hero_mov);		
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {	}

	@Override
	public void keyReleased(KeyEvent e) { }

}
