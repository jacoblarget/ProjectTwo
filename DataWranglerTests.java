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

import org.junit.Test;
import static org.junit.Assert.*;

public class DataWranglerTests {
	
	public static void main(String[] args){
		(new DataWranglerTests()).runTests();

		// Console Output Test
// 		PokemonDataReaderInterface reader = new PokemonDataReader();
		
// 		try {
//      List<PokemonInterface> list = reader.readDataSet(new FileReader("src/updatedPokemon.csv"));
     
//      for(PokemonInterface p: list) {
//        System.out.println(p);
//        System.out.println();
//      }
//    } 
// 		catch (Exception e) 
// 		{
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
	}
	
	// Tests will run as JUnit Tests, as well as return a boolean and print to the console
	public void runTests(){

		if(this.testPokemonConstructor()) {
			System.out.println("Test Pokemon Constructor: PASSED");
		}
		else{
			System.out.println("Test Pokemon Constructor: FAILED");
		}

		if(this.testPokemonGetters()) {
			System.out.println("Test Pokemon Getters: PASSED");
		}
		else{
			System.out.println("Test Pokemon Getters: FAILED");
		}

		if(this.testPokemonReaderConstructor()) {
			System.out.println("Test Pokemon Reader Constructor: PASSED");
		}
		else{
			System.out.println("Test Pokemon Reader Constructor: FAILED");
		}

		if(this.testPokemonReaderListSize()) {
			System.out.println("Test Pokemon Reader List size: PASSED");
		}
		else{
			System.out.println("Test Pokemon Reader List size: FAILED");
		}

		if(this.testPokemonReaderReadDataSet()) {
			System.out.println("Test Pokemon Reader Expected List from readDataSet(): PASSED");
		}
		else{
			System.out.println("Test Pokemon Reader Expected List from readDataSet(): FAILED");
		}
	}

	// Tests implemented below

	@SuppressWarnings("unused")
  @Test
	/**
	 * This test instantiates a Pokemon object to make sure the constructor functions
	 * as expected. It passes when the object is initialized with its fields set to the
	 * correct values, and fails otherwise.
	 * @return true if test passed, false otherwise
	 */
	public boolean testPokemonConstructor(){
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
			
			if(testPokemon != null){
				return true;
			}
			else{
				fail("No Pokemon object instantiated.");
				return false;
			}
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
			return false;
		}
	}

	@Test
	/**
	 * After above test verifies that constructor does not cause issues,
	 * this test verifies that the Pokemon object getters will properly return
	 * the values the constructor placed in that Pokemon object's fields.
	 * @return true is test passes, false otherwise
	 */
	public boolean testPokemonGetters(){
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
		
			if(
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
				!testPokemon.getTypes().contains("Poison")
			  ){
				fail("Getter failed to properly retrieve Pokemon info.");
				return false;
			  }
			else{
				return true;
			}	
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
			return false;
		}
	}

	@Test
	/**
	 * Tests the Reader classes readDataSet() method to verify it properly
	 * takes a StringReader or FileReader and returns a List of Pokemon.
	 * @return true if test passes, false otherwise
	 */
	public boolean testPokemonReaderReadDataSet(){
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

			if(
				!pokemonList.get(0).getName().equals("Bulbasaur") ||
				!pokemonList.get(1).getName().equals("Ivysaur") ||
				!pokemonList.get(2).getName().equals("Venusaur") ||
				(pokemonList.get(0).getHP() != 45) ||
				(pokemonList.get(1).getHP() != 60) ||
				(pokemonList.get(2).getHP() != 80)
			){
				fail("DataReader.readDataSet() not able to retrieve info from CSV format.");
				return false;	
			}

			return true;
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
			return false;
		}
	}

	@Test
	/**
	 * Tests that the readDataSet method returns an appropriately sized list of Pokemon objects.
	 * @return true if test passes, false otherwise
	 */
	public boolean testPokemonReaderListSize(){
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
			
			if(
				(pokemonListLen1.size() != 1) ||
				(pokemonListLen2.size() != 2) ||
				(pokemonListLen3.size() != 3)
			){
				fail("DataReader.readDataSet() not returning list of correct size.");
				return false;	
			}

			return true;
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
			return false;
		}
	}

	@Test
	/**
	 * Verify that a PokemonDataReader can be instantiated (eventually by the Backend)
	 * without throwing excecptions
	 * @return true if test passes, false otherwise
	 */
	public boolean testPokemonReaderConstructor(){
		try{
			PokemonDataReaderInterface dataReader = new PokemonDataReader();

			if(dataReader instanceof PokemonDataReaderInterface){
				return true;
			}
			else{
				fail("Failed to correctly create PokemonDataReader.");
				return false;
			}
		}
		catch(Exception e){
			System.out.println("An unexpected exception was thrown, test failed.");
			e.printStackTrace();
			fail("Unexpected exception");
			return false;
		}
	}

}
