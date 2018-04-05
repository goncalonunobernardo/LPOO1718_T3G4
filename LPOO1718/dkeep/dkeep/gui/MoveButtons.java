package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import java.awt.Rectangle;

public class MoveButtons extends JPanel implements ActionListener {
	
	private  JButton btnUp, btnDown, btnLeft, btnRight;
	private DkeepGUI gui;
	
	
	MoveButtons (DkeepGUI gui) {
		this.gui = gui;

		this.setBounds (559, 277, 184, 104);
		
		this.btnRight = new JButton("right");
		btnRight.setBounds(668, 315, 97, 25);
		btnRight.setLayout(null);
		btnRight.addActionListener(this);
		btnRight.setActionCommand("d");

		this.btnUp = new JButton("up");
		btnUp.setBounds(587, 2, 97, 25);
		btnUp.addActionListener(this);
		btnUp.setActionCommand("w");

		this.btnLeft = new JButton("left");
		btnLeft.setBounds(559, 315, 97, 25);
		btnLeft.addActionListener(this);
		btnLeft.setActionCommand("a");

		this.btnDown = new JButton("down");
		btnDown.setBounds(587, 353, 97, 25);
		btnDown.addActionListener(this);
		btnDown.setActionCommand("s");
		
		enable_buttons (false);

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
