
import java.util.*;

public class Game {
    static HashMap<Integer, Landmark> TRAILS = LandmarkParser.parseLandmarks();


    public static void main(String[] args) {

        //Prompt Player Name
        Utils.print("player_name");
        String name = Keyboard.nextLine();

        //Prompt Occupation
        Utils.println("occupation");
        Occupation occ = Utils.choice(Occupation.values);

        //Initialize player object
        Player player = new Player(name, occ);

        //Prompt party names
        Utils.println("family_members");

        for(Member i : player.getMembers()){
            Utils.print("family_member");
            String memberName = Keyboard.nextLine();
            i.setName(memberName);
        }

        //Prompt starting date
        Utils.println("select_month");
        player.setStartingDate(Utils.choice(Month.values));

        Utils.println("shop_intro");
        Shop.run(player);

        Utils.println("loading_wagon");
        GameLoop(player);

        Keyboard.close();
    }

    private static void TravelLoop(Player player, Landmark destination){
        int StartingMilesTraveled = player.getMilesTraveled();
        int travelDelay = Settings.getInt("travel_delay");
        int travelDistance = Settings.getInt("travel_distance");
        while(player.getMilesTraveled() < (destination.p1Miles + StartingMilesTraveled)){

            //travel the road
            Utils.println("travel", travelDistance);

            try {
                Thread.sleep(travelDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.setMilesTraveled(player.getMilesTraveled() + travelDistance);
        }
        Utils.println("arrived", destination.name);
    }

    private static void GameLoop(Player player){
        Landmark current = TRAILS.get(0);
            while(current.id != 17){
                Landmark next = TRAILS.get(current.nextPoint1);
                TravelLoop(player, next);
                System.out.println("Total Miles Traveled: " + player.getMilesTraveled());
                current = next;
                if(current.nextPoint2 >= 0){
                    //TODO: query user which point to go to
                    next = TRAILS.get(current.nextPoint2);
                } else {
                    next = TRAILS.get(current.nextPoint1);
                }
            }
/*        Iterator i = TRAILS.iterator();
        while(i.hasNext()) {
            TravelLoop(player, (Landmark) i.next());
        }*/
        Utils.println("congrats");
    }


}

