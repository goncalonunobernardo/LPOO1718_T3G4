package dkeep.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dkeep.logic.Game;
import dkeep.logic.GameState;
import dkeep.serialization.SaveLoad;

public class SaveLoadButton extends JPanel {

    private  JButton saveG, loadG;
    private DkeepGUI gui;
    private SaveLoad slg;
    private GameState gameState;

    SaveLoadButton(DkeepGUI gui) {
        this.gui = gui;

        saveG = new JButton("SAVE");
        saveG.setBounds(550, 300, 97, 25);
        saveG.addActionListener(new ActionListener() {
        		@Override
            public void actionPerformed(ActionEvent evt) {
                slg.Save_Game(gameState);
            }
        });

        loadG = new JButton("LOAD");
        loadG.setBounds(680, 300, 97, 25);
        loadG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				slg.Load_Game();
			}
        });

        enable_buttons (false);

        add(saveG);
        add(loadG);
    }

    public void enable_buttons(boolean enable_value) {
        saveG.setEnabled(enable_value);
    }
}


