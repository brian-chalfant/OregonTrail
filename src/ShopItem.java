public class ShopItem extends ShopEntity{
    public ItemEnum id;
    public int price;
    public int quantity;

    public ShopItem(String name, ItemEnum id, int price, int quantity) {
        super(name);
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }
}
