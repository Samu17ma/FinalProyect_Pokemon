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
    private List<Pokemon> pokemons;

    public void start() {

    }

    public void processOption(int option) {

    }

    public void saveGame() {}

    public void loadGame() {
        pokemons = loadPokemons();
    }

    private List<Pokemon> loadPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pokemonsFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fullLine = line.split(",");

                if (!fullLine[0].equals("Number")) {
                    String name =  fullLine[1];
                    String typeText =  fullLine[3];

                    Type type = Type.valueOf(typeText);

                    int HP = Integer.parseInt(fullLine[8]);
                    int attack  = Integer.parseInt(fullLine[9]);
                    int defense = Integer.parseInt(fullLine[10]);

                    Random rand = new Random();
                    int level = rand.nextInt(5);
                    Pokemon p = new Pokemon(name, type, level, HP, HP, attack, defense, 0);

                    pokemons.add(p);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pokemons;
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
}
