package dsy.pokemonfinalproyect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    public Button btn_Exit;

    @FXML
    private Label lbl_trainerId;


    public void setDisplayName(String userName) {
        lbl_trainerId.setText(userName);
    }

    public void exitButton() {
    }
}
