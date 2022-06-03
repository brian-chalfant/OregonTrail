public class Player {

    private String name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    private Member member2 = new Member();
    private Member member3 = new Member();
    private Member member4 = new Member();
    private Member member5 = new Member();

    public Member[] getMembers() {
        return members;
    }

    private Member[] members = new Member[]{member2, member3, member4, member5};
    private Occupation occupation;
    private double money;
    private int oxenAmount;
    private int clothingAmount;
    private int ammoAmount;
    private int[] sparePartsAmount = new int[]{0,0,0};
    private int foodAmount;
    private int pace;
    private int rations;

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    private String startingDate;

    public Player(String name, Occupation occupation) {
        this.name = name;
        this.occupation = occupation;
        this.money = occupation.getStartingCash();

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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getOxenAmount() {
        return oxenAmount;
    }

    public void setOxenAmount(int oxenAmount) {
        this.oxenAmount = oxenAmount;
    }

    public int getClothingAmount() {
        return clothingAmount;
    }

    public void setClothingAmount(int clothingAmount) {
        this.clothingAmount = clothingAmount;
    }

    public int getAmmoAmount() {
        return ammoAmount;
    }

    public void setAmmoAmount(int ammoAmount) {
        this.ammoAmount = ammoAmount;
    }

    public int getSparePartsAmount() {
        return sparePartsAmount[0] + sparePartsAmount[1] + sparePartsAmount[2];
    }
    public int[] getSpareParts(){
        return sparePartsAmount;
    }

    public void setSparePartsAmount(int[] sparePartsAmount) {
        this.sparePartsAmount = sparePartsAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
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