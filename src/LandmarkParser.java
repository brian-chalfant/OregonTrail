
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LandmarkParser {


  public static HashMap<Integer, Landmark> parseLandmarks() {
    HashMap<Integer, Landmark> list = new HashMap<>();
    JSONParser parser = new JSONParser();

    File file = new File("resources/landmarks/landmarks.json");

    JSONObject obj = null;

    try {
      obj = (JSONObject) parser.parse(new FileReader(file));
      for(Object i : obj.values()){
        Landmark lm = new Landmark((JSONObject) i);
        list.put(Integer.parseInt(String.valueOf(lm.id)), lm);
      }
    } catch (IOException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    return list;
  }


}