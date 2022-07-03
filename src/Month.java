import java.util.Arrays;

import org.json.simple.JSONObject;

public class Month{
    
    public static Month[] values = castArray(Utils.load(Month.class, "resources/objects/months.json"));

    private String name;

    public Month(JSONObject o){
        this.name = (String)o.get("name");
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }

    private static Month[] castArray(Object[] objs){
        return Arrays.stream(objs).toArray(Month[]::new);
    }
}
