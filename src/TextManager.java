import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TextManager {
    private HashMap<String, String> data = new HashMap<>();
    
    public TextManager(String jsonDirectory){
        JSONParser parser = new JSONParser();
        File jsonDir = new File(jsonDirectory);
        
        for(File jsonFile: jsonDir.listFiles()){
            JSONObject obj = null;
            try {
                obj = (JSONObject)parser.parse(new FileReader(jsonFile));
            } catch (IOException | ParseException e) {
                System.err.println("Failed to load json file: " + jsonFile.getAbsolutePath());
                e.printStackTrace();
                continue;
            }

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
