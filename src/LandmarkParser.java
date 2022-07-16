
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class LandmarkParser {


  public static LandmarkLinkedList parseLandmarks() {
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
      // Loop through the Arraylist and retrieve all of the primary routes
      // put the primary routes into the linked list
      for(int i = 0;i<list.size();i++){
        Landmark lm = list.get(i);
        if(!lm.altRoute){
          LandmarkLinkedList.insert(llist,lm);
        }
      }
      //go through the array list and Find a node that wasn't put in originally
      for(int i =0;i<list.size();i++){
        boolean found = false;
        Node node = new Node(list.get(i));
        Node nlist = llist.head;
        while(nlist.next()!=null){
          if(node.equals(nlist)){
            found = true;
            break;
          }
          nlist = nlist.next();
        }
        // node wasn't found in the linked list
        if(!found){
          int id = node.getData().getId();
          nlist = llist.head;
          while(nlist.next()!=null){
            //cycle through destinations for each node and find a match
            for(Destination d: nlist.getData().getDestinations()){
              if(d != null) {
                if (d.getId() == id) {
                  //we found the previous node
                  node.setPrevious(nlist);
                  nlist.setAltNext(node);
                }
              }
            }
            //cycle through destinations for current alt node and find its match
            for(Destination d: node.getData().getDestinations()){
              if(d != null) {
                if (d.getId() == nlist.getData().getId()) {
                  //we found the next node
                  node.setNext(nlist);
                  nlist.setAltPrevious(node);
                }
              }
            }
            nlist = nlist.next();
          }
        }

      }
    } catch (IOException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }

    return llist;
  }


}