package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    final String pokemonsFile = "pokemons.txt";
    static final String usersFile = "users.txt";

    private Trainer trainer;
    private Scanner sc = new Scanner(System.in);
    private static List<Pokemon> pokemons;
    public static List<Trainer> users = new ArrayList<>();
    public static List<Pokemon> existingPokemons = new ArrayList<>();

    public void start() {

    }

    public void processOption(int option) {

    }

    public void saveGame() {}
    public static void loadGame() {
        existingPokemons = loadPokemonsFromFile();
    }

    public static List<Pokemon> loadPokemonsFromFile() {
        List<Pokemon> pokemonList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pokemons.txt"))) {
            String line;
            br.readLine(); // Saltar la cabecera (Number, Name...)

            while ((line = br.readLine()) != null) {
                // Usamos una expresión regular para que no separe las comas dentro de comillas ""
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (data.length >= 11) {
                    try {
                        String name = data[1].trim();

                        // LIMPIEZA DEL TIPO: de "['grass', 'poison']" a "GRASS"
                        String typeRaw = data[3].replace("[", "").replace("]", "").replace("'", "").replace("\"", "").trim();
                        String firstType = typeRaw.split(",")[0].toUpperCase(); // Tomamos solo el primer tipo

                        Type type = Type.valueOf(firstType);

                        int hp = Integer.parseInt(data[8].trim());
                        int attack = Integer.parseInt(data[9].trim());
                        int defense = Integer.parseInt(data[10].trim());

                        Random rand = new Random();
                        int level = rand.nextInt(5) + 1;

                        pokemonList.add(new Pokemon(name, type, level, hp, hp, attack, defense, 0));
                    } catch (Exception e) {
                        // Si un pokemon falla (como Mr. Mime), saltamos al siguiente
                        continue;
                    }
                }
            }
            System.out.println("Total cargados: " + pokemonList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemonList;
    }

    public static boolean validateLogin(String username, String password) {
        File file = new File(usersFile);

        boolean result = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fullLine = line.split(",");

                if (fullLine.length >= 2) {
                    if (fullLine[0].equals(username) && fullLine[1].equals(password)) {
                        result = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File was not found: " + usersFile);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading file.");
            e.printStackTrace();
        }

        return result;
    }
    public static List<Trainer> user = new ArrayList<>();

    public static void registerUserToFile(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", true))) {
            writer.write(username + ":" + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
