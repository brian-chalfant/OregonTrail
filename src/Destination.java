public class Destination {

    private int id;
    private String name;
    private int distance;

    public Destination(int id, String name, int distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
    }

    public Destination(int id, int distance) {
        this.id = id;
        this.distance = distance;
        this.name = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
