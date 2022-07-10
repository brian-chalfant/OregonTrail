
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class LandmarkParser {


  public static HashMap<Integer, Landmark> parseLandmarks() {
    HashMap<Integer, Landmark> list = new HashMap<>();
    LandmarkLinkedList llist = new LandmarkLinkedList();
    JSONParser parser = new JSONParser();

    File file = new File("resources/landmarks/landmarks.json");

    JSONObject obj;

    try {
      obj = (JSONObject) parser.parse(new FileReader(file));
      for(Object i : obj.values()){
        Landmark lm = new Landmark((JSONObject) i);
        list.put(lm.id, lm);
      }
      for(int i = 0;i<list.size();i++){
        Landmark lm = list.get(i);
        if(lm.altRoute){
          LandmarkLinkedList.insertAlt(llist, lm);
        } else {
          LandmarkLinkedList.insert(llist,lm);
        }
      }
    } catch (IOException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    LandmarkLinkedList.printList(llist);
    return list;
  }


}