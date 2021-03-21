import java.util.Scanner;
import java.util.List;

public class Frontend{
    //m(enu) g(eneration), t(ype), n(ame), p(ower), (e)x(it)
    private BackendInterface backend;
    private Scanner in;

    public static void main(String args[]){
        Frontend frontend = new Frontend(new Scanner(System.in), new BackendDummy());
        frontend.runBaseMode();
    }
    
    public Frontend(Scanner in, BackendInterface backend){
        this.in = in;
        this.backend = backend;
    }

    void runBaseMode(){
        boolean isBaseMenuRunning = true;
        while(isBaseMenuRunning){
            printPokedexTitle();
            System.out.println(
          "g : generation selection mode                         .-----------.\n"
        + "t : type selection mode                               | Base Mode |\n"
        + "n : name search mode                                  '-----------'\n"
        + "p : total power filtering mode\n"
        + "x : exit\n");
            String input = in.next();
            switch(input){
                case "g":
                    runGenSelectMode();
                    break;
                case "t":
                    runTypeSelectMode();
                    break;
                case "n":
                    runNameSelectMode();
                    break;
                case "p":
                    runPowerFilterMode();
                    break;
                case "x":
                    isBaseMenuRunning = false;
                    consoleClear();
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    void runGenSelectMode(){
        boolean isGenMenuRunning = true;
        while(isGenMenuRunning){
            printPokedexTitle();
            System.out.println(
              "Type the index of a generation you    .---------------------------.\n"
            + "would like to select or deselect.     | Generation Selection Mode |\n"
            + "Only one generation may be selected   '---------------------------'\n"
            + "at a time. \n\n"
            + "x : back to menu \n");

            List<Integer> selectedGens = backend.getGenerations();
            List<Integer> allGens = backend.getAllGenerations();
            for(Integer gen : allGens){
                System.out.println(
                    "GENERATION "+gen+" : " + (selectedGens.contains(gen) ? "SELECTED" : ""));
            }

            String input = in.next();
            try{
                Integer selectedGen = Integer.parseInt(input);
                if(selectedGens.contains(selectedGen)){
                    backend.removeGeneration(selectedGen);
                }else{
                    backend.addGeneration(selectedGen);
                }
            }catch(NumberFormatException ex){
                if(input.equals("x")){
                    isGenMenuRunning = false;
                }
            }
        }
    }

    void runTypeSelectMode(){
        boolean isTypeMenuRunning = true;
        while(isTypeMenuRunning){
            printPokedexTitle();
            System.out.println(
              "Type the index of a type you would like     .---------------------.\n"
            + "to select or deselect                       | Type Selection Mode |\n"
            + "x : back to main menu                       '---------------------'\n");
            
            List<String> selectedTypes = backend.getTypes();
            List<String> allTypes = backend.getAllTypes();
            for(String type : allTypes){
                System.out.println(
                    type.toUpperCase() +" : " + (selectedTypes.contains(type) ? "SELECTED" : ""));
            }

            String input = in.next();

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
                    isTypeMenuRunning = false;
                }
            }
        }
    }

    void runNameSelectMode(){
        boolean isNameMenuRunning = true;
        while(isNameMenuRunning){
            printPokedexTitle();
            System.out.println(
              "Type a name or partial name to search       .---------------------.\n"
            + "through the filtered Pokedex                | Name Selection Mode |\n"
            + "x : back to main menu                       '---------------------'\n");
            String input = in.next();
            switch(input){
                case "x":
                    isNameMenuRunning = false;
                    return;
                default:
                    PokemonInterface pokemon = backend.getByName(input);
                    if(pokemon == null){
                        System.out.println("No pokemon with the name "+input+" found.");
                    }else{
                        System.out.println(pokemon);
                    }

            }
        }
    }
    
    void runPowerFilterMode(){
        boolean isPowerMenuRunning = true;
        while(isPowerMenuRunning){
            printPokedexTitle();
            System.out.println(
              "Type the minimum value the hundreds digit .----------------------.\n"
            + "of total power you want.                  | Power Filtering Mode |\n"
            + "For example, `2` will select the power    '----------------------'\n"
            + "ranges from 200+.\n"
            + "x : back to menu\n\n");

            System.out.println("SELECTED POWER RANGES : ");
            List<Integer> selectedPowers = backend.getPowers();
            for(Integer range : selectedPowers){
                System.out.println("\t"+(range*100) + " -> "+(range*100+100));
            }
            String input = in.next();
            try{
                Integer selectedPower = Integer.parseInt(input);
                if(selectedPowers.contains(selectedPower)){
                    backend.removeGeneration(selectedPower);
                }else{
                    backend.addGeneration(selectedPower);
                }
            }catch(NumberFormatException ex){
                if(input.equals("x")){
                    isGenMenuRunning = false;
                }
            }
        }
    }

    void printPokedexTitle(){ 
        consoleClear();
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        System.out.println(
      "                ▀▄▀▄▀▄ Pokémon™: "+ANSI_RED+"Red"+ANSI_RESET+" & "
        + ANSI_BLACK+"Black"+ANSI_RESET+" ▄█▄▀▄▀\n"
        + " ▀███▀▀▀██▄         ▀███                    ▀███\n"
        + "  ██   ▀██▄          ██                      ██\n"
        + "  ██   ▄██  ▄██▀██▄  ██  ▄██▀  ▄▄█▀██   ▄█▀▀███   ▄▄█▀██▀██▀   ▀██▀\n"
        + "  ███████  ██▀   ▀██ ██ ▄█    ▄█▀   ██▄██    ██  ▄█▀   ██ ▀██ ▄█▀\n"
        + "  ██       ██     ██ ██▄██    ██▀▀▀▀▀▀███    ██  ██▀▀▀▀▀▀   ███\n"
        + "  ██       ██▄   ▄██ ██ ▀██▄  ██▄    ▄▀██    ██  ██▄    ▄ ▄█▀ ██▄\n"
        + "▄████▄      ▀█████▀▄████▄ ██▄▄ ▀█████▀ ▀████▀███▄ ▀█████▀██▄   ▄██▄\n");
    }

    void consoleClear() {  
        try{
            Runtime.getRuntime().exec("clear");
        } catch (Exception e) { System.out.println(e); }
    }
}