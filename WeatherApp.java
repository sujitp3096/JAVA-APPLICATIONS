import java.util.HashMap;
import java.util.Scanner;

public class WeatherApp {

    public static void main(String[] args) {
        // Predefined weather data for popular cities
        HashMap<String, String> weatherData = new HashMap<>();
        weatherData.put("mumbai", "Temperature: 32°C, Humidity: 70%, Condition: Sunny");
        weatherData.put("delhi", "Temperature: 28°C, Humidity: 60%, Condition: Cloudy");
        weatherData.put("pune", "Temperature: 26°C, Humidity: 55%, Condition: Partly Cloudy");
        weatherData.put("kolkata", "Temperature: 30°C, Humidity: 75%, Condition: Rainy");
        weatherData.put("bangalore", "Temperature: 22°C, Humidity: 50%, Condition: Cool Breeze");
        weatherData.put("chennai", "Temperature: 34°C, Humidity: 80%, Condition: Hot & Humid");\
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Simple Weather App ===");
        System.out.print("Enter city name: ");
        String city = sc.nextLine().toLowerCase();

        if (weatherData.containsKey(city)) {
            System.out.println("\nWeather Report for " + city.substring(0, 1).toUpperCase() + city.substring(1) + ":");
            System.out.println(weatherData.get(city));
        } else {
            System.out.println("\nSorry! Weather data for this city is not available.");
        }

        sc.close();
    }
}


