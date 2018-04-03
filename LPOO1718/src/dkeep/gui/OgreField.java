package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OgreField extends JPanel implements ActionListener {
	
	private JLabel nrOgres;
	private DkeepGUI gui;
	private JTextField ogreField;
	private JButton newGameBtn;
	
	OgreField (DkeepGUI gui, JButton newGameBtn) {
		this.gui = gui;
		this.newGameBtn = newGameBtn;
		
		this.ogreField = new JTextField("0");
		ogreField.setBounds(57, 43, 108, 16);
		ogreField.addActionListener(this);
		ogreField.setColumns(10);
		
		this.nrOgres = new JLabel("Number of Ogres:");
		nrOgres.setBounds(177, 40, 54, 22);
		
		add (nrOgres);
		add (ogreField);
	}
	
	public int get_nr_ogres () {
		try {
			int nr_ogres = Integer.parseInt(ogreField.getText());
			return nr_ogres;
		}
		catch(NumberFormatException e){
			return 0;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		try {
			int nr_ogres = Integer.parseInt(ogreField.getText());
			
			if (nr_ogres > 5) {
				gui.set_label("Number of ogres is way high! Have some mercy...");
				newGameBtn.setEnabled(false);
			}
			else if (nr_ogres < 1) {
				gui.set_label("Number of ogres is too low...");
				newGameBtn.setEnabled(false);
			}
			else {
				gui.set_label("You can play now.");
				newGameBtn.setEnabled(true);
			}
		}
		catch(NumberFormatException e){
			gui.set_label("Invalid number of ogres");
			newGameBtn.setEnabled(false);
		}
	}
}
