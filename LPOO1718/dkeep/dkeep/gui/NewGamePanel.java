package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dkeep.logic.Dungeon;
import dkeep.logic.Game;
import dkeep.logic.GameLogic;
import dkeep.logic.Keep;

public class NewGamePanel extends JPanel implements ActionListener {
	
	private DkeepGUI gui;
	private JLabel gameState;
	private SettingsBtn settingsBtn;
	private Settings settingsInfo;
	private JButton btnNewGame;
	
	NewGamePanel (DkeepGUI gui, JLabel label) {

		this.gui = gui;
		this.gameState = label;
		
		this.btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(614, 172, 97, 25);
		btnNewGame.setEnabled(true);
		btnNewGame.addActionListener(this);
		
		this.settingsInfo = new Settings (gameState, btnNewGame);
		add(btnNewGame);

		this.settingsBtn = new SettingsBtn (settingsInfo);
		add(settingsBtn);
		
		
		this.editFrame = new EditMap ();
		add(editFrame);
		
		this.editMapBtn = new EditBtn (editFrame);
		add(editMapBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Dungeon level1 = new Dungeon (settingsInfo.get_selected_guard());
		Keep level2 = new Keep (settingsInfo.get_nr_ogres());
		
		gui.start_new_game (new Game (new GameLogic [] { level1, level2 }));
	}
}
