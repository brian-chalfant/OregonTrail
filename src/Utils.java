import java.util.ArrayList;

import org.json.simple.JSONArray;

public class Utils {
    public static ArrayList<String> castList(JSONArray list){
        ArrayList<String> out = new ArrayList<>();
        for(Object obj: list){
            out.add(obj.toString());
        }
        return out;
    }
}
