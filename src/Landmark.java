public class Landmark {
    final String name;
    final boolean isCity;
    final boolean isRiver;

    public Landmark(String name, boolean isCity, boolean isRiver, int distance) {
        this.name = name;
        this.isCity = isCity;
        this.isRiver = isRiver;
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
