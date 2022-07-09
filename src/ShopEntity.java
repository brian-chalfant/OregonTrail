import org.json.simple.JSONObject;

public class ShopEntity {
    public String name;

    public ShopEntity(JSONObject o) {
        this.name = (String)o.get("name");
    }

    public String getID(){
        return this.name.toLowerCase().replaceAll(" ", "_");
    }
}
