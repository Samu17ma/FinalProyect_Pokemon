package dsy.pokemonfinalproyect;

import dsy.pokemonfinalproyect.shapes.types.Game;
import dsy.pokemonfinalproyect.shapes.types.Pokemon;
import dsy.pokemonfinalproyect.shapes.types.Battle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class CombactController {

    private int index = 0;
    private Pokemon pokemonTrainer = Game.currentTrainer.getTeam().get(index);
    private Random random = new Random();
    private Pokemon rivalPokemon = Game.existingPokemons.get(random.nextInt(Game.existingPokemons.size()));
    private boolean healLast = false;
    private Battle currentBattle;

    @FXML private Text txtTrainerPokemon;
    @FXML private Text txtTrainerHP;
    @FXML private ProgressBar progressTrainerHP;

    @FXML private Text txtEnemyPokemon;
    @FXML private Text txtEnemyHP;
    @FXML private ProgressBar progressEnemyHP;

    @FXML private TextArea txtConsole;
    @FXML private Button btn_attack;
    @FXML private Button btn_heal;
    @FXML private Button btn_flee;

    @FXML
    public void initialize() {
        currentBattle = new Battle(pokemonTrainer, rivalPokemon);
        updateUI();
        txtConsole.appendText("A wild " + rivalPokemon.getName() + " appeared!\n");
    }

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

    @FXML
    private void pokemonAttack(ActionEvent actionEvent) {
        if (pokemonTrainer.getHp() > 0) {
            String combatLog = captureOutput(() -> currentBattle.executeAttack());
            txtConsole.appendText(combatLog);

            updateUI();
            checkBattleEnd();
        } else {
            if (Game.currentTrainer.getTeam().size() > index + 1) {
                index++;
                pokemonTrainer = Game.currentTrainer.getTeam().get(index);
                currentBattle = new Battle(pokemonTrainer, rivalPokemon);
                updateUI();
                txtConsole.appendText("Go, " + pokemonTrainer.getName() + "!\n");
            } else {
                txtConsole.appendText("You have no more usable Pokémon! You lost.\n");
                disableControls();
            }
        }
        healLast = true;
    }

    @FXML
    private void heal(ActionEvent actionEvent) {
        if (healLast) {
            int healAmount = random.nextInt(51) + 1;
            pokemonTrainer.healPokemon(healAmount);
            txtConsole.appendText(pokemonTrainer.getName() + " recovered health!\n");

            if (rivalPokemon.getHp() > 0) {
                int damageToTrainer = rivalPokemon.getAttack() - (pokemonTrainer.getDefense() / 2);
                damageToTrainer = Math.max(1, damageToTrainer);
                pokemonTrainer.setHp(pokemonTrainer.getHp() - damageToTrainer);
                txtConsole.appendText("Enemy attacks! It takes from you " + damageToTrainer + " HP.\n");
            }

            healLast = false;
            updateUI();
            checkBattleEnd();
        } else {
            txtConsole.appendText("You cannot use heal consecutively!\n");
        }
    }

    private void checkBattleEnd() {
        if (rivalPokemon.getHp() <= 0) {
            txtConsole.appendText("\nYou defeated " + rivalPokemon.getName() + "!\n");
            Game.saveCurrentTrainerState();
            disableControls();
        } else if (pokemonTrainer.getHp() <= 0) {
            txtConsole.appendText("\n" + pokemonTrainer.getName() + " fainted!\n");
            if (Game.currentTrainer.getTeam().size() <= index + 1) {
                disableControls();
            }
        }
    }

    private void updateUI() {
        txtTrainerPokemon.setText(pokemonTrainer.getName() + " (Lv. " + pokemonTrainer.getLevel() + ")");
        txtTrainerHP.setText("HP: " + Math.max(0, pokemonTrainer.getHp()) + " / " + pokemonTrainer.getMaxHp());
        double trainerProgress = (double) pokemonTrainer.getHp() / pokemonTrainer.getMaxHp();
        progressTrainerHP.setProgress(Math.max(0, trainerProgress));

        txtEnemyPokemon.setText(rivalPokemon.getName() + " (Lv. " + rivalPokemon.getLevel() + ")");
        txtEnemyHP.setText("HP: " + Math.max(0, rivalPokemon.getHp()) + " / " + rivalPokemon.getMaxHp());
        double enemyProgress = (double) rivalPokemon.getHp() / rivalPokemon.getMaxHp();
        progressEnemyHP.setProgress(Math.max(0, enemyProgress));
    }

    private void disableControls() {
        btn_attack.setDisable(true);
        btn_heal.setDisable(true);
    }

    private String captureOutput(Runnable action) {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        action.run();

        System.setOut(oldOut);
        return baos.toString();
    }
}