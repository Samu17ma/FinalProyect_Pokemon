package dsy.pokemonfinalproyect.shapes;

import dsy.pokemonfinalproyect.shapes.types.Pokemon;

public interface IAttackable {
    public void Execute(Pokemon attackingPokemon, Pokemon objectivePokemon);
}
