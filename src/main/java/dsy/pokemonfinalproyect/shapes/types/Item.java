package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.IUsable;

/**
 * Class to manage the items you will win defeating the enemy
 * @author Samuel
 */

public class Item implements IUsable {
    private String name;
    private String description;

    /**
     * A method that will be used in the future to make an item usable
     * @param pokemon The Pokémon that will use the item
     */

    @Override
    public void Use(Pokemon pokemon) {

    }
}
