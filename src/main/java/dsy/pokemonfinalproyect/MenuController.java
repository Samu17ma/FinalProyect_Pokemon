package dsy.pokemonfinalproyect;

import dsy.pokemonfinalproyect.shapes.types.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    public Button btn_Exit;

    @FXML
    private Label lbl_trainerId;

    @FXML
    private Button btn_Inventory;

    @FXML
    private Label lbl_pokemon;

    public void setDisplayName(String userName) {
        lbl_trainerId.setText(userName);
    }

    public void exitButton() {
        javafx.application.Platform.exit();
    }

    public void handleViewTeam() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("team-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btn_Inventory.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        if (Game.currentTrainer != null) {

            lbl_trainerId.setText("Trainer: " + Game.currentTrainer.getName());

            if (Game.currentTrainer.getTeam() != null && !Game.currentTrainer.getTeam().isEmpty()) {
                String firstPokeName = Game.currentTrainer.getTeam().get(0).getName();
                lbl_pokemon.setText("First pokemon -> " + firstPokeName);
            } else {
                lbl_pokemon.setText("First pokemon -> None");
            }
        }
    }
}
