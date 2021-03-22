import java.util.Scanner;
import java.util.List;

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

    static void consoleClear() {  
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    public Frontend(BackendInterface backend){
        this.backend = backend;
        this.currentMinPower = 0;
        this.currentSearchInput = null;
        this.currentMode = "b";
    }
    
    boolean isRunning(){
        return currentMode == null;
    }

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

    String getBaseModeInfo(){
        return
          "t : type selection mode                               .-----------.\n"
        + "n : name search mode                                  | Base Mode |\n"
        + "p : total power filtering mode                        '-----------'\n"
        + "x : exit\n";
    }

    String getTypeModeInfo(){
        return
          "Type the index of a type you would like     .---------------------.\n"
        + "to select or deselect                       | Type Selection Mode |\n"
        + "x : back to main menu                       '---------------------'\n";
    }

    String getNameModeInfo(){
        return 
          "Type a name or partial name to search       .---------------------.\n"
        + "through the filtered Pokedex                | Name Selection Mode |\n"
        + "x : back to main menu                       '---------------------'\n";
    }

    String getPowerModeInfo(){
        return 
          "Type the minimum value the hundreds digit .----------------------.\n"
        + "of combat power you want.                 | Power Filtering Mode |\n"
        + "For example, `2` will select the power    '----------------------'\n"
        + "ranges from 200+.\n"
        + "x : back to menu\n";
    }

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
                output += (i+") "+filteredPokemon.get(i).getName() + "\n");
            }
        }
        return output;
    }

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

    String getPowerSelectionOutput(){
        return 
            "CURRENT MINIMUM : "+(currentMinPower*100)+"\n";
    }

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

    void processNameInput(String input){
        if(input.equals("x")){
            currentMode = "b";
        }else{
            currentSearchInput = input;
        }
    }
  
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

    public BackendInterface getBackend(){
        return backend;
    }
}