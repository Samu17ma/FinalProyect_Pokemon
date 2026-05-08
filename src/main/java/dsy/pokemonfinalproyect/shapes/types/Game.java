package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static final String usersFile = "users.txt";
    public static List<Trainer> users = new ArrayList<>();
    public static List<Pokemon> existingPokemons = new ArrayList<>();

    public static Trainer currentTrainer;

    public static void loadGame() {
        existingPokemons = loadPokemonsFromFile();
    }

    public static List<Pokemon> loadPokemonsFromFile() {
        List<Pokemon> pokemonList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pokemons.txt"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length >= 11) {
                    try {
                        String name = data[1].trim();
                        String typeRaw = data[3].replace("[", "").replace("]", "").replace("'", "").replace("\"", "").trim();
                        String firstType = typeRaw.split(",")[0].toUpperCase();
                        Type type = Type.valueOf(firstType);
                        int hp = Integer.parseInt(data[8].trim());
                        int attack = Integer.parseInt(data[9].trim());
                        int defense = Integer.parseInt(data[10].trim());
                        Random rand = new Random();
                        int level = rand.nextInt(5) + 1;
                        pokemonList.add(new Pokemon(name, type, level, hp, hp, attack, defense, 0));
                    } catch (Exception e) { continue; }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
        return pokemonList;
    }

    public static boolean validateLogin(String username, String password) {
        File file = new File(usersFile);
        if (!file.exists()) return false;

        boolean result = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].equals(username) && data[1].equals(password)) {
                    currentTrainer = new Trainer(username);

                    for (int i = 2; i < data.length; i++) {
                        String pName = data[i];
                        for (Pokemon p : existingPokemons) {
                            if (p.getName().equalsIgnoreCase(pName)) {
                                currentTrainer.addPokemon(p);
                            }
                        }
                    }
                    result = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void registerUserToFile(String username, String password, String firstPokemon) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(usersFile, true))) {
            writer.write(username + "," + password + "," + firstPokemon);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCurrentTrainerState(String password) {
        if (currentTrainer == null) return;

        List<String> allUsers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(currentTrainer.getName() + ",")) {
                    allUsers.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(usersFile))) {
            for (String u : allUsers) { bw.write(u); bw.newLine(); }

            StringBuilder sb = new StringBuilder();
            sb.append(currentTrainer.getName()).append(",").append(password);
            for (Pokemon p : currentTrainer.getTeam()) {
                sb.append(",").append(p.getName());
            }
            bw.write(sb.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
