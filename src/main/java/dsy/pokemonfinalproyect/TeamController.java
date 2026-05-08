package dsy.pokemonfinalproyect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import dsy.pokemonfinalproyect.shapes.types.Pokemon;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TeamController {
    @FXML
    private Button txt_goBack;

    @FXML
    private GridPane grid_pokemons;

    @FXML
    public void handleBack(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) txt_goBack.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPokemonTem(List<Pokemon> team) {
        grid_pokemons.getChildren().clear();

        int column = 0;
        int row = 0;

        for (Pokemon pokemon : team) {
            HBox card = new HBox(15);
            card.setStyle("-fx-border-color: #CCCCCC; -fx-border-radius: 10; -fx-padding: 15; -fx-background-color: white; -fx-background-radius: 10;");
            card.setPrefWidth(540);

            VBox nameBox = new VBox(5);
            Label nameLabel = new Label(pokemon.getName());
            nameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
            Label levelLabel = new Label("Lv. " + pokemon.getLevel());
            nameBox.getChildren().addAll(nameLabel, levelLabel);

            VBox hpBox = new VBox(5);
            Label hpLabel = new Label("HP: " + pokemon.getHp() + "/" + pokemon.getMaxHp());
            ProgressBar hpBar = new ProgressBar((double) pokemon.getHp() / pokemon.getMaxHp());
            hpBar.setMaxWidth(Double.MAX_VALUE);
            hpBar.setPrefHeight(15);
            hpBox.getChildren().addAll(hpLabel, hpBar);

            HBox.setHgrow(hpBox, Priority.ALWAYS);

            card.getChildren().addAll(nameBox, hpBox);
            grid_pokemons.add(card, column, row);

            column++;
            if (column > 1) {
                column = 0;
                row++;
            }
        }
    }
}