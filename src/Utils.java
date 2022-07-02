import java.util.ArrayList;

import org.json.simple.JSONArray;

public class Utils {
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
}
