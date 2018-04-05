package dkeep.serialization;

import dkeep.logic.GameState;

import java.io.*;

public class SaveLoad {
    public void Save_Game(GameState status) {
            try {
                OutputStream file = new FileOutputStream("game.ser");
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
                output.writeObject(status);
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    public void Load_Game() {
            try {
                FileInputStream fileIn = new FileInputStream("game.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            }

    }


}