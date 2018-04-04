package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class GameFrame extends JFrame {

	private DkeepGUI gui = new DkeepGUI (this);
	private NewGamePanel newGamePanel;
	private MoveButtons moveBtns;
	private JTextArea textArea;
	private ExitButton btnExitGame;
	private JLabel gameLabel;
	
	private GraphicsPanel images;
	private JButton editMap;

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

		this.gameLabel = new JLabel("You can play now");
		gameLabel.setBounds(23, 530, 508, 16);
		getContentPane().add(gameLabel);
		
		this.newGamePanel = new NewGamePanel (gui, gameLabel);
		newGamePanel.setBounds(23, 43, 754, 85);
		getContentPane().add(newGamePanel);
		
		this.moveBtns = new MoveButtons(gui);
		moveBtns.setSize(182, 123);
		moveBtns.setLocation(577, 225);
		getContentPane().add(moveBtns);
		
		this.btnExitGame = new ExitButton();
		btnExitGame.setBounds(614, 460, 97, 39);
		getContentPane().add(btnExitGame);
		
		this.images = new GraphicsPanel(gui);
		images.setBounds(23, 140, 508, 368);
		images.setFocusable(true);
		getContentPane().add(images);
		images.repaint();
	}
	
	public void set_label (String label) {
		gameLabel.setText(label);
	}
	
	public void set_text (String map) {
		textArea.setText(map);
	}

	public void start_game(char[][] map) {
		moveBtns.enable_buttons(true);
		images.requestFocusInWindow();
		images.set_map(map);
		images.repaint();
	}
	
	public void update_map (char[][] map) {
		images.set_map(map);
		images.repaint();
	}

	public void enable_buttons(boolean enable_value) {
		moveBtns.enable_buttons(enable_value);
	}

	public JLabel get_label() {
		return this.gameLabel;
	}
}

