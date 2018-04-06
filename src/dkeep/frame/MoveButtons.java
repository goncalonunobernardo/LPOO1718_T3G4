package dkeep.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MoveButtons extends JPanel implements ActionListener {
	
	private  JButton btnUp, btnDown, btnLeft, btnRight;
	private DkeepGUI gui;
	
	
	MoveButtons (DkeepGUI gui) {

		super();
		
		this.gui = gui;

		this.setBounds (559, 277, 184, 104);
		
		this.btnRight = new JButton("right");
		btnRight.setBounds(103, 39, 75, 29);
		btnRight.setLayout(null);
		btnRight.addActionListener(this);
		btnRight.setActionCommand("d");

		this.btnUp = new JButton("up");
		btnUp.setBounds(55, 5, 75, 29);
		btnUp.addActionListener(this);
		btnUp.setActionCommand("w");

		this.btnLeft = new JButton("left");
		btnLeft.setBounds(14, 39, 75, 29);
		btnLeft.addActionListener(this);
		btnLeft.setActionCommand("a");

		this.btnDown = new JButton("down");
		btnDown.setBounds(55, 69, 78, 29);
		btnDown.addActionListener(this);
		btnDown.setActionCommand("s");
		
		enable_buttons (false);
		setLayout(null);

		add (btnRight);
		add (btnDown);
		add (btnLeft);
		add (btnUp);
	}
	
	public void enable_buttons(boolean enable_value) {
		btnRight.setEnabled(enable_value);
		btnLeft.setEnabled(enable_value);
		btnDown.setEnabled(enable_value);
		btnUp.setEnabled(enable_value);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gui.play(evt.getActionCommand().charAt(0));
		
	}
}
