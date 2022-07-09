import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Landmark {
    final String name;
    boolean isCity;
     boolean isRiver;
     int id;
     JSONArray sayings;
     HashMap<Integer, Integer> points = new HashMap<>();
     int riverDepth;
     int riverWidth;


    public Landmark(String name, boolean isCity, boolean isRiver, int distance) {
        this.name = name;
        this.isCity = isCity;
        this.isRiver = isRiver;
    }

    /*
    *   "willemettevalley":{
    "name": "The Willemette Valley",
    "id": 18,
    "isCity": false,
    "isRiver": false,
    "s1": "",
    "s2":"",
    "s3":"",
    "nextPointID": 0,
    "p1miles": 0,
    "nextPoint2": "",
    "p2miles":"",
    "riverDepth": "",
    "riverWidth": ""
    * */
    public Landmark(JSONObject obj) {
        this.id = Utils.castInt(obj.get("id"));
        this.name = (String) obj.get("name");
        this.isCity = (boolean) obj.get("isCity");
        this.isRiver = (boolean) obj.get("isRiver");
        this.sayings = (JSONArray) obj.get("sayings");

        JSONArray p = (JSONArray) obj.get("nextPoints");
        for (Object o : p) {
            JSONObject rr = (JSONObject) o;
            int id = Utils.castInt(rr.get("id"));
            int distance = Utils.castInt(rr.get("distance"));
            this.points.put(id, distance);
            System.out.println(points);
        }


        if(obj.get("riverData")!= null){
            JSONObject a = (JSONObject) obj.get("riverData");
            this.riverDepth = Utils.castInt(a.get("riverDepth"));
            this.riverWidth = Utils.castInt(a.get("riverWidth"));
        } else {
            this.riverWidth = 0;
            this.riverDepth = 0;
        }

        
    }

    public int getDistance() {
        return 100;
    }

    public ArrayList<Integer> getDestinations(){
        ArrayList<Integer> destinations = new ArrayList<>();
        this.points.forEach((key, value) -> destinations.add(key));
        return destinations;
    }



    public String getName() {
        return name;
    }


    public boolean isCity() {
        return isCity;
    }

    public boolean hasFork(){
        return (this.points.size() > 1);
    }

    public int FirstFork(){
        ArrayList<Integer> e = this.getDestinations();
        return e.get(0);
    }

    public int FirstForkDistance(){
        ArrayList<Integer> e = this.getDestinations();
        return this.points.get(e.get(0));
    }

    public int getId(){
        return this.id;
    }
}

