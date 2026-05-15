package dsy.pokemonfinalproyect.shapes.types;

import java.util.List;
import java.util.Random;

public class Battle {
    private Pokemon playerActive;
    private Pokemon enemy;
    private boolean battleOver = false;

    public Battle(Pokemon playerActive, Pokemon enemy) {
        this.playerActive = playerActive;
        this.enemy = enemy;
    }

    public void executeAttack() {
        if (!battleOver) {
            int damageToEnemy = calculateDamage(playerActive, enemy);
            enemy.setHp(enemy.getHp() - damageToEnemy);
            System.out.println("¡" + playerActive.getName() + " makes " + damageToEnemy + " of damage!");

            if (enemy.getHp() <= 0) {
                System.out.println("¡The enemy has been weakened!");
                processVictory();
                battleOver = true;
            } else {
                enemyTurn();
            }
        }
    }

    private void enemyTurn() {
        int damageToPlayer = calculateDamage(enemy, playerActive);
        playerActive.setHp(playerActive.getHp() - damageToPlayer);
        System.out.println("¡Enemy attacks! It takes from you " + damageToPlayer + " HP.");

        if (playerActive.getHp() <= 0) {
            System.out.println("Your Pokemon has been weakened...!");
            battleOver = true;
        }
    }

    public boolean attemptEscape() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            System.out.println("¡You escaped safely!");
            battleOver = true;
            return true;
        }
        System.out.println("¡You couldn't escape!");
        enemyTurn();
        return false;
    }

    private int calculateDamage(Pokemon attacker, Pokemon defender) {
        int dmg = attacker.getAttack() - (defender.getDefense() / 2);
        return Math.max(1, dmg);
    }

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
