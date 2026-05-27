package dsy.pokemonfinalproyect.shapes.types;

/**
 * Class to manage the status conditions (altered states) that can affect a Pokémon
 * @author Samuel
 */

public class Status {
    private String name;
    private String description;
    private int duration;

    /**
     * Constructor with parameters
     * @param name A String with the name of the status condition
     * @param description A String describing the effect of the status
     * @param duration An int representing the remaining turns of the status
     */
    public Status(String name, String description, int duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    /**
     * Return's the status condition's name
     * @return Status condition's name
     */
    public String getName() {
        return name;
    }

    /**
     * Establishes the status condition's name
     * @param name Status condition's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return's the description of the status effect
     * @return Status condition's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establishes the description of the status effect
     * @param description Status condition's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return's the remaining duration of the status condition
     * @return An int with the number of turns
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Establishes the duration of the status condition
     * @param duration An int with the number of turns
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Applies the specific effect of the status condition to a Pokémon
     * @param pokemon The Pokémon affected by this status
     */
    public void applyEffect(Pokemon pokemon) {

    }
}
