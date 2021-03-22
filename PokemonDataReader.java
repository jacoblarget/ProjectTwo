// --== CS400 File Header Information ==--
// Name: Patrick Nowakowski
// Email: pnowakowski@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * This class provides the readDataSet() method needed to convert a CSV format to 
 * a list of Pokemon objects.
 * @author pnowa
 */
public class PokemonDataReader implements PokemonDataReaderInterface{

  @Override
  /**
   * When passed a Reader for a CSV file, this method will return a list
   * of Pokemon objects
   * @param inputFileReader FileReader for Pokemon CSV file
   * @return a list of Pokemon
   */
  public List<PokemonInterface> readDataSet(Reader inputFileReader)
      throws IOException, DataFormatException{

    List<PokemonInterface> ret = new ArrayList<PokemonInterface>();
    int stopAfterGeneration = 1; // Which Pokemon generation should we stop reading after?

    int lastCharNum = -1;
    
    // 10 LF
    // 13 CR
    // 44 ','
    
    // Read one character at a time until a line feed (ASCII 10)
    // This is done to skip the first line
    while(lastCharNum != 10) {
      lastCharNum = inputFileReader.read();
    }
    
    // Stats for each Pokemon
    String[] statsArray = new String[11];
    // 0 number
    // 1 name
    // 2 Type One
    // 3 Type Two
    // 4 Total CP
    // 5 HP
    // 6 Attack
    // 7 Defense
    // 8 Speed
    // 9 Generation
    // 10 Legendary
    
    String readBuffer = ""; // Stores what the read() method has read
    int i = 0;
    
    // This while loop will read one char at a time until the end of the file
    while(lastCharNum != -1) {
      // Get the next char
      lastCharNum = inputFileReader.read();
      
      switch(lastCharNum) {
      case -1: // End of file
        break;
        
      case 10: // Line feed, need to send statsArray values to new Pokemon()
        // Check for generation first?
        // Adds a new Pokemon with the stats pulled from the CSV to the return list
        
        // Store readBuffer into appropriate index of stats array
        // End of line means the only thing left to store is Legendary at index i=10
        // System.out.println("Storing " + readBuffer + " at index " + i);
        statsArray[i] = readBuffer;
        readBuffer = "";
        i++; // Next stat

        int number = Integer.valueOf(statsArray[0]).intValue();
        int totalCombatPower = Integer.valueOf(statsArray[4]).intValue();
        int hp = Integer.valueOf(statsArray[5]).intValue();
        int attack = Integer.valueOf(statsArray[6]).intValue();
        int defense = Integer.valueOf(statsArray[7]).intValue();
        int speed = Integer.valueOf(statsArray[8]).intValue();
        int generation = Integer.valueOf(statsArray[9]).intValue();
        
        if(generation > stopAfterGeneration) {
          // If the generation of this line's Pokemon is greater than the stop generation,
          // set lastCharNum to -1 so the fileRead stops
          lastCharNum = -1;
          break;
        }
        
        // Add type one from statsArray[2] to types, as well as type two if it exists
        List<String> types = new ArrayList<String>();
        types.add(statsArray[2]);
        if(!statsArray[3].equals("")) {
          types.add(statsArray[3]);
        }
        
        boolean isLegendary;
        if(statsArray[10].equals("TRUE")) {
          isLegendary = true;
        }
        else {
          isLegendary = false;
        }

        ret.add(new Pokemon(statsArray[1], number, types, totalCombatPower, hp, attack, defense, speed, generation, isLegendary));
        i = 0; // New line, new Pokemon, so move statsArray index back to 0
        break;
        
      case 13: // Carriage return
        // Do nothing
        break;
        
      case 44: // Comma
        // Store readBuffer into appropriate index of stats array
        //System.out.println("Storing " + readBuffer + " at index " + i);
        statsArray[i] = readBuffer;
        readBuffer = "";
        i++; // Next stat
        break;
        
      default: // Not a comma or line end, add to the readBuffer
        readBuffer += (char)lastCharNum;
      }
      // End of while loop, check lastCharNum != -1 and read() next character
    }
    
    // Close the reader and return the list of Pokemon
    inputFileReader.close();
    return ret;
  }
  
