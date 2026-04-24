package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.IAttackable;
import dsy.pokemonfinalproyect.shapes.ISpecialAttack;
import dsy.pokemonfinalproyect.shapes.Type;

public class SpecialAttack extends Attack implements ISpecialAttack, IAttackable {

    public SpecialAttack(String name, int damage, Type type) {
        super(name, damage, type);
    }

    @Override
    public void ApplyEffect(Pokemon objectivePokemon) {

    }
}
