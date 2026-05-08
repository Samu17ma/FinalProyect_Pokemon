package dsy.pokemonfinalproyect.shapes.types;

import java.util.ArrayList; // Necesario para inicializar las listas
import java.util.List;

public class Trainer {
    private String name;
    private List<Pokemon> team;
    private List<Item> inventory;

    // Constructor: Obligatorio para crear al entrenador desde el Sign Up
    public Trainer(String name) {
        this.name = name; // Línea importante
        this.team = new ArrayList<>();
        this.inventory = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        if (this.team.size() < 6) { // Límite estándar de Pokémon
            this.team.add(pokemon);
        }
    }

    public Pokemon choosePokemon(int index) {
        if (index >= 0 && index < team.size()) {
            return team.get(index);
        }
        return null;
    }

    public void showTeam() {
        for (Pokemon p : team) {
            System.out.println(p.toString()); // Asumiendo que Pokemon tiene toString[cite: 1]
        }
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void useItem(int itemIndex, int pokemonIndex) {
        if (itemIndex < inventory.size() && pokemonIndex < team.size()) {
            Item item = inventory.get(itemIndex);
            // Lógica para aplicar el item al pokemon[cite: 1]
            inventory.remove(itemIndex); // Se consume el objeto
        }
    }

    // Getters: Necesarios para que la interfaz JavaFX pueda leer los datos[cite: 1]
    public String getName() { return name; }
    public List<Pokemon> getTeam() { return team; }
    public List<Item> getInventory() { return inventory; }
}