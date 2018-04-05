package dkeep.frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SettingsFrame extends JFrame {
	
	private OgrePanel ogre_sett;
	private GuardPanel guard_sett;
	
	
	SettingsFrame (JLabel label, JButton newGameBtn) {
		
		this.setTitle("Settings");
		this.setBounds(150, 150, 350, 100);
		this.setResizable(false);
		
		guard_sett = new GuardPanel ();
		getContentPane().add(guard_sett);
		
		ogre_sett = new OgrePanel (label, newGameBtn);
		getContentPane().add(ogre_sett);
	}
	
	
	public String get_selected_guard() {
		return guard_sett.get_selected_guard();
	}
	
	public int get_nr_ogres () {
		return ogre_sett.get_nr_ogres();
	}
}
