package dsy.pokemonfinalproyect;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class OptionsController {

    @FXML private VBox optionsRoot;
    @FXML private Slider volumeSlider;
    @FXML private CheckBox muteCheckBox;
    @FXML private CheckBox darkModeCheckBox;
    @FXML private ChoiceBox<String> windowModeChoice;

    // Persisted application settings held in runtime memory
    public static double masterVolume = 50.0;
    public static boolean isMuted = false;
    public static boolean isDarkMode = false;
    public static String windowMode = "Windowed";

    @FXML
    public void initialize() {
        windowModeChoice.setItems(FXCollections.observableArrayList("Windowed", "Fullscreen"));

        volumeSlider.setValue(masterVolume);
        muteCheckBox.setSelected(isMuted);
        darkModeCheckBox.setSelected(isDarkMode);
        windowModeChoice.setValue(windowMode);

        if (isDarkMode) {
            optionsRoot.setStyle("-fx-background-color: #121212;");
        } else {
            optionsRoot.setStyle("-fx-background-color: #ffffff;");
        }
    }

    @FXML
    protected void onSaveButtonClick(ActionEvent event) {
        masterVolume = volumeSlider.getValue();
        isMuted = muteCheckBox.isSelected();
        isDarkMode = darkModeCheckBox.isSelected();
        windowMode = windowModeChoice.getValue();

        System.out.println("Global game settings updated successfully.");

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            if (windowMode.equalsIgnoreCase("Fullscreen")) {
                stage.setFullScreen(true);
            } else {
                stage.setFullScreen(false);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Failed to display the menu layout scene: " + e.getMessage());
            e.printStackTrace();
        }
    }
}