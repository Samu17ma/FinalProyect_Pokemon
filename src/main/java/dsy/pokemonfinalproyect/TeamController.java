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
        System.out.println("DEBUG: Entrando en TeamController");
        grid_pokemons.getChildren().clear();

        if (Game.currentTrainer == null) {
            System.out.println("ERROR: El entrenador actual es NULL");
            return;
        }

        List<Pokemon> team = Game.currentTrainer.getTeam();
        System.out.println("DEBUG: El equipo tiene " + team.size() + " pokemons.");

        int col = 0;
        int row = 0;

        for (int i = 0; i < 6; i++) {
            if (i < team.size()) {
                Pokemon p = team.get(i);
                System.out.println("DEBUG: Intentando crear carta para: " + p.getName());
                HBox card = createCard(p); // <-- Si esto falla aquí, se para todo
                grid_pokemons.add(card, col, row);
            } else {
                grid_pokemons.add(createEmptyCard(), col, row);
            }

            col++;
            if (col == 2) { col = 0; row++; }
        }
    }

    private HBox createCard(Pokemon p) {
        HBox card = new HBox(15);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-border-color: #CCCCCC; -fx-border-radius: 10; -fx-padding: 15; -fx-background-color: white; -fx-background-radius: 10;");
        card.setPrefHeight(120);

        // 1. Contenedor de Información (Nombre y Nivel)
        VBox infoBox = new VBox(5);
        infoBox.setMinWidth(150);

        Label lblName = new Label(p.getName().toUpperCase());
        lblName.setFont(Font.font("System", FontWeight.BOLD, 18));
        lblName.setStyle("-fx-text-fill: black;"); // Asegura que el texto sea visible

        Label lblLevel = new Label("Lv. " + p.getLevel());
        lblLevel.setStyle("-fx-text-fill: #555555;");

        infoBox.getChildren().addAll(lblName, lblLevel);

        // 2. Contenedor de la Barra de Vida y el texto de HP
        VBox barBox = new VBox(5);
        barBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(barBox, Priority.ALWAYS);

        // Texto de HP: "HP: 20 / 20"
        Label lblHp = new Label("HP: " + p.getHp() + " / " + p.getMaxHp());
        lblHp.setFont(Font.font("System", FontWeight.MEDIUM, 13));
        lblHp.setStyle("-fx-text-fill: black;"); // Forzamos color negro para que no desaparezca

        // Configuración de la barra
        double progress = (p.getMaxHp() > 0) ? (double) p.getHp() / p.getMaxHp() : 0;
        ProgressBar hpBar = new ProgressBar(progress);
        hpBar.setMaxWidth(Double.MAX_VALUE);

        // Aplicar color verde y asegurar que la barra tenga altura visible
        hpBar.setStyle("-fx-accent: #2ecc71;");
        hpBar.setPrefHeight(15);

        // IMPORTANTE: El orden de añadir determina la posición
        barBox.getChildren().clear();
        barBox.getChildren().addAll(lblHp, hpBar); // Primero el texto, luego la barra

        card.getChildren().addAll(infoBox, barBox);

        return card;
    }

    private HBox createEmptyCard() {
        HBox card = new HBox();
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-border-color: #DDDDDD; -fx-border-style: dashed; -fx-border-radius: 10; -fx-padding: 15;");
        card.setPrefHeight(120);
        card.getChildren().add(new Label("Empty Slot"));
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