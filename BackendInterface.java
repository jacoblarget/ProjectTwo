// --== CS400 File Header Information ==--
// Name: Vatsal Patel
// Email: vpatel43@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Gary Dahl
// Notes to Grader: 

import java.util.List;
import java.util.NoSuchElementException;

public interface BackendInterface {

    public List<String> getAllTypes(); // Returns a list of Strings that contains the name of every type
    public void addType(String type); // Selects a type
    public void removeType(String type); // Deselects a type
    public void filterPower(int minimum); // Sets a filter such that only Pokemon with a 
        // combat power >= minimum are selected
    public List<String> getTypes(); // Returns a list of the currently selected types
    public List<PokemonInterface> getFilteredPokemon(); // Returns a list of Pokemon that fit the type
        // and combat power filters
    public Pokemon getByName(String name) throws NoSuchElementException; // Returns a Pokemon if found
        // or throws Exception if there is no Pokemon. Name must match exactly, except for preceding/succeeding
        // whitespace and capitalization
}


