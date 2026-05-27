package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.IAttackable;
import dsy.pokemonfinalproyect.shapes.ISpecialAttack;
import dsy.pokemonfinalproyect.shapes.Type;

/**
 * Class to manage the special attack of a Pokémon
 * @author Samuel
 */

public class SpecialAttack extends Attack implements ISpecialAttack, IAttackable {
    /**
     * Constructor with parameters
     * @param name A String with the name of the special attack
     * @param damage An int with the amount of damage
     * @param type A Type with the type of the attack
     */

    public SpecialAttack(String name, int damage, Type type) {
        super(name, damage, type);
    }

    /**
     * Method which will apply the effect of the special attack on the enemy Pokémon
     * @param objectivePokemon A Pokémon who will recieve the damage
     */

    @Override
    public void ApplyEffect(Pokemon objectivePokemon) {

    }
}
