package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditFrame extends JFrame implements ActionListener {
	
	private JButton exitBtn;
	private JLabel editLbl;
	private SizePanel sizePanel;
	private EditPanel editPanel;
	private DkeepGUI gui;
	
	EditFrame (DkeepGUI gui) {
		getContentPane().setLayout(null);

		this.setBounds(100, 100, 806, 606);
		this.setResizable(false);
		
		editLbl = new JLabel("It's ready to start editing");
		editLbl.setBounds(23, 562, 440, 16);
		getContentPane().add(editLbl);
		
		exitBtn = new JButton("Apply");
		exitBtn.setBounds(685, 547, 68, 29);
		exitBtn.addActionListener(this);
		getContentPane().add(exitBtn);
		
		editPanel = new EditPanel (gui.get_images(), editLbl, exitBtn);
		editPanel.setBounds(23, 84, 747, 466);
		editPanel.setEnabled(true);
		getContentPane().add (editPanel);
		
		sizePanel = new SizePanel(editLbl, editPanel);
		sizePanel.setBounds(42, 18, 404, 45);
		getContentPane().add(sizePanel);
		
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		char [][] matrix = editPanel.get_matrix();
		
		gui.set_map(matrix);
		this.setVisible(false);
	}
}
