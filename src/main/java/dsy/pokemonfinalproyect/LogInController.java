package dsy.pokemonfinalproyect;

import dsy.pokemonfinalproyect.shapes.types.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class LogInController {
    @FXML
    private TextField txt_user;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label lbl_message;

    public void handleEnter(ActionEvent actionEvent) {
        String user = txt_user.getText();
        String pass = txt_password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            lbl_message.setText("Cannot login without user or password");
        }

        if (Game.validateLogin(user, pass)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = (Stage) txt_user.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                lbl_message.setText("Error loading the next screen. Please try again");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else  {
            lbl_message.setText("Incorrect username or password");
        }
    }

    public void handleBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) txt_user.getScene().getWindow();
        stage.setScene(scene);
    }
}
