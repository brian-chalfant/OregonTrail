import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Game {
    static LinkedList<Landmark> TRAILS = new LinkedList<>(); //Should call BuildTrail

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

        System.out.println("Now Loading the Wagon");
        GameLoop(player);



    }

    private static void TravelLoop(Player player, Landmark destination){
        while(player.getMilesTraveled() < destination.distance){
            //travel the road
            System.out.println("Traveling 20 miles...");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.setMilesTraveled(player.getMilesTraveled() + 20);
        }
        System.out.println("Arrived at " + destination.name);
    }

    private static void GameLoop(Player player){
        Iterator i = TRAILS.iterator();
        while(i.hasNext()) {
            TravelLoop(player, (Landmark) i.next());
        }
        System.out.println("Congratulations!");
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

    private static String displayMoney(int cents){
        int absCents = Math.abs(cents);
        return String.format((cents >= 0 ? "" : "-") + "$%d.%02d", absCents / 100, absCents % 100);
    }

    private static void Store(Player player){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int userInput;
        boolean purchaseCompeted = false;
        int bill = 0;
        int oxenAmount = 0;
        int foodAmount = 0;
        int clothingAmount = 0;
        int ammoAmount = 0;
        //int wagonWheelAmount = 0;
        //int wagonAxleAmount = 0;
        //int wagonTongueAmount = 0;
        int[] sparePartsAmount = new int[3];
        while(!purchaseCompeted){
            try {
                System.out.println("1. Oxen: " + displayMoney(oxenAmount * 3000));
                System.out.println("2. Food: " + displayMoney(foodAmount * 20));
                System.out.println("3. Clothing: " + displayMoney(clothingAmount * 1000));
                System.out.println("4. Ammunition: " + displayMoney(ammoAmount * 200));
                System.out.println("5. SpareParts: " + displayMoney(IntStream.of(sparePartsAmount).sum() * 1000));
                System.out.println("----------------------------------------------");
                System.out.println("Your current bill is: " + displayMoney(bill));
                System.out.println("You have: " + displayMoney(player.getMoney()));
                System.out.println("What would you like to buy?");
                try{
                    userInput = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e){
                    userInput = 0;
                }

                switch(userInput){
                    case 1:
                        int newOxen = buyOxen(bill);
                        oxenAmount += newOxen;
                        bill += newOxen * 3000;
                        break;
                    case 2:
                        int newFood = buyFood(bill);
                        foodAmount += newFood;
                        bill += newFood * 20;
                        break;
                    case 3:
                        int newClothing = buyClothing(bill);
                        clothingAmount += newClothing;
                        bill += newClothing * 1000;
                        break;
                    case 4:
                        int newAmmo = buyAmmunition(bill);
                        ammoAmount += newAmmo;
                        bill += newAmmo * 200;
                        break;
                    case 5:
                        int[] newParts = buySpareParts(bill);
                        for(int i = 0; i < sparePartsAmount.length; i++){
                            sparePartsAmount[i] += newParts[i];
                        }
                        bill += IntStream.of(sparePartsAmount).sum() * 1000;
                        break;
                    default:
                        if(bill > player.getMoney()){
                            System.out.println("Sorry, you can't afford all of that");
                        } else {
                            player.setMoney(player.getMoney() - bill);
                            player.setOxenAmount(player.getOxenAmount() + oxenAmount);
                            player.setFoodAmount(player.getFoodAmount() + foodAmount);
                            player.setClothingAmount(player.getClothingAmount() + clothingAmount);
                            player.setAmmoAmount(player.getAmmoAmount() + ammoAmount);
                            for(int i = 0;i<3;i++){
                                player.getSpareParts()[i] += sparePartsAmount[i];
                            }
                            bill = 0;
                            purchaseCompeted = true;
                        }
                }

            } catch (IOException e){
                System.out.println("Sorry what was that sonny boy?");
            }
        }
    }

    private static int[] buySpareParts(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] spareParts = new String[]{"wagon wheels", "wagon axle", "wagon tongue"};
        int[] numberPurchased = new int[]{0,0,0};
        System.out.println("*Buy Spare Parts*");
        System.out.println("Bill so far: " + displayMoney(bill));
        System.out.println("It's a good idea to have a few");
        System.out.println("spare parts for your wagon");
        System.out.println("Here are the prices:");
        System.out.println("");
        System.out.println("wagon wheel - $10 each");
        System.out.println("wagon axle - $10 each");
        System.out.println("wagon tongue - $10 each");
        try{
            for(int i = 0;i<spareParts.length;i++){
                System.out.println("How many " + spareParts[i]+"?: ");
                numberPurchased[i]= Integer.parseInt(reader.readLine());
            }
            return numberPurchased;
        } catch (IOException e) {
            return new int[]{0,0,0};
        }
    }

    private static int buyAmmunition(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*Buy Ammo*");
        System.out.println("Bill so far: " + displayMoney(bill));
        System.out.println("I sell ammunition in boxes of 20");
        System.out.println("bullets. Each box costs $2.00.");
        System.out.println("");
        System.out.println("How many boxes do");
        System.out.println("you want? ");
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }
    }

    private static int buyClothing(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*Buy Clothes*");
        System.out.println("Bill so far: " + displayMoney(bill));
        System.out.println("You'll need warm clothing in the");
        System.out.println("mountains. I recommend taking at");
        System.out.println("least 2 sets of clothes per person.");
        System.out.println("Each set is $10.00.");
        System.out.println("");
        System.out.println("How many sets of clothes do");
        System.out.println("you want? ");
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }
    }

    private static int buyFood(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*Buy Food*");
        System.out.println("Bill so far: " + displayMoney(bill));
        System.out.println("I recommend that you take at least");
        System.out.println("200 pounds of food for each person");
        System.out.println("in your family.  I see that you have");
        System.out.println("5 people in all.  You'll need flour,");
        System.out.println("sugar, bacon, and coffee.  My price");
        System.out.println("is 20 cents a pound.");
        System.out.println("");
        System.out.println("How many pounds of food do");
        System.out.println("you want? ");
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }

    }

    private static int buyOxen(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*Buy Oxen*");
        System.out.println("Bill so far: " + displayMoney(bill));
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

/*    private static LinkedList<Landmark> buildTrail(){

    }*/


}

