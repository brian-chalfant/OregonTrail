public class ShopEntity {
    public String name;

    public ShopEntity(String name) {
        this.name = name;
    }

    public String shopMessageID(){
        return "buy_" + name.toLowerCase().replaceAll(" ", "_");
    }
}
