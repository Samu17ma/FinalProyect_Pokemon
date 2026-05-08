package dsy.pokemonfinalproyect;

import dsy.pokemonfinalproyect.shapes.types.Game;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        Game.loadGame();
        Application.launch(HelloApplication.class, args);
    }
}
