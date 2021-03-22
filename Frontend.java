import java.util.Scanner;
import java.util.List;

public class Frontend{
    //m(enu) g(eneration), t(ype), n(ame), p(ower), (e)x(it)
    private BackendInterface backend;
    private Scanner in;

    public static void main(String args[]){
        Frontend frontend = new Frontend(new Scanner(System.in), new Backend());
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
          "t : type selection mode                               .-----------.\n"
        + "n : name search mode                                  | Base Mode |\n"
        + "p : total power filtering mode                        '-----------'\n"
        + "x : exit\n");
            String input = in.next();
            switch(input){
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
            for(int i=0; i<allTypes.size(); i++){
                String type = allTypes.get(i);
                System.out.println(
                    i + ") "+(selectedTypes.contains(type) ? "☒ " : "☐ ") + type.toUpperCase());
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
            + "of combat power you want.                 | Power Filtering Mode |\n"
            + "For example, `2` will select the power    '----------------------'\n"
            + "ranges from 200+.\n"
            + "x : back to menu\n\n");

            String input = in.next();
            try{
                Integer selectedPower = Integer.parseInt(input);
                backend.filterPower(selectedPower);
                isPowerMenuRunning = false;
            }catch(NumberFormatException ex){
                if(input.equals("x")){
                    isPowerMenuRunning = false;
                }
            }
        }
    }

    void printPokedexTitle(){ 
        consoleClear();
        String ANSI_WHITE_BACKGROUND = "\u001B[47m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        System.out.println(
      "                ▀▄▀▄▀▄ Pokémon™: "+ANSI_RED+"Red"+ANSI_RESET+" & "
        + ANSI_WHITE_BACKGROUND + ANSI_BLACK+"Black"+ANSI_RESET+" ▄█▄▀▄▀\n"
        + " ▀███▀▀▀██▄         ▀███                    ▀███\n"
        + "  ██   ▀██▄          ██                      ██\n"
        + "  ██   ▄██  ▄██▀██▄  ██  ▄██▀  ▄▄█▀██   ▄█▀▀███   ▄▄█▀██▀██▀   ▀██▀\n"
        + "  ███████  ██▀   ▀██ ██ ▄█    ▄█▀   ██▄██    ██  ▄█▀   ██ ▀██ ▄█▀\n"
        + "  ██       ██     ██ ██▄██    ██▀▀▀▀▀▀███    ██  ██▀▀▀▀▀▀   ███\n"
        + "  ██       ██▄   ▄██ ██ ▀██▄  ██▄    ▄▀██    ██  ██▄    ▄ ▄█▀ ██▄\n"
        + "▄████▄      ▀█████▀▄████▄ ██▄▄ ▀█████▀ ▀████▀███▄ ▀█████▀██▄   ▄██▄\n");
    }

    void consoleClear() {  
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
}