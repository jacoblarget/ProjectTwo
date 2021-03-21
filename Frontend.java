import java.util.Scanner;

public class Frontend{
    //m(enu) g(eneration), t(ype), n(ame), p(ower), (e)x(it)
    private String mode = "";
    private BackendInterface backend;
    private Scanner in;

    public static void main(String args[]){
        Frontend frontend = new Frontend(new Scanner(System.in), new Backend());
        //Frontend frontend = new Frontend(new Scanner(System.in), new BackendDummy());
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
            mode = in.next();
            switch(mode){
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
            + "would like to select or deselect      | Generation Selection Mode |\n"
            + "x : back to main menu                 '---------------------------'\n");
            mode = in.next();
            switch(mode){
                case "x":
                    isGenMenuRunning = false;
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
            mode = in.next();
            switch(mode){
                case "x":
                    isTypeMenuRunning = false;
                    return;
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
            mode = in.next();
            switch(mode){
                case "x":
                    isNameMenuRunning = false;
                    return;
            }
        }
    }
    
    void runPowerFilterMode(){
        boolean isPowerMenuRunning = true;
        while(isPowerMenuRunning){
            printPokedexTitle();
            System.out.println(
              "Type the index of a power range you        .----------------------.\n"
            + "would like to select or deselect           | Power Filtering Mode |\n"
            + "x : back to main menu                      '----------------------'\n");
            mode = in.next();
            switch(mode){
                case "x":
                    isPowerMenuRunning = false;
                    return;
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
        //System.out.print("\033[H\033[2J");
        //System.out.flush();  
    }  

}