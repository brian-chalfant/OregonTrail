import org.json.simple.JSONArray;


public class Menu {


    public static void main(String[] args){
        Utils.clearScreen();
        int userInput;
        do {
            JSONArray menu = Settings.getArray("starting_menu");
            for(int i = 0; i < menu.size(); i++) {
                Utils.println("display_shop_item", i + 1, menu.get(i), "");
            }
                userInput = Utils.promptln(true);

            switch(userInput){
                case(1):
                    Game.play();
                    userInput = 6; //exit the loop, avoids error with scanner if game loops back to menu.
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
                        Utils.println("sound_on");
                        Keyboard.nextLine(true);
                    } else{
                        Utils.println("sound_off");
                        Keyboard.nextLine(true);
                    }
                    break;
                case(5):
                    //Choose Management Options
                    managementMenu();
                    break;

            }
        } while (userInput!=6);
    }

        public static void learnAboutTrail(){
            JSONArray arr = Settings.getArray("learn_about_trail");
            for (Object o : arr) {
                Utils.println("display_line_item", o);
            }
            Keyboard.nextLine(true);
    }

    public static void oregonTopTen(){
        JSONArray topTen = Settings.getArray("oregon_top_ten");
        for(int i = 0; i < topTen.size(); i++){
            Utils.println("display_shop_item", i + 1, topTen.get(i), "");
        }
        Keyboard.nextLine(true);


    }

    public static void managementMenu() {
        int userInput;
        do {

            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            JSONArray mgtmenu = Settings.getArray("management_menu");
            for(int i = 0; i < mgtmenu.size(); i++) {
                Utils.println("display_shop_item", i + 1, mgtmenu.get(i), "");
            }
            userInput = Utils.promptln(true);
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
    }

    private static void calibrateJoystick() {
        System.out.println("Joystick Calibrated");
        Keyboard.nextLine(true);
    }

    private static void turnJoystickOn() {
        System.out.println("Joystick Functional");
        Keyboard.nextLine(true);
    }

    private static void eraseSavedGames() {
        System.out.println("Saved Games Erased");
        Keyboard.nextLine(true);
    }

    private static void eraseTombstone() {
        System.out.println("Tombstone Messages Reset");
        Keyboard.nextLine(true);
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
