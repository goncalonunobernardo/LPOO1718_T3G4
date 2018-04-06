package dkeep.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GuardPanel extends JPanel implements ActionListener{

	private JLabel guardPerson, gameLabel;
	@SuppressWarnings("rawtypes")
	private JComboBox guardList;
	
	GuardPanel (JLabel label) {

		super();

		this.setBounds(40, 40, 263, 40);
		
		this.guardPerson = new JLabel("Guard personality:");
		guardPerson.setBounds(57, 91, 108, 16);
		
		String [] guards = {"Rookie", "Drunken", "Suspicious"};

		this.guardList = new JComboBox<Object> (guards);
		guardList.setBounds(177, 88, 145, 22);
		guardList.addActionListener(this);
		
		add(guardPerson);
		add(guardList);
		
		this.gameLabel = label;
	}
	
	public String get_selected_guard () {
		return (String) guardList.getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameLabel.setText("Start a new game to apply the new settings.");
	}
}
