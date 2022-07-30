import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {


    public static void main(String[] args){

        try {
            int userInput;
            do {
                Utils.clearScreen();
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                //Utils.println("starting_menu",1,2,3,4, Game.isSoundOn()? "Off": "On",5,6);
                JSONArray menu = Settings.getArray("starting_menu");
                for(int i = 0; i < menu.size(); i++) {
                    Utils.println("display_shop_item", i + 1, menu.get(i), "");
                }
                    userInput = Integer.parseInt(reader.readLine());

                switch(userInput){
                    case(1):
                        Game.play();
                        break;
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
                        Game.setSoundOn(!Game.isSoundOn());
                        if(Game.isSoundOn()){
                            System.out.println("Sound is now on");
                        } else{
                            System.out.println("Sound is now off");
                        }
                        break;
                    case(5):
                        //Choose Management Options
                        managementMenu();
                        break;
                    case(6):
                        // Exit the game
                        //System.exit(0);
                }
            } while (userInput!=6);
        } catch (IOException e) {
            System.out.println("please enter a valid number");
        }}

        public static void learnAboutTrail(){
            JSONArray arr = Settings.getArray("learn_about_trail");
            for (Object o : arr) {
                Utils.println((String) "display_line_item", o);
            }
            Keyboard.nextLine();
    }

    public static void oregonTopTen(){
        JSONArray topTen = Settings.getArray("oregon_top_ten");
        for(int i = 0; i < topTen.size(); i++){
            Utils.println("display_shop_item", i + 1, topTen.get(i), "");
        }
        Keyboard.nextLine();


    }

    public static void managementMenu() {
        try {
            int userInput;
            do {
                Utils.clearScreen();
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                JSONArray mgtmenu = Settings.getArray("management_menu");
                for(int i = 0; i < mgtmenu.size(); i++) {
                    Utils.println("display_shop_item", i + 1, mgtmenu.get(i), "");
                }
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
        Keyboard.nextLine();
    }

    private static void turnJoystickOn() {
        System.out.println("Joystick Functional");
        Keyboard.nextLine();
    }

    private static void eraseSavedGames() {
        System.out.println("Saved Games Erased");
        Keyboard.nextLine();
    }

    private static void eraseTombstone() {
        System.out.println("Tombstone Messages Reset");
        Keyboard.nextLine();
    }

    private static void eraseCurrent() {
        oregonTopTen();

    }

    private static void originalTopTen() {
        oregonTopTen();

    }

    private static void currentTopTen() {
        oregonTopTen();

    }

}
