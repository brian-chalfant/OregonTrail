import java.io.File;
import java.util.HashMap;

import org.json.simple.JSONArray;

public class TextManager {
    private HashMap<String, String> data = new HashMap<>();
    
    public TextManager(String jsonDirectory){
        File jsonDir = new File(jsonDirectory);
        
        for(File jsonFile: jsonDir.listFiles()){
            HashMap<String, Object> obj = Utils.readJSON(jsonFile);
            for(Object key : obj.keySet()){
                data.put((String)key, String.join("\n", Utils.castList((JSONArray)obj.get(key))));
            }
        }
    }

    public String get(String key){
        String value = data.get(key);
        if (value == null) System.err.println("Error: cannot find text '" + key + "'");
        return value;
    }

    public void print(String key, Object... args){
        System.out.print(String.format(get(key), args));
    }

    public void println(String key, Object... args){
        System.out.println(String.format(get(key), args));
    }
}
