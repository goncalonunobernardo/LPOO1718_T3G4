package dkeep.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SizePanel extends JPanel implements ActionListener {
	
	private JTextField widthText, heigthText;
	private JLabel widthLabel, heightLabel, editState;
	private  EditPanel editPanel;

	SizePanel (JLabel editState, EditPanel editPanel) {
		setLayout(null);
		widthLabel = new JLabel("Width:");
		widthLabel.setBounds(6, 10, 40, 16);
		add(widthLabel);
		
		widthText = new JTextField();
		widthText.setText("10");
		widthText.setBounds(51, 5, 130, 26);
		widthText.setColumns(10);
		widthText.addActionListener(this);
		add(widthText);
		
		heightLabel = new JLabel("Heigth:");
		heightLabel.setBounds(208, 10, 46, 16);
		add(heightLabel);
		
		heigthText = new JTextField();
		heigthText.setText("10");
		heigthText.setBounds(259, 5, 130, 26);
		heigthText.setColumns(10);
		heigthText.addActionListener(this);
		add(heigthText);
		
		this.editState = editState;
		this.editPanel = editPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		try {
			int width = Integer.parseInt(widthText.getText());
			int height = Integer.parseInt(heigthText.getText());
			
			if (width < 3 || width > 13 || height < 3 || height > 13) {
				editState.setText("Invalid size");
				editPanel.setEnabled(false);
			}
			else {
				editState.setText("It's ready to start editing");
				editPanel.setEnabled(true);
				editPanel.change_matrix_size(width, height);
				editPanel.repaint();
			}
		}
		catch(NumberFormatException e){
			editState.setText("Invalid size");
		}
	}
}
