package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dungeonkeep_gui {

	private JFrame frmDungeonkeepgui;
	private JTextField textField;

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

		textField = new JTextField();
		textField.setBounds(177, 40, 54, 22);
		frmDungeonkeepgui.getContentPane().add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(3);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Rookie", "Drunken", "Suspicious"}));
		comboBox.setBounds(177, 88, 145, 22);
		frmDungeonkeepgui.getContentPane().add(comboBox);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewGame.setBounds(614, 172, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnNewGame);

		JButton btnExitGame = new JButton("Exit");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExitGame.setBounds(614, 460, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnExitGame);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(23, 140, 508, 368);
		frmDungeonkeepgui.getContentPane().add(textArea);

		JButton btnNewButton = new JButton("up");
		btnNewButton.setBounds(614, 277, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnNewButton);

		JButton btnDown = new JButton("down");
		btnDown.setBounds(614, 353, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnDown);

		JButton btnLeft = new JButton("left");
		btnLeft.setBounds(559, 315, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("right");
		btnRight.setBounds(668, 315, 97, 25);
		frmDungeonkeepgui.getContentPane().add(btnRight);

		JLabel lblYouCanStart = new JLabel("You can start a new game [IN TEST]");
		lblYouCanStart.setBounds(23, 530, 508, 16);
		frmDungeonkeepgui.getContentPane().add(lblYouCanStart);
		/*
		//Handling entry value for number of ogres
		textField.;
		int n_ogres = Integer.parseInt(textField.getText());
		if(n_ogres > 5) lblYouCanStart.setText("Number of ogres is way high! Have some mercy...");
		else if(n_ogres < 0) lblYouCanStart.setText("Number of ogres is too low...");

		try{
			int n_ogres = Integer.parseInt(textField.getText());
		catch(NumberFormatExceptinon e){
			if()
		}
		 */
	}
}

