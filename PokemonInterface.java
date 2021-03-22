import java.util.List;

// --== CS400 File Header Information ==--
// Name: Patrick Nowakowski
// Email: pnowakowski@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: 

public interface PokemonInterface extends Comparable<PokemonInterface> {

  public String getName();
  public int getNumber();
  public List<String> getTypes();
  public int getTotalCombatPower();
  public int getHP();
  public int getAttack();
  public int getDefense();
  public int getSpeed();
  public int getGeneration();
  public boolean getIsLegendary();

  // from super interface Comparable
  // Compares two Pokemon by TotalCombatPower
  public int compareTo (PokemonInterface otherPokemon);
}
