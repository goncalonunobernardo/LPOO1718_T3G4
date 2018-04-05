package dkeep.serialization;

import dkeep.gui.DkeepGUI;
import dkeep.logic.Game;
import dkeep.logic.GameState;

import java.io.*;

public class SaveLoad {
    public void Save_Game(Game game) {
            try {
                OutputStream file = new FileOutputStream("game.ser");
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
                output.writeObject(game);
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Exception thrown save");
            }


    }

    public void Load_Game(DkeepGUI gui) {
            try {
                FileInputStream fileIn = new FileInputStream("game.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                gui.start_new_game( (Game) in.readObject());
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                System.out.println("Exception thrown load");
            } catch (ClassNotFoundException e) {
				e.printStackTrace();
                System.out.println("Exception thrown load game");
			}
    }
}