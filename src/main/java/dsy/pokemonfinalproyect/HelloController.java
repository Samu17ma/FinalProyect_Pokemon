package dsy.pokemonfinalproyect;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    public Button btn_login;

    @FXML
    public Button btn_sinup;

    private void changeScene(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene newScene = new Scene(loader.load());

            Stage stage = (Stage) btn_login.getScene().getWindow();

            stage.setTitle(title);
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToStartScene(ActionEvent actionEvent) {
        changeScene("hello-view.fxml", "Pokémon Proyect");
    }

    public void logIn(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btn_login.getScene().getWindow();
            stage.setTitle("Login - Pokémon Project");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUp(ActionEvent actionEvent) {
    }
}
