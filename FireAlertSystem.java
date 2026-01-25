import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

// ---------------- SENSOR INTERFACE ----------------
interface Sensor {
    double readValue();
    String getName();
}
// ---------------- TEMPERATURE SENSOR ----------------
class TemperatureSensor implements Sensor {
    private Random rand = new Random();

    public double readValue() {
        return 20 + rand.nextDouble() * 80; // 20°C – 100°C
    }

    public String getName() {
        return "Temperature Sensor";
    }
}
// ---------------- SMOKE SENSOR ----------------
class SmokeSensor implements Sensor {
    private Random rand = new Random();

    public double readValue() {
        return rand.nextDouble() * 100; // Smoke density %
    }
    public String getName() {
        return "Smoke Sensor";
    }
}


// ---------------- ALERT EVENT ----------------
class FireEvent implements Serializable {
    LocalDateTime time;
    double temperature;
    double smoke;
    String status;

    FireEvent(double t, double s, String status) {
        this.time = LocalDateTime.now();
        this.temperature = t;
        this.smoke = s;
        this.status = status;
    }

    public String toString() {
        return time + " | Temp: " + temperature + "°C | Smoke: " + smoke + "% | " + status;
    }
}

// ---------------- DATA LOGGER ----------------
class EventLogger {
    private final String FILE = "fire_events.log";

    public void log(FireEvent event) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE, true))) {
            oos.writeObject(event);
        } catch (Exception ignored) {}
    }

    public List<FireEvent> readAll() {
        List<FireEvent> list = new ArrayList<>();
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE))) {
            while (true) {
                list.add((FireEvent) ois.readObject());
            }
        } catch (Exception ignored) {}
        return list;
    }
}

// ---------------- MAIN SYSTEM ----------------
public class FireAlertSystem {

    static Sensor tempSensor = new TemperatureSensor();
    static Sensor smokeSensor = new SmokeSensor();
    static EventLogger logger = new EventLogger();

    static final double TEMP_THRESHOLD = 60.0;
    static final double SMOKE_THRESHOLD = 50.0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println(" Fire Alert Monitoring System Started...");
        System.out.println("Monitoring sensors (Press Ctrl+C to stop)\n");

        while (true) {
            double temp = tempSensor.readValue();
            double smoke = smokeSensor.readValue();

            System.out.printf("Temp: %.2f°C | Smoke: %.2f%%\n", temp, smoke);

            if (temp >= TEMP_THRESHOLD && smoke >= SMOKE_THRESHOLD) {
                FireEvent event = new FireEvent(temp, smoke, " FIRE ALERT!");
                logger.log(event);

                System.out.println(" ALERT: FIRE DETECTED!");
                System.out.println(" Emergency Services Notified!\n");
            } else {
                FireEvent event = new FireEvent(temp, smoke, "Normal");
                logger.log(event);
            }

            Thread.sleep(3000); // sensor read interval
        }
    }
}




