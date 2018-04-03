package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import dkeep.logic.Dungeon;
import dkeep.logic.Game;
import dkeep.logic.GameLogic;
import dkeep.logic.Keep;

public class NewGame extends JPanel implements ActionListener {
	
	private DkeepGUI gui;
	private GuardField guard;
	private OgreField ogres;
	private JButton btnNewGame;
	
	NewGame (DkeepGUI gui) {
		
		this.btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(614, 172, 97, 25);
		btnNewGame.setEnabled(false);
		btnNewGame.addActionListener(this);

		this.ogres = new OgreField(gui, btnNewGame);
		this.guard = new GuardField();
		this.gui = gui;
		
		add(ogres);
		add(guard);
		add(btnNewGame);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Dungeon level1 = new Dungeon (guard.get_selected_guard());
		Keep level2 = new Keep (ogres.get_nr_ogres());
		
		gui.start_new_game (new Game (new GameLogic [] {level1, level2 }));
	}
}
