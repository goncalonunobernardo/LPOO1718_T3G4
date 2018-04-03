package dkeep.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextAreaList  implements KeyListener {
	
	private DkeepGUI gui;
	
	TextAreaList (DkeepGUI gui) {
		this.gui = gui;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char hero_key = e.getKeyChar();
	
		gui.play(hero_key);
	}

	@Override
	public void keyPressed(KeyEvent e) {	}

	@Override
	public void keyReleased(KeyEvent e) { }

}
