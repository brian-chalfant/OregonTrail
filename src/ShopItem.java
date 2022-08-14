import java.util.Arrays;

import org.json.simple.JSONObject;

public class ShopItem extends ShopEntity{

    public static ShopItem[] values = castArray(Utils.load(ShopItem.class, "resources/objects/shop_items.json"));

    public int price;
    public int quantity;
    public int max_quantity;

    public ShopItem(JSONObject o){
        super(o);
        
        this.price = Utils.castInt(o.get("price"));
        this.quantity = Utils.castInt(o.get("quantity"));
        
        Object max_quantity = o.get("max_quantity");
        this.max_quantity = max_quantity == null ? Integer.MAX_VALUE / 2 : Utils.castInt(max_quantity);

    }

    public static ShopItem get(String id){
        for(ShopItem item: values){
            if(item.getID().equals(id))
                return item;
        }
        return null;
    }

    private static ShopItem[] castArray(Object[] objs){
        return Arrays.stream(objs).toArray(ShopItem[]::new);
    }
}
