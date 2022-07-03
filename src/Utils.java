import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utils {
    public static JSONParser parser = new JSONParser();
    public static TextManager text = new TextManager("resources/text");

    public static ArrayList<String> castList(JSONArray list){
        ArrayList<String> out = new ArrayList<>();
        for(Object obj: list){
            out.add(obj.toString());
        }
        return out;
    }

    public static <T> T choice(T[] args){
        for(int i=0; i<args.length;i++){
            text.println("choice", i+1, args[i]);
        }
        text.print("select_choice");
        int playerChoice = Keyboard.nextInt();
        return args[playerChoice-1];
    }

    public static void print(String key, Object ...args){
        text.print(key, args);
    }

    public static void println(String key, Object ...args){
        text.println(key, args);
    }

    public static int castInt(Object o){
        return ((Long)o).intValue();
    }

    public static <T> Object[] load(Class<T> clazz, String path){
        JSONArray jsonArray = readJSONArray(new File(path));
        Object[] values = new Object[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++){
            values[i] = parse(clazz, ((JSONObject)jsonArray.get(i)));
        }
        return values;
    }

    public static HashMap<String, Object> readJSON(File file){
        HashMap<String, Object> data = new HashMap<>();
        JSONObject obj = (JSONObject)parseJSON(file);
        for(Object key : obj.keySet()){
            data.put((String)key, obj.get(key));
        }
        return data;
    }

    public static JSONArray readJSONArray(File file){
        return (JSONArray)parseJSON(file);
    }

    private static Object parseJSON(File file){
        try {
            return parser.parse(new FileReader(file));
        } catch (IOException | ParseException e) {
            System.err.println("Failed to load json file: " + file.getAbsolutePath());
            e.printStackTrace();
        }
        return null;
    }

    private static <T> Object parse(Class<T> clazz, JSONObject o){
        if(clazz == Occupation.class) return new Occupation(o);
        if(clazz == Month.class) return new Month(o);
        if(clazz == ShopItem.class) return new ShopItem(o);
        if(clazz == ShopItemGroup.class) return new ShopItemGroup(o);
        System.err.println("Failed to parse: " + clazz);
        return null;
    }
}