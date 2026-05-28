package dsy.pokemonfinalproyect.shapes;

import dsy.pokemonfinalproyect.OptionsController;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public interface IScreeenSettings {
    public static void syncGameSettings(Region rootLayout, Node nodeForStage) {
        // 1. Control del color de fondo (Original Blanco vs Opuesto Negro)
        if (OptionsController.isDarkMode) {
            rootLayout.setStyle("-fx-background-color: #121212;");
        } else {
            rootLayout.setStyle("-fx-background-color: #ffffff;");
        }

        // 2. Control de pantalla completa sin usar retornos intermedios
        Platform.runLater(() -> {
            if (nodeForStage != null && nodeForStage.getScene() != null && nodeForStage.getScene().getWindow() != null) {
                Stage stage = (Stage) nodeForStage.getScene().getWindow();

                if (OptionsController.windowMode.equalsIgnoreCase("Fullscreen")) {
                    stage.setFullScreen(true);
                } else {
                    stage.setFullScreen(false);
                }
            }
        });
    }
}