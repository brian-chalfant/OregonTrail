import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Landmark {
    final String name;
    final boolean isCity;
    final boolean isRiver;
    final boolean altRoute;
    final int id;
    final JSONArray sayings;
    final Destination[] points = new Destination[2];

    final RiverData riverData;
    final String state;



    public Landmark(JSONObject obj) {
        this.id = Utils.castInt(obj.get("id"));
        this.name = (String) obj.get("name");
        this.isCity = (boolean) obj.get("isCity");
        this.isRiver = (boolean) obj.get("isRiver");
        this.sayings = (JSONArray) obj.get("sayings");
        this.altRoute = (boolean) obj.get("altRoute");
        this.state = ((String)obj.get("state")).toLowerCase();
        JSONArray p = (JSONArray) obj.get("nextPoints");
        int count = 0;
        for (Object o : p) {
            JSONObject rr = (JSONObject) o;
            int id = Utils.castInt(rr.get("id"));
            String name = (String) rr.get("name");
            int distance = Utils.castInt(rr.get("distance"));
            points[count] = new Destination(id, name, distance);
            count++;

        }


        if(obj.get("riverData")!= null){
            JSONObject a = (JSONObject) obj.get("riverData");
            this.riverData = new RiverData();
            this.riverData.setRiverDepth(Utils.castInt(a.get("riverDepth")));
            this.riverData.setRiverWidth(Utils.castInt(a.get("riverWidth")));
        } else {
            this.riverData = null;
        }

        
    }

    public Destination[] getDestinations(){
        return points;
    }


    public String getName() {
        return name;
    }


    public boolean isCity() {
        return isCity;
    }

    public boolean isRiver(){
        return isRiver;
    }


    public int getId(){
        return this.id;
    }
    public RiverData getRiverData() {
        return riverData;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        String name = this.getName();
        if(obj instanceof Landmark) {
            Landmark lm = (Landmark) obj;
            String oname = lm.getName();
            return name.equals(oname);
        }
        return false;

    }


}

