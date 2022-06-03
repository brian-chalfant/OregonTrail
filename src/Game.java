import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Prompt Player Name
        System.out.print("Enter Player Name: ");
        String name = scanner.nextLine();

        //Prompt Occupation
        System.out.println("Choose an Occupation: ");
        Occupation occ = choice(new Occupation[]{Occupation.BANKER, Occupation.CARPENTER, Occupation.FARMER});

        //Initialize player object
        Player player = new Player(name, occ);

        //Prompt party names
        System.out.println("Please enter the names of your four party members");

        for(Member i : player.getMembers()){
            System.out.print(": ");
            String memberName = scanner.nextLine();
            i.setName(memberName);
        }

        //Prompt starting date
        System.out.println("it is 1848.  Your jumping off place for Oregon is Independence, Missouri.\n" +
                "You must decide which month to leave Independence.");
        player.setStartingDate(choice(new String[]{"March", "April", "May", "June", "July"}));

        System.out.println("      Hello, I'm Matt. So you're going to Oregon! I can fix you up with what you need.\n" +
                "        - A team of oxen to pull your wagon\n" +
                "        - clothing for both summer and winter\n" +
                "        - plenty of food for the trip\n" +
                "        - ammunition for your rifles\n" +
                "        - spare parts for your wagon");
        Store(player);


    }


    public static <T> T choice(T[] args){
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<args.length;i++){
            System.out.println(i+1 + ": " + args[i]);
        }
        System.out.print("Enter your choice: ");
        int playerChoice = scanner.nextInt();
        scanner.nextLine();
        return args[playerChoice-1];



    }

    private static void Store(Player player){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int userInput;
        boolean purchaseCompeted = false;
        Double bill = 0.00;
        int oxenAmount = 0;
        Double foodAmount = 0.00;
        int clothingAmount = 0;
        int ammoAmount = 0;
        int wagonWheelAmount = 0;
        int wagonAxleAmount = 0;
        int wagonTongueAmount = 0;
        int sparePartsAmount = wagonAxleAmount + wagonTongueAmount + wagonWheelAmount;
        while(!purchaseCompeted){
            try {
                System.out.println("1. Oxen: $" + oxenAmount * 30 + ".00");
                System.out.println("2. Food: $" + foodAmount * .20);
                System.out.println("3. Clothing: $" + clothingAmount * 10 + ".00");
                System.out.println("4. Ammunition: $" + ammoAmount * 30 + ".00");
                System.out.println("5. SpareParts: $" + sparePartsAmount * 10 + ".00");
                System.out.println("----------------------------------------------");
                System.out.println("What would you like to buy?");
                userInput = Integer.parseInt(reader.readLine());
                switch(userInput){
                    case 1:
                        oxenAmount += buyOxen();
                        break;
                    case 2:
                        buyFood();
                        break;
                    case 3:
                        buyClothing();
                        break;
                    case 4:
                        buyAmmunition();
                        break;
                    case 5:
                        buySpareParts();
                        break;
                }
            } catch (IOException e){
                System.out.println("Sorry what was that sonny boy?");
            }


        }


    }

    private static void buySpareParts() {

    }

    private static void buyAmmunition() {

    }

    private static void buyClothing() {

    }

    private static void buyFood() {

    }

    private static int buyOxen() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*Buy Oxen*");
        System.out.println("There are 2 Oxen in a Yoke");
        System.out.println("I recommend at least 3 Yoke");
        System.out.println("I charge $30 a yoke.");
        System.out.println("");
        System.out.println("How many would you like?");
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }


    }


}
