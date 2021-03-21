// --== CS400 File Header Information ==--
// Name: Vatsal Patel
// Email: vpatel43@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Gary Dahl
// Notes to Grader: 

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.StringReader;

public class BackEndDeveloperTests {

	/**
	 * This test ensures that the number of Pokemon selected at the start of the Program is 0.
	 */
	@Test
	public void testInitialPokemonSelected() {
		try {
		    // Instatiate BackEnd
		    //BackendInterface backend = null;  
			Backend backend = new Backend(new StringReader(
		    "#,Name,Type1,Type2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n" 
		    + "1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n" 
		    + "12,Butterfree,Bug,Flying,395,60,45,50,70,1,FALSE\n" 
		    + "48,Venonat,Bug,Poison,305,60,55,50,45,1,FALSE\n")); 
		
		    // Test that the initial list of selected Pokemon is empty (size 0)
		    List<PokemonInterface> actual = backend.getFilteredPokemon();
			//System.out.println("List: " + Arrays.toString(actual.toArray()));

		    // Run assertEquals
			assertEquals(0, actual.size(), "No Pokemon Should Be Selected At Start Of Program");
		} catch (Exception e) {
			fail("Unexpected Exception Caught");
			e.printStackTrace();
		}
	}

	/**
	 * The list returned by getTypes() should be empty at the start of the program
	 */
	@Test
	public void testInitialGetTypes() {
		try {
		    // Instatiate BackEnd
		    BackendInterface backend = new Backend(new StringReader(
		    "#,Name,Type1,Type2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n" 
		    + "1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n" 
		    + "12,Butterfree,Bug,Flying,395,60,45,50,70,1,FALSE\n" 
		    + "48,Venonat,Bug,Poison,305,60,55,50,45,1,FALSE\n"));
		
		    // Test that the initial list of selected types is empty
		    List<String> actual = backend.getTypes();

		    // Run assertEquals
		    assertEquals(0, actual.size(), "No Types Should Be Selected At Start Of Program");
		} catch (Exception e) {
		    fail("Unexpected Exception Caught");
		}
	}
	
	/**
	 * Tests whether getByName() throws a NoSuchElementException when the Pokemon has not been found
	 */
	@Test
	public void testGetByNameException() {
		try {
		    // Instatiate BackEnd
		    BackendInterface backend = new Backend(new StringReader(
		    "#,Name,Type1,Type2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n" 
		    + "1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n" 
		    + "12,Butterfree,Bug,Flying,395,60,45,50,70,1,FALSE\n" 
		    + "48,Venonat,Bug,Poison,305,60,55,50,45,1,FALSE\n"));
		
		    // Try to retrieve a Pokemon that is not in the backend
		    boolean found = false;
		    try{
		        Pokemon search = backend.getByName("Invalid");
			// Exception should have been thrown
			found = true;
		    } catch(NoSuchElementException nsee) {
		    	// Behaves as expected
		    }

		    // Run assertEquals
		    assertEquals(false, found, "getByName() should throw Exception when Pokemon not found");
		} catch (Exception e) {
		    fail("Unexpected Exception Caught");
		}
	}

	/**
	 * Test that after selecting a type, the list returned by getFilteredPokemon() is not empty and contains
	 * the correct number of Pokemon
	 */
	@Test
	public void testGetFilteredSizeAfterSelectingType() {
		try {
		    // Instatiate BackEnd
		    BackendInterface backend = new Backend(new StringReader(
		    "#,Name,Type1,Type2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n" 
		    + "1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n" 
		    + "12,Butterfree,Bug,Flying,395,60,45,50,70,1,FALSE\n" 
		    + "48,Venonat,Bug,Poison,305,60,55,50,45,1,FALSE\n")); 
		
		    // Select 'Bug' Types
		    backend.addType("Bug");

		    // Retrive List of filtered Pokeon
		    List<PokemonInterface> pokemon = backend.getFilteredPokemon();
		    int size = pokemon.size();
			
		    // Run assertEquals
		    assertEquals(2, size, "getFilteredPokemon() should return a List of 2 Bug Pokemon");
		} catch (Exception e) {
		    fail("Unexpected Exception Caught");
		}
	}

	/**
	 * Tests that after adding types with addType(), that the correct Pokemon are returned with 
	 * getFilteredPokemon() and that these Pokemon can be found using getByName()
	 */
	@Test
	public void testGetFilteredPokemonAfterSelectingType() {
		try {
		    // Instatiate BackEnd
		    BackendInterface backend = new Backend(new StringReader(
		    "#,Name,Type1,Type2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n" 
		    + "1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n" 
		    + "12,Butterfree,Bug,Flying,395,60,45,50,70,1,FALSE\n" 
		    + "48,Venonat,Bug,Poison,305,60,55,50,45,1,FALSE\n")); 
		
		    // Select 'Pokemon' and 'Grass' Types
		    backend.addType("Poison");
		    backend.addType("Grass");

		    // Retrive List of filtered Pokeon
		    List<PokemonInterface> pokemon = backend.getFilteredPokemon();
		    int size = pokemon.size();	    

		    // Run assertEquals to verify size
		    assertEquals(1, size, "getFilteredPokemon() should return a List of 1 Bug/Poision Pokemon");

		    // Attempt to search for a Pokemon in the resulting list using getByName()
		    boolean found = false;
		    try {
				Pokemon search = backend.getByName("Bulbasaur");
				// No Exception - Pokemon was found
				found = true;
		    } catch (NoSuchElementException nsee) {
				// Pokemon was not selected when it should have been
		    }

		    assertEquals(true, found, "Venonat should be selected when Grass and Poison are selected " + pokemon.get(0));
		} catch (Exception e) {
		    fail("Unexpected Exception Caught");
		}
	}
}
