package dsy.pokemonfinalproyect.shapes.types;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the trainer's information, their Pokémon team, and item inventory
 * @author Samuel
 * @author dantorcom
 * @author yassine
 */

public class Trainer {
    private String name;
    private String password;
    private List<Pokemon> team;
    private List<Item> inventory;
    private List<Pokemon> box;

    /**
     * Constructor with parameters
     * @param name A String with the name of the Trainer
     * @param password A String with the password of the Trainer
     */

    public Trainer(String name, String password) {
        this.name = name;
        this.password = password;
        this.team = new ArrayList<>();
        this.box = new ArrayList<>();
        this.inventory = new ArrayList<>();
    }

    /**
     * Return's the Trainer's password
     * @return Trainer's password
     */

    public String getPassword() {
        return password;
    }

    /**
     * Establishes the Trainer's password
     * @param password Trainer's password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Establishes the Trainer's name
     * @param name Trainer's name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Establishes the Trainer's Pokémon team
     * @param team A List with the Pokémon team
     */

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    /**
     * Establishes the Trainer's item inventory
     * @param inventory A List with the items
     */

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * Return's the Trainer's PC storage box
     * @return A List with the Pokémon in the box
     */

    public List<Pokemon> getBox() {
        return box;
    }

    /**
     * Establishes the Trainer's PC storage box
     * @param box A List with the Pokémon in the box
     */

    public void setBox(List<Pokemon> box) {
        this.box = box;
    }

    /**
     * Adds a Pokémon to the trainer's current team if it has less than 6 members
     * @param pokemon The Pokémon to be added
     */

    public void addPokemon(Pokemon pokemon) {
        if (this.team.size() < 6) {
            this.team.add(pokemon);
        }
    }

    /**
     * Adds a Pokémon directly to the PC storage box
     * @param pokemon The Pokémon to be added
     */

    public void addBox(Pokemon pokemon) {
        this.box.add(pokemon);
    }

    /**
     * Selects and returns a Pokémon from the team based on its list index
     * @param index An int with the position of the Pokémon in the team
     * @return The selected Pokémon, or null if the index is invalid
     */

    public Pokemon choosePokemon(int index) {
        if (index >= 0 && index < team.size()) {
            return team.get(index);
        }
        return null;
    }

    /**
     * Prints the details of all Pokémon currently in the trainer's team
     */

    public void showTeam() {
        for (Pokemon p : team) {
            System.out.println(p.toString());
        }
    }

    /**
     * Adds an item to the trainer's inventory
     * @param item The Item to be added
     */

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    /**
     * Uses an item from the inventory on a specific Pokémon from the team
     * @param itemIndex An int with the position of the item in the inventory
     * @param pokemonIndex An int with the position of the Pokémon in the team
     */

    public void useItem(int itemIndex, int pokemonIndex) {
        if (itemIndex < inventory.size() && pokemonIndex < team.size()) {
            Item item = inventory.get(itemIndex);
            inventory.remove(itemIndex);
        }
    }

    /**
     * Return's the Trainer's name
     * @return Trainer's name
     */

    public String getName() {
        return this.name;
    }

    /**
     * Return's the Trainer's Pokémon team
     * @return A List with the Pokémon team
     */

    public List<Pokemon> getTeam() {
        return team;
    }

    /**
     * Return's the Trainer's item inventory
     * @return A List with the inventory items
     */

    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * Moves a Pokémon from the storage box to the current team if there is space
     * @param p The Pokémon to move to the team
     */

    public void moveToTeam(Pokemon p) {
        if (team.size() < 6 && box.contains(p)) {
            box.remove(p);
            team.add(p);
        }
    }

    /**
     * Moves a Pokémon from the current team back to the storage box
     * @param p The Pokémon to move to the box
     */

    public void moveToBox(Pokemon p) {
        if (team.contains(p)) {
            team.remove(p);
            box.add(p);
        }
    }
}