package dsy.pokemonfinalproyect;

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
}
