public class Game {
    static LandmarkLinkedList TRAILS = LandmarkParser.parseLandmarks();

    static boolean SOUND_ON = true;



    public static void play() {
        Player player = new Player();
        //Prompt Player Name
        Utils.print("player_name");
        String name = Keyboard.nextLine();
        player.setName(name);
        Utils.clearScreen();

        //Prompt Occupation
        Utils.println("occupation");
        Occupation occ = Utils.choice(Occupation.values);
        player.setOccupation(occ);
        Utils.clearScreen();
        //Initialize player object


        //Prompt party names
        Utils.println("family_members");

        for(Member i : player.getMembers()){
            Utils.print("family_member");
            String memberName = Keyboard.nextLine();
            i.setName(memberName);
        }
        Utils.clearScreen();

        //Prompt starting date
        Utils.println("select_month");
        player.setStartingDate(Utils.choice(Month.values));
        Utils.clearScreen();
        Utils.println("shop_intro");
        Shop.run(player);

        Utils.println("loading_wagon");
        GameLoop(player);

        Keyboard.close();
    }

    private static void TravelLoop(Player player, Node current, Node destination, Destination finalDestination){
        //int startingMilesTraveled = player.getMilesTraveled();  Not used YET
        int currentTravelMiles = 0;
        int distanceToDestination = finalDestination.getDistance();
        int travelDelay = Settings.getInt("travel_delay");
        while(currentTravelMiles < distanceToDestination) {
                //destination.p1Miles + startingMilesTraveled)){
            int remainingDistance = distanceToDestination-currentTravelMiles;
            int travelDistance = player.getTravelDistance();
            //travel the road
            if(remainingDistance > travelDistance){
                Utils.println("travel", travelDistance);
                currentTravelMiles += travelDistance;
                player.setMilesTraveled(player.getMilesTraveled() + travelDistance);
            } else {
                Utils.println("travel", remainingDistance);
                currentTravelMiles += remainingDistance;
                player.setMilesTraveled(player.getMilesTraveled() + remainingDistance);
            }


            try {
                Thread.sleep(travelDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        Utils.println("arrived", finalDestination.getName());
    }

    private static void GameLoop(Player player){
        Node current = TRAILS.head;
        Node next;
            while(current.hasNext()){
                //determine next destination
                Destination finalDestination;
                if(current.altNext() != null){
                    //there are multiple options
                    Destination d1 = current.getData().points[0];
                    Destination d2 = current.getData().points[1];

                    //display fork message
                    Utils.println("fork",d1.getName(),d1.getDistance(), d2.getName(), d2.getDistance());

                    //get user choice and set destination landmark
                    finalDestination = Utils.choice(current.getData().points);
                    if (finalDestination.equals(d1)){
                        next = current.next();
                    } else {
                        next = current.altNext();

                    }
                } else {
                    //no option, set next destination
                    finalDestination = current.getData().points[0];
                    finalDestination.setName(current.next().getData().name);
                    next = current.next();
                }
                TravelLoop(player, current, next, finalDestination );
                System.out.println("Total Miles Traveled: " + player.getMilesTraveled());
                current = next;
            }

        Utils.println("congrats");
    }



    public static boolean isSoundOn() {
        return SOUND_ON;
    }

    public static void setSoundOn(boolean soundOn) {
        SOUND_ON = soundOn;
    }

}

