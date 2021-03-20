import java.util.Scanner;

public class Frontend{
    public static void main(String args[]){
        run(new Scanner(System.in));
    }

    static void run(Scanner in){
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";

        while(true){
            consoleClear();
            System.out.println(
  "                ▀▄▀▄▀▄ Pokémon™: "+ANSI_RED+"Red"+ANSI_RESET+" & "
+ ANSI_BLACK+"Black"+ANSI_RESET+" ▄█▄▀▄▀\n"
+ "▀███▀▀▀██▄         ▀███                    ▀███\n"
+ " ██   ▀██▄          ██                      ██\n"
+ " ██   ▄██  ▄██▀██▄  ██  ▄██▀  ▄▄█▀██   ▄█▀▀███   ▄▄█▀██▀██▀   ▀██▀\n"
+ " ███████  ██▀   ▀██ ██ ▄█    ▄█▀   ██▄██    ██  ▄█▀   ██ ▀██ ▄█▀\n"
+ " ██       ██     ██ ██▄██    ██▀▀▀▀▀▀███    ██  ██▀▀▀▀▀▀   ███\n"
+ " ██       ██▄   ▄██ ██ ▀██▄  ██▄    ▄▀██    ██  ██▄    ▄ ▄█▀ ██▄\n"
+ "▄████▄      ▀█████▀▄████▄ ██▄▄ ▀█████▀ ▀████▀███▄ ▀█████▀██▄   ▄██▄\n"
+ "g : generation selection mode                         .-----------.\n"
+ "t : type selection mode                               | Base Mode |\n"
+ "n : name search mode                                  '-----------'\n"
+ "p : total power filtering mode\n"
+ "x : exit\n");
            String input = in.next();
            
        }
    }

    static void consoleClear() {  
        System.out.print("\033[H\033[2J");
        System.out.flush();  
    }  
}