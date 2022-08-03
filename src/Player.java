import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player extends Member{

    private Member[] members = new Member[4];
    private Occupation occupation;
    private int money;
    
    private HashMap<ShopItem, Integer> inventory = new HashMap<>();

    private int pace;
    private int rations;
    private Month startingDate;
    private int milesTraveled;
    private ArrayList<Entity> oxen = new ArrayList<>();

    private final double TRAVEL_MULTIPLIER;
    private final double TRAVEL_CONSTANT;

    public Player(String name, Occupation occupation) {
        this.setName(name);
        this.occupation = occupation;
        this.money = occupation.getStartingCash();
        this.pace = Settings.getInt("starting_pace");
        
        for(int i = 0; i < members.length; i++){
            members[i] = new Member();
        }

        TRAVEL_MULTIPLIER = Settings.getDouble("travel_multiplier");
        TRAVEL_CONSTANT = Settings.getDouble("travel_constant");
    }

    public Member[] getMembers() {
        return members;
    }

    public int getMilesTraveled() {
        return milesTraveled;
    }

    public void setMilesTraveled(int milesTraveled) {
        this.milesTraveled = milesTraveled;
    }

    public Month getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Month startingDate) {
        this.startingDate = startingDate;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void applyPurchases(HashMap<ShopItem, Integer> purchases){
        for (Map.Entry<ShopItem,Integer> p : purchases.entrySet()) {
            int amount = inventory.getOrDefault(p.getKey(), 0) + p.getValue();
            inventory.put(p.getKey(), amount);
        }

        int newOxenCount = inventory.getOrDefault(ShopItem.get("oxen"), 0);
        for(int i = 0; i < newOxenCount; i++){
            oxen.add(new Entity());
        }
    }

    public int getInventory(ShopItem item){
        return inventory.getOrDefault(item, 0);
    }

    public int getPace() {
        return pace;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public int getRations() {
        return rations;
    }

    public void setRations(int rations) {
        this.rations = rations;
    }

    public int getAliveOxenCount(){
        int count = 0;
        for(Entity e: oxen) count += e.isAlive() ? 1: 0;
        return count;
    }

    public int getTotalOxenHealth(){
        int health = 0;
        for(Entity e: oxen) health += e.health;
        return health;
    }

    public int getTravelDistance(){
        double distance_per_pace = TRAVEL_MULTIPLIER * Math.log(getTotalOxenHealth()) + TRAVEL_CONSTANT;
        double max_distance = pace * Math.max(distance_per_pace, 1);
        return (int)max_distance;
    }
}