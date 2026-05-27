package dsy.pokemonfinalproyect.shapes.types;

import java.util.List;
import java.util.Random;

/**
 * Class to make works the battle logic
 * @author dantorcom
 */

public class Battle {
    private Pokemon playerActive;
    private Pokemon enemy;
    private boolean battleOver = false;

    /**
     * Constructor with parameters
     * @param playerActive A Pokémon who represent the Pokémon of the actual trainer
     * @param enemy A Pokémon who represent the enemy Pokémon
     */

    public Battle(Pokemon playerActive, Pokemon enemy) {
        this.playerActive = playerActive;
        this.enemy = enemy;
    }

    /**
     * Method that starts the attack situation and takes control of it
     */

    public void executeAttack() {
        if (!battleOver) {
            int damageToEnemy = calculateDamage(playerActive, enemy);
            enemy.setHp(enemy.getHp() - damageToEnemy);
            System.out.println("¡" + playerActive.getName() + " makes " + damageToEnemy + " of damage!");

            if (enemy.getHp() <= 0) {
                System.out.println("The enemy has been weakened!");
                processVictory();
                battleOver = true;
            } else {
                enemyTurn();
            }
        }
    }

    /**
     * Methot that execute the logic of the enemy turn.
     */

    private void enemyTurn() {
        int damageToPlayer = calculateDamage(enemy, playerActive);
        playerActive.setHp(playerActive.getHp() - damageToPlayer);
        System.out.println("Enemy attacks! It takes from you " + damageToPlayer + " HP.");

        if (playerActive.getHp() <= 0) {
            System.out.println("Your Pokemon has been weakened...!");
            battleOver = true;
        }
    }

    /**
     * Methot that calculates if the trainer can escape of the combat
     * @return A Boolean with the result of whether the coach can escape or not
     */

    public boolean attemptEscape() {
        Random rand = new Random();

        boolean result = false;

        if (rand.nextBoolean()) {
            System.out.println("You escaped safely!");
            battleOver = true;
            result = true;
        }
        System.out.println("You couldn't escape!");
        enemyTurn();

        return result;
    }

    /**
     * Methot that calculates the damage the attacker Pokémon will make
     * @param attacker A Pokémon pokémon who will attack
     * @param defender A Pokémon pokémon who will recieve the damage
     * @return An int with the damage the attacker will do
     */

    private int calculateDamage(Pokemon attacker, Pokemon defender) {
        int dmg = (attacker.getAttack()*attacker.getLevel()) / defender.getDefense();
        return Math.max(1, dmg);
    }

    /**
     * Method that process the logic of the victory
     */

    private void processVictory() {
        if (Game.currentTrainer.getTeam().size() < 6) {
            Game.currentTrainer.addPokemon(enemy);
            System.out.println(enemy.getName() + " join to yur team.");
        } else {
            Game.currentTrainer.addBox(enemy);
            System.out.println("Team full. " + enemy.getName() + " send to Box.");
        }
    }
}
