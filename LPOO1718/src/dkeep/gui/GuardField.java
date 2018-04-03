package dkeep.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuardField extends JPanel {

	private JLabel guardPerson;
	private JComboBox guardList;
	
	GuardField () {
		this.guardPerson = new JLabel("Guard personality:");
		guardPerson.setBounds(57, 91, 108, 16);
		
		String [] guards = {"Rookie", "Drunken", "Suspicious"};

		this.guardList = new JComboBox<Object> (guards);
		guardList.setBounds(177, 88, 145, 22);
		
		add(guardPerson);
		add(guardList);
	}
	
	public String get_selected_guard () {
		return (String) guardList.getSelectedItem();
	}
}