  @Override
  /**
   * When passed a Reader for a CSV file, this method will return a list
   * of Pokemon objects
   * @param inputFileReader FileReader for Pokemon CSV file
   * @return a list of Pokemon
   */
  public List<PokemonInterface> readDataSet(Reader inputFileReader, int stopGeneration)
      throws IOException, DataFormatException{

    List<PokemonInterface> ret = new ArrayList<PokemonInterface>();
    int stopAfterGeneration = stopGeneration; // Which Pokemon generation should we stop reading after?

    int lastCharNum = -1;
    
    // 10 LF
    // 13 CR
    // 44 ','
    
    // Read one character at a time until a line feed (ASCII 10)
    // This is done to skip the first line
    while(lastCharNum != 10) {
      lastCharNum = inputFileReader.read();
    }
    
    // Stats for each Pokemon
    String[] statsArray = new String[11];
    // 0 number
    // 1 name
    // 2 Type One
    // 3 Type Two
    // 4 Total CP
    // 5 HP
    // 6 Attack
    // 7 Defense
    // 8 Speed
    // 9 Generation
    // 10 Legendary
    
    String readBuffer = ""; // Stores what the read() method has read
    int i = 0;
    
    // This while loop will read one char at a time until the end of the file
    while(lastCharNum != -1) {
      // Get the next char
      lastCharNum = inputFileReader.read();
      
      switch(lastCharNum) {
      case -1: // End of file
        break;
        
      case 10: // Line feed, need to send statsArray values to new Pokemon()
        // Check for generation first?
        // Adds a new Pokemon with the stats pulled from the CSV to the return list
        
        // Store readBuffer into appropriate index of stats array
        // End of line means the only thing left to store is Legendary at index i=10
        // System.out.println("Storing " + readBuffer + " at index " + i);
        statsArray[i] = readBuffer;
        readBuffer = "";
        i++; // Next stat

        int number = Integer.valueOf(statsArray[0]).intValue();
        int totalCombatPower = Integer.valueOf(statsArray[4]).intValue();
        int hp = Integer.valueOf(statsArray[5]).intValue();
        int attack = Integer.valueOf(statsArray[6]).intValue();
        int defense = Integer.valueOf(statsArray[7]).intValue();
        int speed = Integer.valueOf(statsArray[8]).intValue();
        int generation = Integer.valueOf(statsArray[9]).intValue();
        
        if(generation > stopAfterGeneration) {
          // If the generation of this line's Pokemon is greater than the stop generation,
          // set lastCharNum to -1 so the fileRead stops
          lastCharNum = -1;
          break;
        }
        
        // Add type one from statsArray[2] to types, as well as type two if it exists
        List<String> types = new ArrayList<String>();
        types.add(statsArray[2]);
        if(!statsArray[3].equals("")) {
          types.add(statsArray[3]);
        }
        
        boolean isLegendary;
        if(statsArray[10].equals("TRUE")) {
          isLegendary = true;
        }
        else {
          isLegendary = false;
        }

        ret.add(new Pokemon(statsArray[1], number, types, totalCombatPower, hp, attack, defense, speed, generation, isLegendary));
        i = 0; // New line, new Pokemon, so move statsArray index back to 0
        break;
        
      case 13: // Carriage return
        // Do nothing
        break;
        
      case 44: // Comma
        // Store readBuffer into appropriate index of stats array
        //System.out.println("Storing " + readBuffer + " at index " + i);
        statsArray[i] = readBuffer;
        readBuffer = "";
        i++; // Next stat
        break;
        
      default: // Not a comma or line end, add to the readBuffer
        readBuffer += (char)lastCharNum;
      }
      // End of while loop, check lastCharNum != -1 and read() next character
    }
    
    // Close the reader and return the list of Pokemon
    inputFileReader.close();
    return ret;
  }

}
