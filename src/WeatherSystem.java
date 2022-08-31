import java.util.ArrayList;
import java.util.HashMap;

public class WeatherSystem {
    private double temperature;
    private double rain;
    private double windSpeed;

    private int droughtCount = 0;
    private int rainCount = 0;

    private double lerpRatio;
    private double rainThreshold;
    private double lowWindThreshold;
    private double highWindThreshold;
    private double freezingPoint;
    private double dewPoint;
    private double dewThreshold;
    private int rainCountThreshold;
    private int droughtCountThreshold;

    // [state, month] -> weather data
    private HashMap<String, ArrayList<WeatherStat>> weatherMap = new HashMap<>();

    public WeatherSystem(String state, int month){
        for(String iterateState: Game.states){
            weatherMap.put(iterateState, new ArrayList<>());
        }
        ArrayList<HashMap<String, String>> csvData = Utils.readCsvRows("resources/generated/trail_weather.csv");
        for(HashMap<String, String> row: csvData){
            WeatherStat ws = new WeatherStat(
                Double.parseDouble(row.get("rain_min")),
                Double.parseDouble(row.get("rain_max")),
                Double.parseDouble(row.get("rain_mean")),
                Double.parseDouble(row.get("temp_min")),
                Double.parseDouble(row.get("temp_max")),
                Double.parseDouble(row.get("temp_mean")),
                Double.parseDouble(row.get("wind_speed_min")),
                Double.parseDouble(row.get("wind_speed_max")),
                Double.parseDouble(row.get("wind_speed_mean"))
            );
            weatherMap.get(row.get("state").toLowerCase()).add(ws);
        }

        WeatherStat w = weatherMap.get(state).get(month);
        this.temperature = w.temp.random();
        this.rain = w.rain.random();
        this.windSpeed = w.windSpeed.random();

        // Weather settings
        this.lerpRatio = Settings.getDouble("weather_lerp");
        this.rainThreshold = Settings.getDouble("rain_threshold");
        this.lowWindThreshold = Settings.getDouble("low_wind_threshold");
        this.freezingPoint = Settings.getDouble("freezing_point");
        this.dewPoint = Settings.getDouble("dew_point");
        this.dewThreshold = Settings.getDouble("dew_threshold");
        this.rainCountThreshold = Settings.getInt("rain_count_threshold");
        this.droughtCountThreshold = Settings.getInt("drought_count_threshold");
        
    }

    /**
     * Updates the weather variables based on the state and month
     * @param state
     * @param month
     */
    public void update(String state, int month){
        WeatherStat w = weatherMap.get(state).get(month);
        this.temperature = Utils.lerp(this.temperature, w.temp.random(), this.lerpRatio);
        this.rain = Utils.lerp(this.rain, w.rain.random(), this.lerpRatio);
        this.windSpeed = Utils.lerp(this.windSpeed, w.windSpeed.random(), this.lerpRatio);

        if(isRain()){
            this.rainCount += 1;
            this.droughtCount = 0;
        }else {
            this.droughtCount += 1;
            this.rainCount = 0;
        }
    }

    public boolean isFog() {
        return isLowWind() && this.temperature > (this.dewPoint - this.dewThreshold) && this.temperature < (this.dewPoint + this.dewThreshold);
    }

    public boolean isBlizzard() {
        return isSnow() && isHighWind();
    }

    public boolean isDrought() {
        return this.droughtCount > this.droughtCountThreshold;
    }

    public boolean isHeavyRain() {
        return this.rainCount > this.rainCountThreshold && isHighWind();
    }

    public String toString() {
        return String.format("Raining: %s[%dR %dD] | Drought: %s Fog: %s Blizzard: %s HeavyRain: %s (%fR %fT %fWS)",
            isRain() ? "TRUE": "FALSE",
            rainCount,
            droughtCount,
            isDrought() ? "TRUE": "FALSE",
            isFog() ? "TRUE": "FALSE",
            isBlizzard() ? "TRUE": "FALSE",
            isHeavyRain() ? "TRUE": "FALSE",
            rain,
            temperature,
            windSpeed
        );
    }

    private boolean isRain(){
        return this.rain > this.rainThreshold;
    }

    private boolean isFreezing(){
        return this.temperature < this.freezingPoint;
    }

    private boolean isSnow(){
        return isRain() && isFreezing();
    }
    
    private boolean isHighWind() {
        return this.windSpeed > this.highWindThreshold;
    }

    private boolean isLowWind() {
        return this.windSpeed < this.lowWindThreshold;
    }
}
