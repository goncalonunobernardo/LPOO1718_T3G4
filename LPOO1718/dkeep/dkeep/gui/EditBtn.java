package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EditBtn extends JButton implements ActionListener {
	
	private EditFrame editFrame;
	
	EditBtn (EditFrame editFrame) {
		super();
		this.setText("Create Map");
		this.setBounds(0, 0, 100, 38);
		this.addActionListener(this);
		this.editFrame = editFrame; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		editFrame.setVisible(true);
	}
}
