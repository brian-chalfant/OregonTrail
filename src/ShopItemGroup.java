public class ShopItemGroup extends ShopEntity{
    private ShopItem[] items;

    public ShopItemGroup(String name, ShopItem ...items) {
        super(name);
        this.items = items;
    }

    public ShopItemGroup(String name, ItemEnum id, int price, int quantity) {
        super(name);
        this.items = new ShopItem[1];
        this.items[0] = new ShopItem(name, id, price, quantity);
    }

    public int size(){
        return items.length;
    }

    public ShopItem get(int i){
        return items[i];
    }
}
