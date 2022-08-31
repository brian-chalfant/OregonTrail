public class WeatherStat {
    public Stat rain, temp, windSpeed;
    public WeatherStat(double minRain, double maxRain, double avgRain, double minTemp, double maxTemp, double avgTemp, double minWindSpeed,
            double maxWindSpeed, double avgWindSpeed) {
        this.rain = new Stat(minRain, maxRain, avgRain);
        this.temp = new Stat(minTemp, maxTemp, avgTemp);
        this.windSpeed = new Stat(minWindSpeed, maxWindSpeed, avgWindSpeed);
    }
}
