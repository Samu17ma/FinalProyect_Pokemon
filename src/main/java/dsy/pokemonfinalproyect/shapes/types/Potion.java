package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.IHealable;

/**
 * Class child from Item.java who can heal
 */

public class Potion extends Item implements IHealable {
    private int healAmount;

    /**
     * Method to start the use of a potion, in the future, if the poison can heal
     * it will call the Heal function.
     * @param pokemon A Pokémon who will recieve the posion
     */

    @Override
    public void Use(Pokemon pokemon) {

    }

    /**
     * Method to use the chosen poison to one Pokémon
     * @param pokemon A Pokémon who will recieve the posion
     */

    @Override
    public void Heal(Pokemon pokemon) {

    }
}
