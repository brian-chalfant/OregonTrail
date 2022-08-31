import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utils {
    public static JSONParser parser = new JSONParser();
    public static TextManager text = new TextManager("resources/text");

    public static ArrayList<String> castList(JSONArray list){
        ArrayList<String> out = new ArrayList<>();
        for (Object obj : list) {
            out.add(obj.toString());
        }
        return out;
    }

    public static <T> int choiceIndex(T[] args){
        for(int i=0; i<args.length;i++){
            text.println("choice", i+1, args[i]);
        }
        text.print("select_choice");
        return Keyboard.nextInt() - 1;
    }

    public static <T> T choice(T[] args){
        return args[choiceIndex(args)];
    }

    public static <T,R> T choice(HashMap<T, R> map){
        ArrayList<T> list = new ArrayList<>();
        int count = 0;
        for (Map.Entry<T,R> me: map.entrySet()){
            text.println("choice", count+1, me.getValue());
            list.add(me.getKey());
            count++;
        }
        text.print("select_choice");
        int playerChoice = Keyboard.nextInt();
        return list.get(playerChoice-1);
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

    public static double castDouble(Object o){
        return (Double)o;
    }

    public static <T> Object[] load(Class<T> clazz, String path){
        JSONArray jsonArray = readJSONArray(new File(path));
        Object[] values = new Object[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++){
            values[i] = parse(clazz, ((JSONObject)jsonArray.get(i)));
        }
        return values;
    }

    public static String[] loadLines(String path, boolean lowercase){
        ArrayList<String> out = new ArrayList<>();
        Scanner scan = null;
        try {
            scan = new Scanner(new File(path));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                if(lowercase) line = line.toLowerCase();
                out.add(line);
            }
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            if(scan != null) scan.close();
        }

        return out.toArray(new String[0]);
    }

    public static String[] loadLines(String path){
        return loadLines(path, false);
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
        if(clazz == ShopItem.class) return new ShopItem(o);
        if(clazz == ShopItemGroup.class) return new ShopItemGroup(o);
        System.err.println("Failed to parse: " + clazz);
        return null;
    }

    public static HashMap<String, ArrayList<String>> readCsvCols(String path){
        HashMap<String, ArrayList<String>> out = new HashMap<>();
        Scanner scan = null;
        try {
            scan = new Scanner(new File(path));
            String[] headers = scan.nextLine().split(",");
            for(String header: headers){
                out.put(header, new ArrayList<>());
            }

            while(scan.hasNextLine()){
                String[] tokens = scan.nextLine().split(",");
                for(int i = 0; i < tokens.length; i++){
                    out.get(headers[i]).add(tokens[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(scan != null) scan.close();
        }
        return out;
    }

    public static ArrayList<HashMap<String, String>> readCsvRows(String path){
        ArrayList<HashMap<String, String>> out = new ArrayList<>();
        Scanner scan = null;
        try {
            scan = new Scanner(new File(path));
            String[] headers = scan.nextLine().split(",");
            while(scan.hasNextLine()){
                String[] tokens = scan.nextLine().split(",");
                HashMap<String, String> row = new HashMap<>();
                for(int i = 0; i < tokens.length; i++){
                    row.put(headers[i], tokens[i]);
                }
                out.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(scan != null) scan.close();
        }
        return out;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int promptln(boolean clearScreen){

        int value = Keyboard.nextInt();
        if(clearScreen) Utils.clearScreen();
        return value;
    }

    public static double lerp(double newValue, double oldValue, double ratio){
        return ratio * newValue + (1.0 - ratio) * oldValue;
    }

    public static int indexOf(Object[] array, Object t){
        for(int i = 0; i < array.length; i++) if(array[i].equals(t)) return i;
        return -1;
    }
}