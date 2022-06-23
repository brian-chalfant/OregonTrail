public class Landmark {
    final String name;
    final boolean isCity;

    public Landmark(String name, boolean isCity, int distance) {
        this.name = name;
        this.isCity = isCity;
        this.distance = distance;
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
