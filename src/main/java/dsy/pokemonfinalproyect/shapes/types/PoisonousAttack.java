package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.Type;

/**
 * Class to manage in the future the special attack of poisoned Pokéon
 */

public class PoisonousAttack extends SpecialAttack {
    /**
     * Constructor with parameters
     * @param name A String with the name of the attack
     * @param damage An int with the damage to the attack
     * @param type A Type with the type of te attack
     */

    public PoisonousAttack(String name, int damage, Type type) {
        super(name, damage, type);
    }
}
