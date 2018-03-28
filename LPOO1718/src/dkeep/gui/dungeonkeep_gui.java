package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dkeep.logic.Dungeon;
import dkeep.logic.Game;
import dkeep.logic.GameLogic;
import dkeep.logic.GameState;
import dkeep.logic.Keep;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class dungeonkeep_gui {

	private JFrame frmDungeonkeepgui;
	private JTextField ogreField;
	private JTextArea textArea;
	private JButton btnRight, btnLeft, btnUp, btnDown, btnExitGame, btnNewGame;
	private JComboBox <String> guardList;
	private JLabel gameState;
	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dungeonkeep_gui window = new dungeonkeep_gui();
					window.frmDungeonkeepgui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dungeonkeep_gui() {
		initialize();
		start_game();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmDungeonkeepgui = new JFrame();
		frmDungeonkeepgui.setTitle("DUNGEON KEEP");
		frmDungeonkeepgui.setBounds(100, 100, 806, 606);
		frmDungeonkeepgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonkeepgui.getContentPane().setLayout(null);

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres:");
		lblNumberOfOgres.setBounds(57, 43, 108, 16);
		frmDungeonkeepgui.getContentPane().add(lblNumberOfOgres);

		JLabel lblGuardPersonality = new JLabel("Guard personality:");
		lblGuardPersonality.setBounds(57, 91, 108, 16);
		frmDungeonkeepgui.getContentPane().add(lblGuardPersonality);

		this.ogreField = new JTextField("0");
		ogreField.setBounds(177, 40, 54, 22);
		frmDungeonkeepgui.getContentPane().add(ogreField);
		ogreField.setColumns(10);
		
		String [] guards = {"Rookie", "Drunken", "Suspicious"};

		this.guardList = new JComboBox <> (guards);
		guardList.setBounds(177, 88, 145, 22);
		frmDungeonkeepgui.getContentPane().add(guardList);

		this.textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setBounds(23, 140, 508, 368);
		frmDungeonkeepgui.getContentPane().add(textArea);
		
		this.btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(614, 172, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnNewGame);
		btnNewGame.setEnabled(false);

		this.btnExitGame = new JButton("Exit");
		btnExitGame.setBounds(614, 460, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnExitGame);

		this.btnUp = new JButton("up");
		btnUp.setBounds(614, 277, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnUp);
		btnUp.setEnabled(false);

		this.btnDown = new JButton("down");
		btnDown.setBounds(614, 353, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnDown);
		btnDown.setEnabled (false);
		
		this.btnLeft = new JButton("left");
		btnLeft.setBounds(559, 315, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnLeft);
		btnLeft.setEnabled(false);
		
		this.btnRight = new JButton("right");
		btnRight.setBounds(668, 315, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnRight);
		btnRight.setEnabled(false);
		
		this.gameState = new JLabel("Invalid number of ogres");
		gameState.setBounds(23, 530, 508, 16);
		frmDungeonkeepgui.getContentPane().add(gameState);
		
	}
	
	private void start_game () {
		
		ogreField.addActionListener(new ActionListener ()  {

			public void actionPerformed(ActionEvent evt) {
				
				try {
					int nr_ogres = Integer.parseInt(ogreField.getText());
					
					if (nr_ogres > 5) {
						gameState.setText("Number of ogres is way high! Have some mercy...");
						btnNewGame.setEnabled(false);
					
					}
					else if (nr_ogres < 1) {
						gameState.setText("Number of ogres is too low...");
						btnNewGame.setEnabled(false);
					}
					else {
						gameState.setText("You can play now.");
						btnNewGame.setEnabled(true);
					}
				}
				catch(NumberFormatException e){
					gameState.setText("Invalid number of ogres");
					btnNewGame.setEnabled(false);
				}
			}
			
		});
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
				int nr_ogres = Integer.parseInt(ogreField.getText());
				String type_guard = (String) guardList.getSelectedItem();
				
				GameLogic [] levels = new GameLogic [] {new Dungeon (type_guard), new Keep (nr_ogres)};
				
				game = new Game (levels);
				textArea.setText(game.get_map().toString());
				gameState.setText("Playing");
				enable_buttons ();
			}
		});
		
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void enable_buttons () {
		btnRight.setEnabled(true);
		btnLeft.setEnabled(true);
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.play('d');
				textArea.setText(game.get_map().toString());
			}
		});
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.play('a');
				textArea.setText(game.get_map().toString());
			}
		});

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.play('w');
				textArea.setText(game.get_map().toString());
			}
		});
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.play('s');
				textArea.setText(game.get_map().toString());
			}
		});
	}
}

