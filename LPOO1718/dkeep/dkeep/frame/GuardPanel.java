package dkeep.frame;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GuardPanel extends JPanel {

	private JLabel guardPerson;
	@SuppressWarnings("rawtypes")
	private JComboBox guardList;
	
	GuardPanel () {

		this.setBounds(40, 40, 263, 40);
		
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
