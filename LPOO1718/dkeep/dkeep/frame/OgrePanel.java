package dkeep.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OgrePanel extends JPanel implements ActionListener {
	
	private JLabel nrOgres, gameState;
	private JTextField ogreField;
	private JButton newGameBtn;
	
	OgrePanel (JLabel label, JButton newGameBtn) {

		super();
		
		this.setBounds(50, 0, 263, 40);
		
		this.newGameBtn = newGameBtn;
		
		this.gameState = label;
		
		this.ogreField = new JTextField("1");
		ogreField.setBounds(140, 5, 130, 26);
		ogreField.addActionListener(this);
		ogreField.setColumns(10);
		
		this.nrOgres = new JLabel("Number of Ogres:");
		nrOgres.setBounds(23, 10, 112, 16);
		
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
				gameState.setText("Number of ogres is way high! Have some mercy...");
				newGameBtn.setEnabled(false);
			}
			else if (nr_ogres < 1) {
				gameState.setText("Number of ogres is too low...");
				newGameBtn.setEnabled(false);
			}
			else {
				gameState.setText("You can play now.");
				newGameBtn.setEnabled(true);
			}
		}
		catch(NumberFormatException e){
			gameState.setText("Invalid number of ogres");
			newGameBtn.setEnabled(false);
		}
	}
}
