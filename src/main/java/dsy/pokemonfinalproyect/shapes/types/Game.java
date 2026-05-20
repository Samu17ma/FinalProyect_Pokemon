package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static final String usersFile = "users.txt";
    static final String usersBoxes = "box.txt";
    public static List<Trainer> users = new ArrayList<>();
    public static List<Pokemon> existingPokemons = new ArrayList<>();

    public static Trainer currentTrainer;

    public static void loadGame() {
        existingPokemons = loadPokemonsFromFile();
    }

    public static List<Pokemon> loadPokemonsFromFile() {
        List<Pokemon> pokemonList = new ArrayList<>();
        existingPokemons.clear();

        try (BufferedReader br = new BufferedReader(new FileReader("pokemons.txt"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
                    currentTrainer = new Trainer(username, password);

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

        try (BufferedReader br = new BufferedReader(new FileReader(new File(usersBoxes)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].equals(username) && data[1].equals(password)) {

                    for (int i = 2; i < data.length; i++) {
                        String pName = data[i];
                        for (Pokemon p : existingPokemons) {
                            if (p.getName().equalsIgnoreCase(pName)) {
                                currentTrainer.addBox(p);
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
        File file = new File(usersFile);
        String finalUsername = username;

        if (file.exists()) {
            List<String> existingUsers = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length > 0) {
                        existingUsers.add(data[0]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (existingUsers.contains(finalUsername)) {
                int count = 1;
                while (existingUsers.contains(username + count)) {
                    count++;
                }
                finalUsername = username + count;
            }
        }

        try (BufferedWriter userWriter = new BufferedWriter(new FileWriter(usersFile, true));
             BufferedWriter boxWriter = new BufferedWriter(new FileWriter(usersBoxes, true))) {

            userWriter.write(finalUsername + "," + password + "," + firstPokemon);
            userWriter.newLine();

            boxWriter.write(finalUsername + "," + password + "," + "Caterpie");
            boxWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCurrentTrainerState() {
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

        List<String> allBoxes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(usersBoxes))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(currentTrainer.getName() + ",")) {
                    allBoxes.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(usersFile))) {
            for (String u : allUsers) {
                bw.write(u);
                bw.newLine();
            }

            StringBuilder sb = new StringBuilder();
            sb.append(currentTrainer.getName()).append(",").append(currentTrainer.getPassword());
            for (Pokemon p : currentTrainer.getTeam()) {
                sb.append(",").append(p.getName());
            }
            bw.write(sb.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(usersBoxes))) {
            for (String u : allBoxes) {
                bw.write(u);
                bw.newLine();
            }

            StringBuilder sb = new StringBuilder();
            sb.append(currentTrainer.getName()).append(",").append(currentTrainer.getPassword());
            for (Pokemon p : currentTrainer.getBox()) {
                sb.append(",").append(p.getName());
            }
            bw.write(sb.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pokemon generateWildPokemon() {
        Random rand = new Random();
        Pokemon template = existingPokemons.get(rand.nextInt(existingPokemons.size()));

        return new Pokemon(
                template.getName(), template.getType(), 5,
                template.getHp(), template.getMaxHp(),
                template.getAttack(), template.getDefense(), 0
        );
    }
}
