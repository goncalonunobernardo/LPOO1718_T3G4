package dkeep.frame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import dkeep.serialization.SaveLoad;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private DkeepGUI gui = new DkeepGUI (this);
	private NewGamePanel newGamePanel;
	private MoveButtons moveBtns;
	private JTextArea textArea;
	private ExitButton btnExitGame;
	private JLabel gameLabel;
	
	private GraphicsPanel images;
	private JButton saveG, loadG;
	private EditFrame editFrame;

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
		gameLabel.setBounds(23, 562, 508, 16);
		getContentPane().add(gameLabel);
		
		this.newGamePanel = new NewGamePanel (gui, gameLabel);
		newGamePanel.setBounds(88, 45, 269, 31);
		getContentPane().add(newGamePanel);
		
		this.editFrame = new EditFrame (gui);

		EditBtn editMapBtn = new EditBtn (editFrame);
		editMapBtn.setBounds(369, 51, 114, 25);
		getContentPane().add(editMapBtn);
		
		this.moveBtns = new MoveButtons(gui);
		moveBtns.setSize(182, 123);
		moveBtns.setLocation(578, 223);
		getContentPane().add(moveBtns);
		
		this.btnExitGame = new ExitButton();
		btnExitGame.setBounds(615, 458, 97, 39);
		getContentPane().add(btnExitGame);
		
		this.images = new GraphicsPanel(gui);
		images.setBounds(23, 102, 524, 433);
		images.setFocusable(true);
		getContentPane().add(images);
		
		SaveLoad slg = new SaveLoad();
		
		saveG = new JButton("SAVE");
        saveG.setBounds(559, 357, 97, 25);
        saveG.setEnabled(false);
        saveG.addActionListener(new ActionListener() {
        		@Override
            public void actionPerformed(ActionEvent evt) {
                slg.Save_Game(gui.get_game());
            }
        });

        loadG = new JButton("LOAD");
        loadG.setEnabled(false);
        loadG.setBounds(689, 357, 97, 25);
        loadG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				slg.Load_Game(gui);
				images.set_map(gui.get_map().get_matrix());
				images.repaint();
			}
        });

        getContentPane().add(saveG);
        getContentPane().add(loadG);

		images.repaint();
	}
	
	public void set_label (String label) {
		gameLabel.setText(label);
	}
	
	public void set_text (String map) {
		textArea.setText(map);
	}

	public void start_game(char[][] map) {
		enable_buttons(true);
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
		loadG.setEnabled(true);
		saveG.setEnabled(true);
	}

	public JLabel get_label() {
		return this.gameLabel;
	}
}

