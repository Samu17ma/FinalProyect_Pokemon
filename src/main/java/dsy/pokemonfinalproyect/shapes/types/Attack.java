package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.IAttackable;
import dsy.pokemonfinalproyect.shapes.Type;

public class Attack implements IAttackable {
    private String name;
    private int damage;
    private Type type;

    public Attack(String name, int damage, Type type) {
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    @Override
    public void Execute(Pokemon attackingPokemon, Pokemon objectivePokemon) {

    }
}
