import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ShopItemGroup extends ShopEntity{
    public static ShopItemGroup[] values = castArray(Utils.load(ShopItemGroup.class, "resources/objects/shop_groups.json"));

    private ShopItem[] items;

    public ShopItemGroup(JSONObject o){
        super(o);
        
        JSONArray arr = (JSONArray)o.get("items");
        this.items = new ShopItem[arr.size()];
        for(int i = 0; i <  arr.size(); i++){
            this.items[i] = ShopItem.get((String)arr.get(i));
        }
        if(this.name == null) this.name = this.items[0].name;
    }

    public int size(){
        return items.length;
    }

    public ShopItem get(int i){
        return items[i];
    }

    public String shopMessageID(){
        return "buy_" + getID();
    }
    
    private static ShopItemGroup[] castArray(Object[] objs){
        return Arrays.stream(objs).toArray(ShopItemGroup[]::new);
    }
}
