package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.FlowLayout;


public class GameFrame extends JFrame {

	private DkeepGUI gui = new DkeepGUI (this);
	private NewGame newGamePanel;
	private MoveButtons moveBtns;
	private JTextArea textArea;
	private ExitButton btnExitGame;
	private JLabel gameLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame window = new GameFrame ();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("DUNGEON KEEP");
		this.setBounds(100, 100, 806, 606);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		this.textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setEditable(false);
		textArea.setBounds(23, 140, 508, 368);
		textArea.setFocusable(true);
		getContentPane().add(textArea);
		textArea.addKeyListener(new TextAreaList (gui));
		
		this.gameLabel = new JLabel("Invalid number of ogres");
		gameLabel.setBounds(23, 530, 508, 16);
		getContentPane().add(gameLabel);
		
		this.newGamePanel = new NewGame (gui);
		newGamePanel.setBounds(23, 43, 754, 147);
		getContentPane().add(newGamePanel);
		
		this.moveBtns = new MoveButtons(gui);
		moveBtns.setSize(182, 123);
		moveBtns.setLocation(577, 225);
		getContentPane().add(moveBtns);
		
		this.btnExitGame = new ExitButton();
		btnExitGame.setBounds(614, 460, 97, 39);
		getContentPane().add(btnExitGame);
	}
	
	public void set_label (String label) {
		gameLabel.setText(label);
	}
	
	public void set_text (String map) {
		textArea.setText(map);
	}

	public void start_game(String map) {
		moveBtns.enable_buttons(true);
		textArea.requestFocusInWindow();
		set_text (map);
	}

	public void enable_buttons(boolean enable_value) {
		moveBtns.enable_buttons(enable_value);
	}
}

