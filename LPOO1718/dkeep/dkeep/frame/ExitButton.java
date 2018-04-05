package dkeep.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ExitButton extends JButton implements ActionListener {
	
	
	ExitButton() {

		super();
		
		this.setText("Exit");
		setBounds(614, 460, 97, 25);
		this.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0); 
	}
}
