// --== CS400 File Header Information ==--
// Name: Patrick Nowakowski
// Email: pnowakowski@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.util.List;

/**
 * Defines the stats fields and comparing functionality of a Pokemon
 * @author pnowa
 */
public class Pokemon implements PokemonInterface {
  
  private String name;
  private int number;
  private List<String> types;
  private int totalCombatPower;
  private int hp;
  private int attack;
  private int defense;
  private int speed;
  private int generation;
  private boolean isLegendary;
  
  /**
   * Field initializing constructor
   */
  public Pokemon(String name, int number, List<String> types, int totCP, int hp, int atk, int def, int spd, int gen, boolean legendary) {
    this.name = name;
    this.number = number;
    this.types = types;
    this.totalCombatPower = totCP;
    this.hp = hp;
    this.attack = atk;
    this.defense = def;
    this.speed = spd;
    this.generation = gen;
    this.isLegendary = legendary;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getNumber() {
    return number;
  }

  @Override
  public List<String> getTypes() {
    return types;
  }

  @Override
  public int getTotalCombatPower() {
    return totalCombatPower;
  }

  @Override
  public int getHP() {
    return hp;
  }

  @Override
  public int getAttack() {
    return attack;
  }

  @Override
  public int getDefense() {
    return defense;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public int getGeneration() {
    return generation;
  }

  @Override
  public boolean getIsLegendary() {
    return isLegendary;
  }

  /**
   * Compares this Pokemon object to another Pokemon based on their
   * totalCombatPower fields. Returns an integer difference.
   * @param otherPokemon other Pokemon to compare against
   * @return the difference between this.totalCombatPower and otherPokemon.totalCombatPower
   * If return value is positive, this Pokemon has a higher stat value
   * If return value is negative, this Pokemon has a lower stat value
   * If return value is zero, the two Pokemon have equal stat values
   */
  public int compareTo(PokemonInterface otherPokemon) {
    return (this.totalCombatPower - otherPokemon.getTotalCombatPower());
  }
  
  /**
   * Compares this Pokemon object to another Pokemon based on their
   * number fields. Returns an integer difference.
   * @param otherPokemon other Pokemon to compare against
   * @return the difference between this.number and otherPokemon.number
   * If return value is positive, this Pokemon has a higher number
   * If return value is negative, this Pokemon has a lower number
   * If return value is zero, the two Pokemon are the same Pokemon (the same Pokedex number)
   */
  public int compareNum(Pokemon otherPokemon) {
    return (this.number - otherPokemon.getNumber());
  }

  /**
   * Compares this Pokemon object to another Pokemon based on their
   * hp fields. Returns an integer difference.
   * @param otherPokemon other Pokemon to compare against
   * @return the difference between this.hp and otherPokemon.hp
   * If return value is positive, this Pokemon has a higher stat value
   * If return value is negative, this Pokemon has a lower stat value
   * If return value is zero, the two Pokemon have equal stat values
   */
  public int compareHP(Pokemon otherPokemon) {
    return (this.hp - otherPokemon.getHP());
  }

  /**
   * Compares this Pokemon object to another Pokemon based on their
   * attack fields. Returns an integer difference.
   * @param otherPokemon other Pokemon to compare against
   * @return the difference between this.attack and otherPokemon.attack
   * If return value is positive, this Pokemon has a higher stat value
   * If return value is negative, this Pokemon has a lower stat value
   * If return value is zero, the two Pokemon have equal stat values
   */
  public int compareAttack(Pokemon otherPokemon) {
    return (this.attack - otherPokemon.getAttack());
  }

  /**
   * Compares this Pokemon object to another Pokemon based on their
   * defense fields. Returns an integer difference.
   * @param otherPokemon other Pokemon to compare against
   * @return the difference between this.defense and otherPokemon.defense
   * If return value is positive, this Pokemon has a higher stat value
   * If return value is negative, this Pokemon has a lower stat value
   * If return value is zero, the two Pokemon have equal stat values
   */
  public int compareDefense(Pokemon otherPokemon) {
    return (this.defense - otherPokemon.getDefense());
  }

  /**
   * Compares this Pokemon object to another Pokemon based on their
   * speed fields. Returns an integer difference.
   * @param otherPokemon other Pokemon to compare against
   * @return the difference between this.speed and otherPokemon.speed
   * If return value is positive, this Pokemon has a higher stat value
   * If return value is negative, this Pokemon has a lower stat value
   * If return value is zero, the two Pokemon have equal stat values
   */
  public int compareSpeed(Pokemon otherPokemon) {
    return (this.speed - otherPokemon.getSpeed());
  }
  
  @Override
  /**
   * Returns a formatted String of this Pokemon's data
   */
  public String toString() {
    String typeString = "";
    
    for(String t: types) {
      typeString += t + " ";
    }
    
    if(isLegendary) {
      return name + " (Legendary) #" + number + "\n" +
          "Type(s): " + typeString + "\n" +
          "Combat Power: " + totalCombatPower + "\n" +
          "HP: " + hp + "\n" +
          "Attack: " + attack + "\n" +
          "Defense: " + defense + "\n" +
          "Speed: " + speed + "\n" +
          "Generation: " + generation;
    }
    else {
      return name + " #" + number + "\n" +
          "Type(s): " + typeString + "\n" +
          "Combat Power: " + totalCombatPower + "\n" +
          "HP: " + hp + "\n" +
          "Attack: " + attack + "\n" +
          "Defense: " + defense + "\n" +
          "Speed: " + speed + "\n" +
          "Generation: " + generation;
    }
  }
  
  @Override
  /**
   * Determines if two Pokemon objects are equal based on their
   * (Pokedex) "number" field
   */
  public boolean equals(Object o) {
    if(this.number == ((Pokemon)o).getNumber()) {
      return true;
    }
    else {
      return false;
    }
  }
}
