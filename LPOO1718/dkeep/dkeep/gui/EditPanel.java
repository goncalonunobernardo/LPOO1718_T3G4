package dkeep.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class EditPanel extends JPanel implements ActionListener, MouseListener {

	private char [][] matrix;
	private char to_replace;
	private HashMap <Character, Image> images;
	private ButtonGroup buttons;
	private JRadioButton wallBtn, ogreBtn, heroBtn, keyBtn, doorBtn, emptyBtn, clubBtn;

	EditPanel (HashMap <Character, Image> images) {
		setLayout(null);
		
		matrix = new char [][] {
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'I', ' ', ' ', ' ', 'O', '*', ' ', ' ', 'k', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
		
		buttons = new ButtonGroup ();
		
		wallBtn = new JRadioButton ("Wall");
		wallBtn.setBounds(503, 200, 73, 23);
		wallBtn.addActionListener(this);
		wallBtn.setActionCommand("X");
		buttons.add(wallBtn);
		add(wallBtn);
		
		ogreBtn = new JRadioButton("Ogre");
		ogreBtn.setBounds(503, 60, 73, 23);
		ogreBtn.addActionListener(this);
		ogreBtn.setActionCommand("O");
		buttons.add(ogreBtn);
		add(ogreBtn);
		
		clubBtn = new JRadioButton("Club");
		clubBtn.setBounds(503, 95, 73, 23);
		clubBtn.addActionListener(this);
		clubBtn.setActionCommand("*");
		buttons.add(clubBtn);
		add(clubBtn);
		
		heroBtn = new JRadioButton("Hero");
		heroBtn.setBounds(503, 25, 73, 23);
		heroBtn.addActionListener(this);
		heroBtn.setActionCommand("H");
		buttons.add(heroBtn);
		add(heroBtn);
		
		keyBtn = new JRadioButton("Key");
		keyBtn.setBounds(503, 130, 73, 23);
		keyBtn.addActionListener(this);
		keyBtn.setActionCommand("k");
		buttons.add(keyBtn);
		add(keyBtn);
		
		doorBtn = new JRadioButton("Door");
		doorBtn.setBounds(503, 165, 73, 23);
		doorBtn.addActionListener(this);
		doorBtn.setActionCommand("I");
		buttons.add(doorBtn);
		add(doorBtn);
		
		emptyBtn  = new JRadioButton("Empty");
		emptyBtn.setBounds(503, 234, 73, 23);
		emptyBtn.addActionListener(this);
		emptyBtn.setActionCommand(" ");
		emptyBtn.setSelected(true);
		buttons.add(emptyBtn);
		add(emptyBtn);
		
		this.images = images;
		
		this.to_replace = ' ';
		
		this.addMouseListener(this);
	}
	
	public void change_matrix (int x, int y) {
		char[][] temp = new char [x][y];
		
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				temp[i][j] = matrix[i][j];
			}
		}
		
		matrix = temp;
		
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


	@Override
	public void mousePressed(MouseEvent e) {
		
		if (isEnabled()) {
			int x = e.getX() / 35;
			int y = e.getY() / 35;

			matrix[y][x] = to_replace;

			this.repaint();
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
