public class Entity {
    public int health;

    public Entity(int health){
        this.health = health;
    }

    public Entity(){
        this.health = Settings.getInt("base_health");
    }

    public boolean isAlive(){
        return health > 0;
    }
}
