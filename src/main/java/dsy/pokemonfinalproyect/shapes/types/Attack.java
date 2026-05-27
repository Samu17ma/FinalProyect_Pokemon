package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.IAttackable;
import dsy.pokemonfinalproyect.shapes.Type;

/**
 * Class to define types and information of attacks
 * @author dantorcom
 */

public class Attack implements IAttackable {
    private String name;
    private int damage;
    private Type type;

    /**
     * Constructor with parameters
     * @param name
     * @param damage
     * @param type
     */

    public Attack(String name, int damage, Type type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    /**
     * Return's the attack's name
     * @return Attack's name
     */

    public String getName() {
        return name;
    }

    /**
     * Establishes the attack's name
     * @param name Attack's name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return's the attack's damage
     * @return Attack's damage
     */

    public int getDamage() {
        return damage;
    }

    /**
     * Establishes the attack's damage
     * @param damage Attack's damage
     */

    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Return's the attack's type
     * @return Attack's type
     */

    public Type getType() {
        return type;
    }

    /**
     * Establishes the attack's type
     * @param type Attack's type
     */

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Method that execute the attacks using all types, that is why we
     * are not using it at Battle Class.
     * @param attackingPokemon Chosen attack of the Pokémon
     * @param objectivePokemon The Pokémon who will recieve the attack
     */

    @Override
    public void Execute(Pokemon attackingPokemon, Pokemon objectivePokemon) {

    }
}
