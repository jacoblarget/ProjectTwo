// --== CS400 File Header Information ==--
// Name: Vatsal Patel
// Email: vpatel43@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Gary Dahl
// Notes to Grader: 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Backend implements BackendInterface {
    private RedBlackTree<PokemonInterface> pokemonTree; // The Tree of all the Pokemon
    private ArrayList<PokemonInterface> filteredPokemon; // The List of Pokemon that fits the specified filters
    private String[] types = {"Normal", "Fire", "Water", "Grass", "Electric", "Ice", 
        "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", 
        "Ghost", "Dark", "Dragon", "Steel", "Fairy"};
    private ArrayList<String> activeTypes; // Filter for types
    private int combatPowerFilter; // Filter for combat power
    
    /**
     * Default Constructor that accesses the Pokemon csv file
     */
    public Backend () {
        // Declare a FileReader to read in the data from the file
        FileReader fileReader;
        try {
            // Initialize fileReader using updatedPokemon.csv
            fileReader = new FileReader("updatedPokemon.csv");
            
            // Create a PokemonDataReader to read the Pokemon data
            PokemonDataReader pdr = new PokemonDataReader();

            try{
                // Try to read the data set using the PokemonDataReader
                List<PokemonInterface> pokemonList = pdr.readDataSet(fileReader);
                // Use the data to populate the RedBlackTree
                initializeTreeSelection(pokemonList);
            } catch (IOException ioe) {
                System.out.println("IO Exception Thrown in Constructor:");
                ioe.printStackTrace();
            } catch (DataFormatException dfe) {
                System.out.println("Data Format Exception Thrown in Constructor:");
                dfe.printStackTrace();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("updatedPokemon.csv File Not Found:");
        }
    }

    /**
     * Constructor that takes in a Reader object to populate the Pokemon tree
     * 
     * @param pokemonReader A Reader that contains the Pokemon data in a CSV 
     * format
     */
    public Backend (Reader pokemonReader) {
        // Create a PokemonDataReader to read the Pokemon data
        PokemonDataReader pdr = new PokemonDataReader();

        try{
            // Try to read the data set using the PokemonDataReader
            List<PokemonInterface> pokemonList = pdr.readDataSet(pokemonReader);
            // Use the data to populate the RedBlackTree
            initializeTreeSelection(pokemonList);
        } catch (IOException ioe) {
            System.out.println("IO Exception Thrown in Constructor:");
            ioe.printStackTrace();
        } catch (DataFormatException dfe) {
            System.out.println("Data Format Exception Thrown in Constructor:");
            dfe.printStackTrace();
        }        
    }

    /**
     * Helper method called by the constructor that populates the RedBlackTree
     * with Pokemon and initializes the type/combat power filters
     * 
     * @param pokemonList The List of Pokemon generated in the constructor
     */
    private void initializeTreeSelection(List<PokemonInterface> pokemonList) {
        //Initialize instance variables
        this.pokemonTree = new RedBlackTree<PokemonInterface>();
        this.filteredPokemon = new ArrayList<PokemonInterface>();

        // Initialize the active types to an empty ArrayList (no types selected)
        this.activeTypes = new ArrayList<String>();
        
        // Initialize the minimum combat power filter to 0 (all Pokemon eligible)
        this.combatPowerFilter = 0; 
        
        for(PokemonInterface pokemon : pokemonList) {
            this.pokemonTree.insert(pokemon); // add each Pokemon to the RBT
        }
    }

    /**
     * A helper method that updates the filteredPokemon List based on the 
     * activeTypes list and the combatPowerFilter
     */
    private void updateFilteredPokemon() {
        // clear list of Pokemon
        this.filteredPokemon.clear();

        // Iterate through all the Pokemon in the Tree
        Iterator<PokemonInterface> iter = this.pokemonTree.iterator();
        
        while(iter.hasNext()) {
            PokemonInterface next = iter.next();
            
            // If the next Pokemon fits the filter criteria, add it to the List
            boolean typesMatch = checkTypes(next.getTypes());
            if(typesMatch && next.getTotalCombatPower() >= this.combatPowerFilter) {
                this.filteredPokemon.add(next);
            }
        }
    }

    /**
     * Helper method that determines whether the provided List of types contains
     * the current selected types
     * 
     * @param types The List of types being checked
     * @return True if the List of types fits the list of selected types,
     * false otherwise
     */
    private boolean checkTypes(List<String> types) {
        for(int i = 0; i < this.activeTypes.size(); i++) {
            if(!types.contains(this.activeTypes.get(i))) {
                // if there is an active type that the List does not have,
                // then the List's types do not fit the selection
                return false;
            }
        }

        // If the list of types has all of the active types, then the types fit
        return true;
    }

    /**
     * Returns a list of Strings that contains the name of every type
     * 
     * @return List of Strings that contains the name of every type
     */
    public List<String> getAllTypes() {
        // Return array of types as a List
        return Arrays.asList(this.types);
    }

    /**
     * Selects a type and updates the filtered pokemon based on the new selection
     * 
     * @param type The type to select
     */
    public void addType(String type) {
        String typeString = type.toLowerCase().strip(); // Standardize spacing/capitalization
        
        // If the type is in the list of all the types, then add it to activeTypes
        for (int i = 0; i < this.types.length; i++) {
            String other = this.types[i];
            // If the type is found, add to activeTypes, update filtered Pokemon, and return
            if (typeString.equals(other.toLowerCase())) {
                this.activeTypes.add(other);
                this.updateFilteredPokemon();
                return;
            }
        }
        
        // If the type is not found - do nothing
        return;
    }

    /**
     * Deselects a type by removing it from the List of active types
     */
    public void removeType(String type) {
        String typeString = type.toLowerCase().strip(); // Standardize spacing/capitalization

        // If the type is in the list of all the types, then remove it from activeTypes
        for (int i = 0; i < this.types.length; i++) {
            String other = this.types[i];
            // If the type is found, remove from activeTypes, update filtered Pokemon, and return
            if (typeString.equals(other.toLowerCase())) {
                this.activeTypes.remove(other);
                this.updateFilteredPokemon();
                return;
            }
        }
        
        // If the type is not found - do nothing
        return;
    }

    /**
     * Sets a filter such that only Pokemon with a combat power >= minimum are selected
     * 
     * @param minimum The combat power filter value
     */
    public void filterPower(int minimum) {
        // Set the combat power filter
        this.combatPowerFilter = minimum;

        // Update the filtered Pokemon
        this.updateFilteredPokemon();
    }

    /**
     * Returns a list of the currently selected types
     * 
     * @return A List of the currently selected types
     */
    public List<String> getTypes() {
        return this.activeTypes;
    }
    
    /**
     * Returns a list of Pokemon that fit the type and combat power filters
     * 
     * @return A List of Pokemon that fit the specified filters
     */
    public List<PokemonInterface> getFilteredPokemon() {
        return this.filteredPokemon;
    }

    /**
     * Returns a Pokemon object if a Pokemon with the specified name is found within
     * the list of filtered Pokemon. If the Pokemon is not found, then a NoSuchElementException 
     * is thrown.
     * 
     * @param name The name of the Pokemon being searched for
     * @return A reference to the Pokemon object with the specified name
     * 
     */
    public Pokemon getByName(String name) throws NoSuchElementException {
        String adjustedName = name.toLowerCase().strip();
        for(PokemonInterface pokemon : this.filteredPokemon) {
            String pokemonName = pokemon.getName().toLowerCase().strip();
            
            // If the input is equal to the current Pokemon's name, return the Pokemon
            if(adjustedName.equals(pokemonName)) {
                return (Pokemon) pokemon;
            }
        }

        // If none of the Pokemon matched, throw Exception
        throw new NoSuchElementException("Pokemon not found in filtered list");
    }
}