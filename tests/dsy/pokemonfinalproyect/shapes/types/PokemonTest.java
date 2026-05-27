package dsy.pokemonfinalproyect.shapes.types;

import dsy.pokemonfinalproyect.shapes.Type;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {

    Pokemon pokemon;

    @BeforeEach
    void setUp() {
        pokemon = new Pokemon("Testing pokemon", Type.FIGHTING, 3, 25, 25, 2, 0, 0);
    }

    @Test
    void getName() {
        assertNotNull(pokemon.getName());
        assertEquals("Testing pokemon", pokemon.getName());

        // Watching if cocntrstructor works good
        Pokemon anotherPokemon = new Pokemon("Another pokemon", Type.FIGHTING, 3, 25, 25, 2, 0, 0);
        assertEquals("Another pokemon", anotherPokemon.getName());
    }

    @Test
    void setName() {
        pokemon.setName("Testing pokemon");
        assertEquals("Testing pokemon", pokemon.getName());
    }

    @Test
    void getType() {
        assertNotNull(pokemon.getType());
        assertEquals(Type.FIGHTING, pokemon.getType());
    }

    @Test
    void setType() {
        pokemon.setType(Type.FIGHTING);
        assertEquals(Type.FIGHTING, pokemon.getType());
    }

    @Test
    void getLevel() {
        assertNotNull(pokemon.getLevel());
        assertEquals(3, pokemon.getLevel());
    }

    @Test
    void setLevel() {
        pokemon.setLevel(25);
        assertEquals(25, pokemon.getLevel());
    }

    @Test
    void getHp() {
        assertNotNull(pokemon.getHp());
        assertEquals(25, pokemon.getHp());
    }

    @Test
    void setHp() {
        pokemon.setHp(25);
        assertEquals(25, pokemon.getHp());
    }

    @Test
    void getMaxHp() {
        assertNotNull(pokemon.getMaxHp());
        assertEquals(25, pokemon.getMaxHp());
    }

    @Test
    void setMaxHp() {
        pokemon.setMaxHp(25);
        assertEquals(25, pokemon.getMaxHp());
    }

    @Test
    void getAttack() {
        assertNotNull(pokemon.getAttack());
        assertEquals(2, pokemon.getAttack());
    }

    @Test
    void setAttack() {
        pokemon.setAttack(2);
        assertEquals(2, pokemon.getAttack());
    }

    @Test
    void getDefense() {
        assertNotNull(pokemon.getDefense());
        assertEquals(0, pokemon.getDefense());
    }

    @Test
    void setDefense() {
        pokemon.setDefense(2);
        assertEquals(2, pokemon.getDefense());
    }

    @Test
    void getExperience() {
        assertNotNull(pokemon.getExperience());
        assertEquals(0, pokemon.getExperience());
    }

    @Test
    void setExperience() {
        pokemon.setExperience(2);
        assertEquals(2, pokemon.getExperience());
    }

    @Test
    void healPokemon() {
        pokemon.setHp(20);
        pokemon.setMaxHp(25);

        pokemon.healPokemon(5);
        assertEquals(25, pokemon.getHp());
    }
}