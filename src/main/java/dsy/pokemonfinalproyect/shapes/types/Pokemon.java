package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.Type;

import java.util.List;

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

    public Pokemon(String name, Type type, int level, int hp, int maxHp, int attack, int defense, int experience, List<Attack> attacks, List<Status> statuses) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.experience = experience;
        this.attacks = attacks;
        this.statuses = statuses;
    }
}
