
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

    private static void TravelLoop(Player player, Landmark current, Landmark destination){
        int startingMilesTraveled = player.getMilesTraveled();
        int currentTravelMiles = 0;
        int distanceToDestination = current.points.get(destination.getId());
        int travelDelay = Settings.getInt("travel_delay");
        int travelDistance = Settings.getInt("travel_distance");
        while(currentTravelMiles < distanceToDestination) {
                //destination.p1Miles + startingMilesTraveled)){

            //travel the road
            Utils.println("travel", travelDistance);

            try {
                Thread.sleep(travelDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentTravelMiles += travelDistance;
            player.setMilesTraveled(player.getMilesTraveled() + travelDistance);
        }
        Utils.println("arrived", destination.name);
    }

    private static void GameLoop(Player player){
        Landmark current = TRAILS.get(1);
        Landmark next;
            while(current.id <= 17){
                //determine next destination
                if(current.hasFork()){
                    //there are multiple options

                    //gather data from Current Landmark Options
                    ArrayList<Integer> distance = new ArrayList<>();
                    ArrayList<String> placeNames = new ArrayList<>();
                    HashMap<Integer, String> destinations = new HashMap<>();
                    current.points.forEach((key, value) -> {
                        Landmark lm = TRAILS.get(key);
                        distance.add(value);
                        placeNames.add(lm.name);
                        destinations.put(key, lm.name);
                    });

                    //display fork message
                    Utils.println("fork", placeNames.get(0), distance.get(0), placeNames.get(1), distance.get(1));

                    //get user choice and set destination landmark
                    next = TRAILS.get(Utils.choice(destinations));
                } else {
                    //no option set next destination
                    next = TRAILS.get(current.FirstFork());
                }
                TravelLoop(player, current, next);
                System.out.println("Total Miles Traveled: " + player.getMilesTraveled());
                current = next;
            }

        Utils.println("congrats");
    }


}

