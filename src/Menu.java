import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {


    public static void startingMenu(Player player){

        try {
            int userInput;
            do {
                Utils.clearScreen();
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Utils.println("starting_menu");
                userInput = Integer.parseInt(reader.readLine());
                switch(userInput){
                    case(2):
                        //Learn about the trail
                        learnAboutTrail();
                        break;
                    case(3):
                        //See the top 10
                        oregonTopTen();
                        break;
                    case(4):
                        //Turn sound off
                        player.setSoundOn(!player.getSoundOn());
                        if(player.getSoundOn()){
                            System.out.println("Sound is now on");
                        } else{
                            System.out.println("Sound is now off");
                        }
                        break;
                    case(5):
                        //Choose Managment Options
                        managementMenu();
                        break;
                    case(6):
                        // Exit the game
                        System.exit(0);
                }
            } while (userInput!=1);
        } catch (IOException e) {
            System.out.println("please enter a valid number");

        }

    }

    public static void learnAboutTrail(){
        Scanner scanner = new Scanner(System.in);
        Utils.clearScreen();
        Utils.println("learn_about_trail");
        System.out.println("Press Enter to return to the menu..");
        scanner.nextLine();
    }

    public static void oregonTopTen(){
        //TODO: print top ten
        Utils.println("oregon_top_ten");


    }

    public static void managementMenu() {
        try {
            int userInput;
            do {
                Utils.clearScreen();
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Utils.println("management_menu");
                userInput = Integer.parseInt(reader.readLine());
                switch(userInput) {
                    case (1):
                        currentTopTen();
                        break;
                    case (2):
                        originalTopTen();
                        break;
                    case(3):
                        eraseCurrent();
                        break;
                    case(4):
                        eraseTombstone();
                        break;
                    case(5):
                        eraseSavedGames();
                        break;
                    case(6):
                        turnJoystickOn();
                        break;
                    case(7):
                        calibrateJoystick();
                        break;

                }




            } while (userInput != 8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void calibrateJoystick() {
        System.out.println("Joystick Calibrated");
    }

    private static void turnJoystickOn() {
        System.out.println("Joystick Functional");
    }

    private static void eraseSavedGames() {
        System.out.println("Saved Games Erased");
    }

    private static void eraseTombstone() {
        System.out.println("Tombstone Messages Reset");
    }

    private static void eraseCurrent() {
        System.out.println("Top Ten List Rest");
    }

    private static void originalTopTen() {
        Utils.println("oregon_top_ten");
    }

    private static void currentTopTen() {
        Utils.println("oregon_top_ten");
    }

}