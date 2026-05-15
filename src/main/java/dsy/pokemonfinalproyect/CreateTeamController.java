package dsy.pokemonfinalproyect;

import dsy.pokemonfinalproyect.shapes.types.Game;
import dsy.pokemonfinalproyect.shapes.types.Pokemon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateTeamController {

    @FXML private ListView<String> listBox;
    @FXML private ListView<String> listTeam;
    @FXML private Button btn_goBack;

    @FXML
    public void initialize() {
        refreshLists();
    }

    private void refreshLists() {
        ObservableList<String> teamItems = FXCollections.observableArrayList();
        for (Pokemon p : Game.currentTrainer.getTeam()) {
            teamItems.add(p.getName() + " - Niv: " + p.getLevel());
        }
        listTeam.setItems(teamItems);

        ObservableList<String> boxItems = FXCollections.observableArrayList();
        for (Pokemon p : Game.existingPokemons) {
            if (!Game.currentTrainer.getTeam().contains(p)) {
                boxItems.add(p.getName());
            }
        }
        listBox.setItems(boxItems);
    }

    @FXML
    private void handleAddToTeam() {
        String selectedName = listBox.getSelectionModel().getSelectedItem();

        if (selectedName != null) {

            if (Game.currentTrainer.getTeam().size() < 6) {
                for (Pokemon p : Game.existingPokemons) {
                    if (p.getName().equals(selectedName)) {
                        Game.currentTrainer.addPokemon(p);
                        break;
                    }
                }

                refreshLists();

            } else {
                showWarning("Equipo lleno", "No puedes tener más de 6 Pokémon en tu equipo.");
            }
        }
    }

    @FXML
    private void handleRemoveFromTeam() {
        int selectedIdx = listTeam.getSelectionModel().getSelectedIndex();

        if (selectedIdx != -1) {
            Game.currentTrainer.getTeam().remove(selectedIdx);
            refreshLists();
        }
    }

    private void showWarning(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleBack(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btn_goBack.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}