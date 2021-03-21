// --== CS400 File Header Information ==--
// Name: Patrick Nowakowski
// Email: pnowakowski@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: 

import java.io.*;
import java.util.List;
import java.util.zip.DataFormatException;

public interface PokemonDataReaderInterface {

  public List<PokemonInterface> readDataSet(Reader inputFileReader)
      throws IOException, DataFormatException;
  
}
