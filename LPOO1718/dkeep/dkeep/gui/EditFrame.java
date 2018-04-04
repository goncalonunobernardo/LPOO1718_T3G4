package dkeep.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class EditFrame extends JFrame {
	
	private char [][] matrix;
	private JButton exitBtn;
	private JLabel editLbl;
	private SizePanel sizePanel;
	private EditPanel editPanel;
	
	EditFrame (HashMap <Character, Image> images) {
		getContentPane().setLayout(null);

		this.setBounds(100, 100, 806, 606);
		this.setResizable(false);
		
		editLbl = new JLabel("It's ready to start editing");
		editLbl.setBounds(6, 562, 440, 16);
		getContentPane().add(editLbl);
		
		exitBtn = new JButton("Apply");
		exitBtn.setBounds(702, 536, 68, 29);
		getContentPane().add(exitBtn);
		
		editPanel = new EditPanel (images);
		editPanel.setBounds(27, 75, 592, 475);
		editPanel.setEnabled(true);
		getContentPane().add (editPanel);
		
		sizePanel = new SizePanel(editLbl, editPanel);
		sizePanel.setBounds(42, 18, 404, 45);
		getContentPane().add(sizePanel);
	}
}
