package com.iplusplus.custopoly.model;

import java.io.IOException;
import android.content.Context;
import com.iplusplus.custopoly.Custopoly;
import com.iplusplus.custopoly.model.gamemodel.GameFacade;
import com.iplusplus.custopoly.model.gamemodel.element.Game;
import com.iplusplus.custopoly.app.R;

import java.io.*;

/**
 * Singleton class in charge of saving and loading Custopoly games from memory
 *
 * @author Fran Lozano
 *
 */

public class SaveGameHandler {

    private String GENERIC_GAME_NAME = Custopoly.getAppContext().getString(R.string.generic_game_name);

    private static SaveGameHandler INSTANCE;

    /**
     * Constructor of the class. Private as it's a Singleton class
     */
    private SaveGameHandler() {
    }

    /**
     * Factory Method for Singleton object.
     *
     * @return Returns the single instance of SaveGameHandler that there should be.
     * It initializes INSTANCE if it's not initialized, and then return it.
     */
    public static SaveGameHandler getInstance() {
        if (INSTANCE == null) {
            // Create the instance
            INSTANCE = new SaveGameHandler();
        }
        return INSTANCE;
    }

    /**
     * Save a Custopoly game to a file.
     *
     * @param gameFacade The {@link Game} to save
     * @param name The file name to save this game under
     * @throws IOException When there is an error saving the game
     */
    public void saveGame(GameFacade gameFacade, String name) throws IOException {
        Context context = Custopoly.getAppContext();
        FileOutputStream fos = context.openFileOutput(name,
                Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(gameFacade);
        oos.close();
        fos.close();
    }

    /**
     * Save a Custopoly game to a file. NO NAME
     *
     * @param game The {@link com.iplusplus.custopoly.model.gamemodel.element.Game} to save.
     * @throws IOException When there is an error saving the game
     */
    public void saveGame(GameFacade game) throws IOException {
        Context context = Custopoly.getAppContext();
        FileOutputStream fos = context.openFileOutput(this.GENERIC_GAME_NAME,
                Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(game);
        oos.close();
        fos.close();
    }

    /**
     * Load a game from a file
     *
     * @param name The file name from which the game should be loaded
     * @return A {@link Game}, representing the loaded game
     * @throws IOException When there is an error while loading the game
     * @throws ClassNotFoundException When there is an error while loading the game
     */
    public GameFacade loadGame(String name)
            throws IOException, ClassNotFoundException {
        Context context = Custopoly.getAppContext();
        File file = new File(context.getFilesDir(), name);
        if (file.exists()) {
            FileInputStream fis;
            fis = context.openFileInput(name);
            ObjectInputStream ois = new ObjectInputStream(fis);
            GameFacade game = (Game) ois.readObject();
            ois.close();
            fis.close();
            return game;
        }
        return null;
    }
    /**
     * Load a game from a file, the game saved under the generic_game_name resource name.
     *
     * @return A {@link Game}, representing the loaded game
     * @throws IOException            When there is an error while loading the game
     * @throws ClassNotFoundException When there is an error while loading the game
     */
    public GameFacade loadGame()
            throws IOException, ClassNotFoundException {
        Context context = Custopoly.getAppContext();
        File file = new File(context.getFilesDir(), this.GENERIC_GAME_NAME);
        if (file.exists()) {
            FileInputStream fis;
            fis = context.openFileInput(this.GENERIC_GAME_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Game game = (Game) ois.readObject();
            ois.close();
            fis.close();
            return game;
        }
        return null;
    }

}
