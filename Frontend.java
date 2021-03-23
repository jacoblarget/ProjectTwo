import java.util.Scanner;
import java.util.List;

// --== CS400 File Header Information ==--
// Name: Guilhem Ane
// Email: gane@wisc.edu
// Team: Blue Team
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: 

public class Frontend{
    private BackendInterface backend;
    private Scanner in;
    private int currentMinPower;
    private String currentSearchInput;
    //m(enu), t(ype), n(ame), p(ower), (e)x(it), null = exit program
    private String currentMode;

    public static void main(String args[]){
        Frontend frontend = new Frontend(new Backend(args[0]));
        Scanner sc = new Scanner(System.in);
        while(frontend.currentMode != null){
            consoleClear();
            System.out.println(frontend.getPokedexTitle());
            System.out.println(frontend.getCurrentInfoText());
            System.out.println(frontend.getCurrentOutputText());
            frontend.processInput(sc.next());
        }
    }

    /**
     * Clears the console by adding many new lines
     */
    static void consoleClear() {  
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
    

    /**
     * Initializes a new Frontend object
     */
    public Frontend(BackendInterface backend){
        this.backend = backend;
        this.currentMinPower = 0;
        this.currentSearchInput = null;
        this.currentMode = "b";
    }
    
    /**
     * @return true if the frontend is in any of the valid modes, 
     *      false if the frontend is in `null` mode
     */
    boolean isRunning(){
        return currentMode != null;
    }

    /**
     * Returns the instructions for the current mode
     */
    public String getCurrentInfoText(){
        switch(currentMode){
            case "b":
                return getBaseModeInfo();
            case "t":
                return getTypeModeInfo();
            case "n":
                return getNameModeInfo();
            case "p":
                return getPowerModeInfo();
        }
        return "";
    }

    /**
     * Returns the output for the current mode
     */
    public String getCurrentOutputText(){
        switch(currentMode){
            case "b":
                return getTopBottomFivePokemon();
            case "t":
                return getTypeSelectionOutput();
            case "n":
                return getNameSelectionOutput();
            case "p":
                return getPowerSelectionOutput();
        }
        return "";
    }

    /**
     * Processes any input for the current mode
     */
    public void processInput(String input){
        switch(currentMode){
            case "b":
                processBaseInput(input);
                break;
            case "t":
                processTypeInput(input);
                break;
            case "n":
                processNameInput(input);
                break;
            case "p":
                processPowerInput(input);
                break;
        }
    }

    /**
     * @return instructions for the base mode
     */
    String getBaseModeInfo(){
        return
          "t : type selection mode                               .-----------.\n"
        + "n : name search mode                                  | Base Mode |\n"
        + "p : total power filtering mode                        '-----------'\n"
        + "x : exit\n";
    }

    /**
     * @return instructions for the type selection mode
     */
    String getTypeModeInfo(){
        return
          "Type the index of a type you would like     .---------------------.\n"
        + "to select or deselect                       | Type Selection Mode |\n"
        + "x : back to main menu                       '---------------------'\n";
    }

    /**
     * @return instructions for the name search mode
     */
    String getNameModeInfo(){
        return 
          "Type a name or partial name to search       .---------------------.\n"
        + "through the filtered Pokedex                | Name Selection Mode |\n"
        + "x : back to main menu                       '---------------------'\n";
    }

    /**
     * @return instructions for the power filtering mode
     */
    String getPowerModeInfo(){
        return 
          "Type the minimum value the hundreds digit .----------------------.\n"
        + "of combat power you want.                 | Power Filtering Mode |\n"
        + "For example, `2` will select the power    '----------------------'\n"
        + "ranges from 200+.\n"
        + "x : back to menu\n";
    }

    /**
     * @return the top five and bottom five pokemon as a String
     */
    String getTopBottomFivePokemon(){
        String output = "";
        //add current filtered pokemon to output
        List<PokemonInterface> filteredPokemon = backend.getFilteredPokemon();
        for(int i=0; i<5 && i<filteredPokemon.size(); i++){
            output += ((i+1)+") "+filteredPokemon.get(i).getName() + "\n");
        }
        if(filteredPokemon.size() > 5){
            output += "...\n";
            for(int i=Math.max(filteredPokemon.size()-6, 5); i<filteredPokemon.size(); i++){
                output += (i+1)+") "+filteredPokemon.get(i).getName() + "\n";
            }
        }
        return output;
    }

    /**
     * @return a list of the selected and unselected types as a single String
     */
    String getTypeSelectionOutput(){
        String output = "";
        List<String> selectedTypes = backend.getTypes();
        List<String> allTypes = backend.getAllTypes();
        for(int i=0; i<allTypes.size(); i++){
            String type = allTypes.get(i);
            output +=
                i + ") "+(selectedTypes.contains(type) ? "X " : "_") + 
                type.toUpperCase() + "\n";
        }
        return output;
    }

    /**
     * @return the output given by searching for any Pokemon's name
     */
    String getNameSelectionOutput(){
        if(currentSearchInput == null) return "";

        String output = "";
        try{
            PokemonInterface pokemon = backend.getByName(currentSearchInput);
            output += pokemon;
        }catch(Exception ex){
            output += "No pokemon with the name "+currentSearchInput+" found.";
        }
        return output;
    }

    /**
     * @return the the current combat power filter minimum as a pretty String
     */
    String getPowerSelectionOutput(){
        return 
            "CURRENT MINIMUM : "+(currentMinPower*100)+"\n";
    }

    /**
     * Processes the input for the base mode
     */
    void processBaseInput(String input){
        switch(input){
            case "n":
                if(!currentMode.equals("n"))
                    currentSearchInput = null;
            case "t":
            case "p":
                currentMode = input;
                return;
            case "x":
                currentMode = null;
        }
    }

    /**
     * Processes the input for the type selection mode
     */
    void processTypeInput(String input){
        List<String> allTypes = backend.getAllTypes();
        List<String> selectedTypes = backend.getTypes();
        try{
            Integer selectedTypeIndex = Integer.parseInt(input);
            if(selectedTypeIndex < allTypes.size() && selectedTypeIndex >= 0){
                String selectedType = allTypes.get(selectedTypeIndex);
                if(selectedTypes.contains(selectedType)){
                    backend.removeType(selectedType);
                }else{
                    backend.addType(selectedType);
                }
            }
        }catch(NumberFormatException ex){
            if(input.equals("x")){
                currentMode = "b";
            }
        }
    }

    /**
     * Processes the input for the name selection mode
     */
    void processNameInput(String input){
        if(input.equals("x")){
            currentMode = "b";
        }else{
            currentSearchInput = input;
        }
    }
  
    /**
     * Processes the input for the power filtering mode
     */
    void processPowerInput(String input){
        try{
            Integer selectedPower = Integer.parseInt(input);
            backend.filterPower(selectedPower);
            currentMinPower = selectedPower;
        }catch(NumberFormatException ex){
            if(input.equals("x")){
                currentMode = "b";
            }
        }
    }

    /**
     * @return the Pokemon title text
     */
    String getPokedexTitle(){ 
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        return
          "                ▀▄▀▄▀▄ Pokémon™: "+ANSI_RED+"Red"+ANSI_RESET+" & "
        +                ANSI_BLACK+"Black"+ANSI_RESET+" ▄█▄▀▄▀\n"
        + " ▀███▀▀▀██▄         ▀███                    ▀███\n"
        + "  ██   ▀██▄          ██                      ██\n"
        + "  ██   ▄██  ▄██▀██▄  ██  ▄██▀  ▄▄█▀██   ▄█▀▀███   ▄▄█▀██▀██▀   ▀██▀\n"
        + "  ███████  ██▀   ▀██ ██ ▄█    ▄█▀   ██▄██    ██  ▄█▀   ██ ▀██ ▄█▀\n"
        + "  ██       ██     ██ ██▄██    ██▀▀▀▀▀▀███    ██  ██▀▀▀▀▀▀   ███\n"
        + "  ██       ██▄   ▄██ ██ ▀██▄  ██▄    ▄▀██    ██  ██▄    ▄ ▄█▀ ██▄\n"
        + "▄████▄      ▀█████▀▄████▄ ██▄▄ ▀█████▀ ▀████▀███▄ ▀█████▀██▄   ▄██▄\n";
    }

    /**
     * @return the Backend object for testing
     */
    public BackendInterface getBackend(){
        return backend;
    }

    /**
     * @return the minimum power for testing
     */
    public int getMinPower(){
        return currentMinPower;
    }
}