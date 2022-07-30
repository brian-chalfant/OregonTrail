import org.json.simple.JSONArray;

import java.io.File;
import java.util.HashMap;

public class Settings {
    private static Settings settings = new Settings("resources/game_data");
    private HashMap<String, Object> data = new HashMap<>();

    public Settings(String jsonDirectory){
        File jsonDir = new File(jsonDirectory);
        
        for(File jsonFile: jsonDir.listFiles()){
            HashMap<String, Object> obj = Utils.readJSON(jsonFile);
            for(Object key : obj.keySet()){
                data.put((String)key, obj.get(key));
            }
        }
    }

    public static String getString(String key){
        return (String)settings.data.getOrDefault(key, null);
    }

    public static int getInt(String key){
        return Utils.castInt(settings.data.get(key));
    }

    public static double getDouble(String key){
        return Utils.castDouble(settings.data.get(key));
    }

    public static JSONArray getArray(String key){
        return (JSONArray)settings.data.getOrDefault(key, null);
    }
}
