
import java.util.Iterator;
import java.util.LinkedList;

public class Game {
    static LinkedList<Landmark> TRAILS = buildTrail();

    public static void main(String[] args) {

        //Prompt Player Name
        Utils.print("player_name");
        String name = Keyboard.nextLine();

        //Prompt Occupation
        Utils.println("occupation");
        Occupation occ = Utils.choice(new Occupation[]{Occupation.BANKER, Occupation.CARPENTER, Occupation.FARMER});

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
        player.setStartingDate(Utils.choice(new String[]{"March", "April", "May", "June", "July"}));

        Utils.println("shop_intro");
        Shop.run(player);

        Utils.println("loading_wagon");
        GameLoop(player);

        Keyboard.close();
    }

    private static void TravelLoop(Player player, Landmark destination){
        while(player.getMilesTraveled() < destination.distance){
            //travel the road
            Utils.println("travel");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            player.setMilesTraveled(player.getMilesTraveled() + 20);
        }
        Utils.println("arrived", destination.name);
    }

    private static void GameLoop(Player player){
        Iterator i = TRAILS.iterator();
        while(i.hasNext()) {
            TravelLoop(player, (Landmark) i.next());
        }
        Utils.println("congrats");
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

