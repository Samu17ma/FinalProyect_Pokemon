package dsy.pokemonfinalproyect;

import dsy.pokemonfinalproyect.shapes.types.Game;
import dsy.pokemonfinalproyect.shapes.types.Pokemon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TeamController {
    @FXML private Button txt_goBack;
    @FXML private GridPane grid_pokemons;

    @FXML
    public void initialize() {
        // Limpiamos el grid antes de cargar para evitar duplicados
        grid_pokemons.getChildren().clear();

        if (Game.currentTrainer != null) {
            List<Pokemon> team = Game.currentTrainer.getTeam();

            int col = 0;
            int row = 0;

            // Siempre iteramos 6 veces para llenar los huecos vacíos si el equipo es menor
            for (int i = 0; i < 6; i++) {
                HBox card;
                if (i < team.size()) {
                    card = createCard(team.get(i));
                } else {
                    card = createEmptyCard();
                }
                grid_pokemons.add(card, col, row);

                col++;
                if (col == 2) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private HBox createCard(Pokemon p) {
        HBox card = new HBox(20);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-border-color: #CCCCCC; -fx-border-radius: 10; -fx-padding: 15; -fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        card.setPrefHeight(120);

        // Bloque Izquierdo: Nombre y Nivel
        VBox infoBox = new VBox(5);
        infoBox.setMinWidth(160); // Espacio garantizado para el nombre
        infoBox.setAlignment(Pos.CENTER_LEFT);

        Label lblName = new Label(p.getName().toUpperCase());
        lblName.setFont(Font.font("System", FontWeight.BOLD, 20));
        lblName.setStyle("-fx-text-fill: black;");

        Label lblLevel = new Label("Lv. " + p.getLevel());
        lblLevel.setFont(Font.font("System", FontWeight.NORMAL, 14));
        lblLevel.setStyle("-fx-text-fill: #666666;");

        infoBox.getChildren().addAll(lblName, lblLevel);

        // Bloque Derecho: HP y Barra de vida
        VBox barBox = new VBox(8);
        barBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(barBox, Priority.ALWAYS);

        Label lblHp = new Label("HP: " + p.getHp() + " / " + p.getMaxHp());
        lblHp.setFont(Font.font("System", FontWeight.MEDIUM, 13));
        lblHp.setStyle("-fx-text-fill: black;");

        double progress = (p.getMaxHp() > 0) ? (double) p.getHp() / p.getMaxHp() : 0;
        ProgressBar hpBar = new ProgressBar(progress);
        hpBar.setMaxWidth(Double.MAX_VALUE);
        hpBar.setPrefHeight(18);

        // Color de la barra: Verde (puedes cambiarlo a rojo si progress < 0.2)
        hpBar.setStyle("-fx-accent: #2ecc71;");

        barBox.getChildren().addAll(lblHp, hpBar);

        card.getChildren().addAll(infoBox, barBox);
        return card;
    }

    private HBox createEmptyCard() {
        HBox card = new HBox();
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-border-color: #DDDDDD; -fx-border-style: dashed; -fx-border-radius: 10; -fx-padding: 15; -fx-background-color: #F9F9F9;");
        card.setPrefHeight(120);

        Label lblEmpty = new Label("Empty Slot");
        lblEmpty.setStyle("-fx-text-fill: #AAAAAA;");
        card.getChildren().add(lblEmpty);

        return card;
    }

    public void handleBack(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) txt_goBack.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}