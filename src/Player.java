import java.util.HashMap;
import java.util.Map;

public class Player {

    private String name;
    private Member[] members = new Member[4];
    private Occupation occupation;
    private int money;
    
    private HashMap<ShopItem, Integer> inventory = new HashMap<>();

    private int pace;
    private int rations;
    private Month startingDate;
    private String status;
    private int milesTraveled;

    public Player(String name, Occupation occupation) {
        this.name = name;
        this.occupation = occupation;
        this.money = occupation.getStartingCash();

        for(int i = 0; i < members.length; i++){
            members[i] = new Member();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}