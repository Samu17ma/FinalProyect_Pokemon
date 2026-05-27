package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the information and usability of the Pokémon
 * @author Samuel
 * @author dantorcom
 */

public class Pokemon {
    private String name;
    private Type type;
    private int level;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int experience;
    private List<Attack> attacks;
    private List<Status> statuses;

    /**
     * Constructor with parameters
     * @param name A String with the name of the Pokémon
     * @param type A Type with the type of the Pokémon
     * @param level An int with the level of the Pokémon
     * @param hp An int with the current HP level of the Pokémon
     * @param maxHp An int with the max HP level of the Pokémon
     * @param attack An int with the damage number of the Pokémon
     * @param defense An int with the defense of the Pokémon
     * @param experience An int with he experience of the Pokémon
     */

    public Pokemon(String name, Type type, int level, int hp, int maxHp, int attack, int defense, int experience) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.experience = experience;
        attacks = new ArrayList<>();
        statuses = new ArrayList<>();
    }

    /**
     * Return's the Pokémon's name
     * @return Pokémon's name
     */

    public String getName() {
        return name;
    }

    /**
     * Establishes the Pokémon's name
     * @param name Pokémon's name
     */

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    /**
     * Establishes the Pokémon's type
     * @param type Pokémon's type
     */

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Return's the Pokémon's level
     * @return Pokémon's level
     */

    public int getLevel() {
        return level;
    }

    /**
     * Establishes the Pokémon's level
     * @param level Pokémon's level
     */

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Return's the Pokémon's current HP level
     * @return Pokémon's current HP level
     */

    public int getHp() {
        return hp;
    }

    /**
     * Establishes the Pokémon's current HP level
     * @param hp Pokémon's current HP level
     */

    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Return's the Pokémon's max HP level
     * @return Pokémon's max HP level
     */

    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Establishes the Pokémon's max HP level
     * @param maxHp Pokémon's max HP level
     */

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Return's the Pokémon's attack damage number
     * @return Pokémon's attack damage number
     */

    public int getAttack() {
        return attack;
    }

    /**
     * Establishes the Pokémon's attack damage number
     * @param attack Pokémon's attack damage number
     */

    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Return's the Pokémon's defense
     * @return Pokémon's defense
     */

    public int getDefense() {
        return defense;
    }

    /**
     * Establishes the Pokémon's defense
     * @param defense Pokémon's defense
     */

    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Return's the Pokémon's experience
     * @return Pokémon's experience
     */

    public int getExperience() {
        return experience;
    }

    /**
     * Establishes the Pokémon's experience
     * @param experience Pokémon's experience
     */

    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Return's the Pokémon's list of attacks
     * @return A List with the Pokémon's attacks
     */

    public List<Attack> getAttacks() {
        return attacks;
    }

    /**
     * Establishes the Pokémon's list of attacks
     * @param attacks A List with the Pokémon's attacks
     */

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    /**
     * Return's the Pokémon's list of statuses
     * @return A List with the Pokémon's statuses
     */

    public List<Status> getStatuses() {
        return statuses;
    }

    /**
     * Establishes the Pokémon's list of statuses
     * @param statuses A List with the Pokémon's statuses
     */

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    /**
     * Attacks another Pokémon and reduces its HP based on attack and defense stats
     * @param objective The Pokémon that will receive the attack
     */

    public void attackPokemon(Pokemon objective) {
        objective.setHp(objective.getHp()-(attack*level)-(objective.getDefense()*objective.getLevel())/2);
    }

    /**
     * Heals the Pokémon's HP up to its maximum capacity
     * @param cant An int with the base amount of healing points
     */

    public void healPokemon(int cant) {
        hp = hp+cant+level;

        if(hp>maxHp) {
            hp=maxHp;
        }
    }
}
