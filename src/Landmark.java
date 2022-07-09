import org.json.simple.JSONObject;

public class Landmark {
    final String name;
     boolean isCity;
     boolean isRiver;
     long id;
     String s1;
     String s2;
     String s3;
     int nextPoint1;
     int p1Miles;
     int nextPoint2;
     int p2Miles;
     int riverDepth;
     int riverWidth;

    public Landmark(String name, boolean isCity, boolean isRiver, int distance) {
        this.name = name;
        this.isCity = isCity;
        this.isRiver = isRiver;
        this.distance = distance;
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
        this.id = (Long) obj.get("id");
        this.name = (String) obj.get("name");
        this.isCity = (boolean) obj.get("isCity");
        this.isRiver = (boolean) obj.get("isRiver");
        this.s1 = (String) obj.get("s1");
        this.s2 = (String) obj.get("s2");
        this.s3 = (String) obj.get("s3");
        this.nextPoint1 = Integer.parseInt(String.valueOf(obj.get("nextPointID")));
        this.p1Miles = Integer.parseInt(String.valueOf(obj.get("p1miles")));
        this.nextPoint2 = Integer.parseInt(String.valueOf(obj.get("nextPoint2")));
        this.p2Miles = Integer.parseInt(String.valueOf(obj.get("p2miles")));
        this.riverDepth = Integer.parseInt(String.valueOf(obj.get("riverDepth")));
        this.riverWidth = Integer.parseInt(String.valueOf(obj.get("riverWidth")));


        
    }

    public int getDistance() {
        return distance;
    }

    int distance;


    public String getName() {
        return name;
    }


    public boolean isCity() {
        return isCity;
    }

}
