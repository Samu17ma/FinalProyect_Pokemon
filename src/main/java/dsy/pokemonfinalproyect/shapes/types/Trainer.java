package dsy.pokemonfinalproyect.shapes.types;

import java.util.ArrayList; // Necesario para inicializar las listas
import java.util.List;

public class Trainer {
    private String name;
    private List<Pokemon> team;
    private List<Item> inventory;
    private List<Pokemon> box;

    public Trainer(String name) {
        this.name = name;
        this.team = new ArrayList<>();
        this.box = new ArrayList<>();
        this.inventory = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public List<Pokemon> getBox() {
        return box;
    }

    public void setBox(List<Pokemon> box) {
        this.box = box;
    }

    public void addPokemon(Pokemon pokemon) {
        if (this.team.size() < 6) {
            this.team.add(pokemon);
        }
    }

    public void addBox(Pokemon pokemon) {
        this.box.add(pokemon);
    }

    public Pokemon choosePokemon(int index) {
        if (index >= 0 && index < team.size()) {
            return team.get(index);
        }
        return null;
    }

    public void showTeam() {
        for (Pokemon p : team) {
            System.out.println(p.toString());
        }
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void useItem(int itemIndex, int pokemonIndex) {
        if (itemIndex < inventory.size() && pokemonIndex < team.size()) {
            Item item = inventory.get(itemIndex);

            inventory.remove(itemIndex);
        }
    }

    public String getName() { return this.name; }
    public List<Pokemon> getTeam() { return team; }
    public List<Item> getInventory() { return inventory; }

    public void moveToTeam(Pokemon p) {
        if (team.size() < 6 && box.contains(p)) {
            box.remove(p);
            team.add(p);
        }
    }

    public void moveToBox(Pokemon p) {
        if (team.contains(p)) {
            team.remove(p);
            box.add(p);
        }
    }
}