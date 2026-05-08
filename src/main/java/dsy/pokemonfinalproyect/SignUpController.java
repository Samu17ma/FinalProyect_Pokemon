package dsy.pokemonfinalproyect;

import dsy.pokemonfinalproyect.shapes.types.Game;
import dsy.pokemonfinalproyect.shapes.types.Trainer;
import dsy.pokemonfinalproyect.shapes.types.Pokemon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


public class SignUpController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> pokemonChoice;

    @FXML
    public void initialize() {
        Game.loadGame();
        if (Game.existingPokemons.isEmpty()) {
            Game.loadPokemonsFromFile();
        }

        int count = 0;
        for (Pokemon p : Game.existingPokemons) {
            if (count < 3) {
                pokemonChoice.getItems().add(p.getName());
                count++;
            } else {
                break;
            }
        }
    }

    @FXML
    protected void onSignUpButtonClick(ActionEvent event) {
        String name = usernameField.getText();
        String pass = passwordField.getText();
        String pName = pokemonChoice.getValue();

        if (!name.isEmpty() && !pass.isEmpty() && pName != null) {
            Trainer newTrainer = new Trainer(name);

            for (Pokemon p : Game.existingPokemons) {
                if (p.getName().equals(pName)) {
                    newTrainer.addPokemon(p);
                    break;
                }
            }

            Game.users.add(newTrainer);
            Game.registerUserToFile(name, pass);

            onBackButtonClick(event);
        }
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
