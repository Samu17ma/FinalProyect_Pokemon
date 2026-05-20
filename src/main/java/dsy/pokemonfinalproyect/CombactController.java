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
import java.util.Random;

public class CombactController {

    private int index = 0;
    private Pokemon pokemonTrainer = Game.currentTrainer.getTeam().get(index);
    private Random random = new Random();
    private Pokemon rivalPokemon = Game.existingPokemons.get(random.nextInt(151) + 1);

    @FXML
    private void flee(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pokemonAttack(ActionEvent actionEvent){
        if(pokemonTrainer.getHp() > 0) {
            pokemonTrainer.attackPokemon(rivalPokemon);
            if(rivalPokemon.getHp() > 0) {
                rivalPokemon.attackPokemon(pokemonTrainer);
                if (pokemonTrainer.getHp() < 0) {
                    flee(actionEvent);
                }
            } else {
                rivalPokemon.setHp(rivalPokemon.getMaxHp());
                Game.currentTrainer.addBox(rivalPokemon);
                flee(actionEvent);
            }
        } else  {
            if(Game.currentTrainer.getTeam().stream().count() > index+1) {
                index++;
            }
            flee(actionEvent);
        }
    }

    private void heal(ActionEvent actionEvent) {
        pokemonTrainer.healPokemon(random.nextInt(151) + 1 + rivalPokemon.getAttack());
    }
}
