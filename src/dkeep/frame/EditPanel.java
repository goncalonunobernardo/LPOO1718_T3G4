package dkeep.frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import dkeep.logic.Keep;

@SuppressWarnings("serial")
public class EditPanel extends JPanel implements ActionListener, MouseListener {

	private char [][] matrix;
	private char to_replace;
	private HashMap <Character, Image> images;
	private HashMap <Character, String> messages;
	private ButtonGroup buttons;
	private JRadioButton wallBtn, ogreBtn, heroBtn, keyBtn, doorBtn, emptyBtn, clubBtn;
	private JLabel mapState;
	private JButton exitBtn;

	EditPanel (HashMap <Character, Image> images, JLabel mapState, JButton exitBtn) {
		super();
		
		setLayout(null);
		
		this.setBounds(23, 102, 747, 433);
		
		matrix = Keep.default_matrix();
		
		buttons = new ButtonGroup ();
		
		messages = new HashMap <Character, String> ();
		
		messages.put('A', "Invalid map: hero missing or repeated");
		messages.put('O', "Invalid map: ogre, club misplaced, missing or repeated");
		messages.put('I', "Invalid map: no door on the left wall");
		messages.put('k', "Invalid map: key missing or repeated");
		messages.put(' ', "Ready to play");
		
		
		wallBtn = new JRadioButton ("Wall");
		wallBtn.setBounds(645, 203, 73, 23);
		wallBtn.addActionListener(this);
		wallBtn.setActionCommand("X");
		buttons.add(wallBtn);
		add(wallBtn);
		
		ogreBtn = new JRadioButton("Ogre");
		ogreBtn.setBounds(645, 63, 73, 23);
		ogreBtn.addActionListener(this);
		ogreBtn.setActionCommand("O");
		buttons.add(ogreBtn);
		add(ogreBtn);
		
		clubBtn = new JRadioButton("Club");
		clubBtn.setBounds(645, 98, 73, 23);
		clubBtn.addActionListener(this);
		clubBtn.setActionCommand("*");
		buttons.add(clubBtn);
		add(clubBtn);
		
		heroBtn = new JRadioButton("Hero");
		heroBtn.setBounds(645, 28, 73, 23);
		heroBtn.addActionListener(this);
		heroBtn.setActionCommand("A");
		buttons.add(heroBtn);
		add(heroBtn);
		
		keyBtn = new JRadioButton("Key");
		keyBtn.setBounds(645, 133, 73, 23);
		keyBtn.addActionListener(this);
		keyBtn.setActionCommand("k");
		buttons.add(keyBtn);
		add(keyBtn);
		
		doorBtn = new JRadioButton("Door");
		doorBtn.setBounds(645, 168, 73, 23);
		doorBtn.addActionListener(this);
		doorBtn.setActionCommand("I");
		buttons.add(doorBtn);
		add(doorBtn);
		
		emptyBtn  = new JRadioButton("Empty");
		emptyBtn.setBounds(645, 237, 73, 23);
		emptyBtn.addActionListener(this);
		emptyBtn.setActionCommand(" ");
		emptyBtn.setSelected(true);
		buttons.add(emptyBtn);
		add(emptyBtn);
		
		this.images = images;
		
		this.to_replace = ' ';
		
		this.mapState = mapState;
		
		this.addMouseListener(this);
		
		this.exitBtn = exitBtn;
	}
	
	public char [][] get_matrix() {
		return matrix;
	}
	
	public void change_matrix_size (int x, int y) {
		matrix = new char [y][x];
				
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (i == 0 || j == 0 || i == matrix.length - 1 || j == matrix[i].length - 1)
					matrix[i][j] = 'X';
				else
					matrix[i][j] = ' ';
			}
		}
		exitBtn.setEnabled(valid_map());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		to_replace = e.getActionCommand().charAt(0);
	}

	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);

		int x = 0, y = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				x = j * 35;
				y = i * 35;
		
			g.drawImage(images.get(matrix[i][j]), x, y, this);
			}
		}
	}
	
	public boolean check_char (char key) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == key)
					return true;
			}
		}
		
		return false;
	}
	
	public boolean check_repeated_char (char key) {
		boolean one_time = false;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == key) {
					
					if (!one_time)
						one_time = true;
					else
						return true;
					
				}
			}
		}
		
		return false;
	}

	public boolean check_surrounders (char found, char around) {
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == found) {
					
					if ((j > 0 && matrix[i][j - 1] == around) || (j < matrix[i].length - 1 && matrix[i][j + 1] == around))
						return true;
					else if ((i > 0 && matrix[i - 1][j] == around) || (i < matrix.length - 1 && matrix[i + 1][j] == around))
						return true;
					
				}
			}
		}
		
		return false;
		
	}
	
	public boolean check_door() {
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 'I') {
				return true;
			}
		}
		return false;
	}
	
	public boolean valid_map () {
		
		boolean valid_map = true;
		char key_missing = ' ';
		
		if (!check_char('A') || check_repeated_char('A')) {
			valid_map = false;
			key_missing = 'A';
		}
		else if (!check_char('O') || !check_surrounders ('O', '*') || !check_surrounders('*', 'O')|| check_repeated_char('O') || check_repeated_char('*')) {
			valid_map = false;
			key_missing = 'O';
		}
		else if (!check_char('k')) {
			valid_map = false;
			key_missing = 'k';
		}
		else if (!check_door()) {
			valid_map = false;
			key_missing = 'I';
		}
			
		mapState.setText(messages.get(key_missing));
		
		return valid_map;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		try {
			if (isEnabled()) {
				int x = e.getX() / 35;
				int y = e.getY() / 35;
				
				System.out.println("" + x + " " + y);

				matrix[y][x] = to_replace;

				this.repaint();
				
				exitBtn.setEnabled(valid_map());
			}
		}
		catch (IndexOutOfBoundsException ex) {
			exitBtn.setEnabled(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
