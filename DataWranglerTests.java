// --== CS400 File Header Information ==--
// Name: Patrick Nowakowski
// Email: pnowakowski@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.io.FileReader;
import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class DataWranglerTests {
	
	public static void main(String[] args){
		(new DataWranglerTests()).runTests();
	}
	
	// Tests will run as JUnit Tests, as well as return a boolean and print to the console
	public void runTests(){
	  this.testPokemonGetters();
	  this.testPokemonReaderConstructor();
	  this.testPokemonReaderListSize();
	  this.testPokemonReaderReadDataSet();
	}

	// Tests implemented below

	@SuppressWarnings("unused")
  @Test
	/**
	 * This test instantiates a Pokemon object to make sure the constructor functions
	 * as expected. It passes when the object is initialized with its fields set to the
	 * correct values, and fails otherwise.
	 */
	public void testPokemonConstructor(){
		try{
			String name = "Bulbasaur";
			int number = 1;
			int combatPower = 318;
			int hp = 45;
			int atk = 49;
		       	int def = 49;
			int spd = 45;
			int gen = 1;
			boolean legendary = false;
			ArrayList<String> types = new ArrayList<String>();
			types.add("Grass"); types.add("Poison");	

			PokemonInterface testPokemon = new Pokemon(name, number, types, combatPower, hp, atk, def, spd, gen, legendary);
			
			assertFalse(testPokemon == null);
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

	@Test
	/**
	 * After above test verifies that constructor does not cause issues,
	 * this test verifies that the Pokemon object getters will properly return
	 * the values the constructor placed in that Pokemon object's fields.
	 */
	public void testPokemonGetters(){
		try{
			String name = "Bulbasaur";
			int number = 1;
			int combatPower = 318;
			int hp = 45;
			int atk = 49;
		       	int def = 49;
			int spd = 45;
			int gen = 1;
			boolean legendary = false;
			ArrayList<String> types = new ArrayList<String>();
			types.add("Grass"); types.add("Poison");	

			PokemonInterface testPokemon = new Pokemon(name, number, types, combatPower, hp, atk, def, spd, gen, legendary);
		
			assertFalse(
				!testPokemon.getName().equals("Bulbasaur") ||
				(testPokemon.getNumber() != 1) ||	
				(testPokemon.getTotalCombatPower() != 318) ||	
				(testPokemon.getHP() != 45) ||	
				(testPokemon.getAttack() != 49) ||	
				(testPokemon.getDefense() != 49) ||	
				(testPokemon.getSpeed() != 45) ||	
				(testPokemon.getGeneration() != 1) ||	
				(testPokemon.getIsLegendary() != false) ||	
				!testPokemon.getTypes().contains("Grass") ||
				!testPokemon.getTypes().contains("Poison"));
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

	@Test
	/**
	 * Tests the Reader classes readDataSet() method to verify it properly
	 * takes a StringReader or FileReader and returns a List of Pokemon.
	 */
	public void testPokemonReaderReadDataSet(){
		try{
			PokemonDataReaderInterface dataReader = new PokemonDataReader();

			List<PokemonInterface> pokemonList = dataReader.readDataSet(
				new StringReader(
"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n"+
"1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n"+
"2,Ivysaur,Grass,Poison,405,60,62,63,60,1,FALSE\n"+
"3,Venusaur,Grass,Poison,525,80,82,83,80,1,FALSE\n"
				)		
			);

			assertFalse(
				!pokemonList.get(0).getName().equals("Bulbasaur") ||
				!pokemonList.get(1).getName().equals("Ivysaur") ||
				!pokemonList.get(2).getName().equals("Venusaur") ||
				(pokemonList.get(0).getHP() != 45) ||
				(pokemonList.get(1).getHP() != 60) ||
				(pokemonList.get(2).getHP() != 80)
			);
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

	@Test
	/**
	 * Tests that the readDataSet method returns an appropriately sized list of Pokemon objects.
	 */
	public void testPokemonReaderListSize(){
		try{
			PokemonDataReaderInterface dataReader = new PokemonDataReader();

			List<PokemonInterface> pokemonListLen1 = dataReader.readDataSet(
				new StringReader(
"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n"+
"1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n"
				)		
			);

			List<PokemonInterface> pokemonListLen2 = dataReader.readDataSet(
				new StringReader(
"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n"+
"1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n"+
"2,Ivysaur,Grass,Poison,405,60,62,63,60,1,FALSE\n"
				)		
			);

			List<PokemonInterface> pokemonListLen3 = dataReader.readDataSet(
				new StringReader(
"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Speed,Generation,Legendary\n"+
"1,Bulbasaur,Grass,Poison,318,45,49,49,45,1,FALSE\n"+
"2,Ivysaur,Grass,Poison,405,60,62,63,60,1,FALSE\n"+
"3,Venusaur,Grass,Poison,525,80,82,83,80,1,FALSE\n"
				)		
			);
			
			assertFalse(
				(pokemonListLen1.size() != 1) ||
				(pokemonListLen2.size() != 2) ||
				(pokemonListLen3.size() != 3)
			);
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

	@Test
	/**
	 * Verify that a PokemonDataReader can be instantiated (eventually by the Backend)
	 * without throwing excecptions
	 * @return true if test passes, false otherwise
	 */
	public void testPokemonReaderConstructor(){
		try{
			PokemonDataReaderInterface dataReader = new PokemonDataReader();

			assertTrue(dataReader instanceof PokemonDataReaderInterface);
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}

}
