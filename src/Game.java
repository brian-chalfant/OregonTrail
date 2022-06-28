import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Game {
    static LinkedList<Landmark> TRAILS = buildTrail();
    static TextManager text = new TextManager("resources/text");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        

        //Prompt Player Name
        text.print("player_name");
        String name = scanner.nextLine();

        //Prompt Occupation
        text.println("occupation");
        Occupation occ = choice(new Occupation[]{Occupation.BANKER, Occupation.CARPENTER, Occupation.FARMER});

        //Initialize player object
        Player player = new Player(name, occ);

        //Prompt party names
        text.println("family_members");

        for(Member i : player.getMembers()){
            text.print("family_member");
            String memberName = scanner.nextLine();
            i.setName(memberName);
        }

        //Prompt starting date
        text.println("select_month");
        player.setStartingDate(choice(new String[]{"March", "April", "May", "June", "July"}));

        text.println("shop_intro");
        Store(player);

        text.println("loading_wagon");
        GameLoop(player);
    }

    private static void TravelLoop(Player player, Landmark destination){
        while(player.getMilesTraveled() < destination.distance){
            //travel the road
            text.println("travel");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.setMilesTraveled(player.getMilesTraveled() + 20);
        }
        text.println("arrived", destination.name);
    }

    private static void GameLoop(Player player){
        Iterator i = TRAILS.iterator();
        while(i.hasNext()) {
            TravelLoop(player, (Landmark) i.next());
        }
        text.println("congrats");
    }


    public static <T> T choice(T[] args){
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<args.length;i++){
            text.println("choice", i+1, args[i]);
        }
        text.print("select_choice");
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
                text.println("display_shop",
                    displayMoney(oxenAmount * 3000),
                    displayMoney(foodAmount * 20),
                    displayMoney(clothingAmount * 1000),
                    displayMoney(ammoAmount * 200),
                    displayMoney(IntStream.of(sparePartsAmount).sum() * 1000),
                    displayMoney(bill),
                    displayMoney(player.getMoney())
                );
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
                            text.println("overbudget");
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
                text.println("invalid_shop_input");
            }
        }
    }

    private static int[] buySpareParts(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] spareParts = new String[]{"wagon wheels", "wagon axle", "wagon tongue"};
        int[] numberPurchased = new int[]{0,0,0};
        text.println("buy_spare_parts", displayMoney(bill));
        try{
            for(int i = 0;i<spareParts.length;i++){
                text.println("buy_single_spare_part", spareParts[i]);
                numberPurchased[i]= Integer.parseInt(reader.readLine());
            }
            return numberPurchased;
        } catch (IOException e) {
            return new int[]{0,0,0};
        }
    }

    private static int buyAmmunition(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text.println("buy_ammo", displayMoney(bill));
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }
    }

    private static int buyClothing(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text.println("buy_clothing", displayMoney(bill));
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }
    }

    private static int buyFood(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text.println("buy_food", displayMoney(bill));
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }

    }

    private static int buyOxen(int bill) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text.println("buy_oxen", displayMoney(bill));
        try{
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }


    }

    private static LinkedList<Landmark> buildTrail(){
        LinkedList<Landmark> trails = new LinkedList<>();
        Landmark kearny = new Landmark("Fort Kearny, Nebraska", true, 319);
        trails.addFirst(kearny);
        Landmark chimney = new Landmark("Chimney Rock, Nebraska", false, 504);
        trails.add(chimney);
        Landmark laramie = new Landmark("Fort Laramie, Wyoming", true, 750);
        trails.add(laramie);
        return trails;
    }


}

