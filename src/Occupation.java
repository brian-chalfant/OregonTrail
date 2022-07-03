import java.util.Arrays;

import org.json.simple.JSONObject;

public class Occupation {
    
    public static Occupation[] values = castArray(Utils.load(Occupation.class, "resources/objects/occupations.json"));

    private int startingCash;
    private String name;

    public Occupation(JSONObject o){
        this.name = (String)o.get("name");
        this.startingCash = Utils.castInt(o.get("startingCash"));
    }

    public String getName(){
        return name;
    }

    public int getStartingCash() {
        return startingCash;
    }

    public String toString(){
        return name;
    }

    private static Occupation[] castArray(Object[] objs){
        return Arrays.stream(objs).toArray(Occupation[]::new);
    }
}
