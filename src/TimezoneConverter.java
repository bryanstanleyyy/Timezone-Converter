import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TimezoneConverter {
    // Map of city names to time zones
    private static final Map<String, String> cityToTimeZone = new HashMap<>();

    static {
        // Fill the map with some common cities and their time zones
        cityToTimeZone.put("New York", "America/New_York");
        cityToTimeZone.put("Los Angeles", "America/Los_Angeles");
        cityToTimeZone.put("London", "Europe/London");
        cityToTimeZone.put("Tokyo", "Asia/Tokyo");
        cityToTimeZone.put("Sydney", "Australia/Sydney");
        cityToTimeZone.put("Paris", "Europe/Paris");
        cityToTimeZone.put("Berlin", "Europe/Berlin");
        cityToTimeZone.put("Moscow", "Europe/Moscow");
        cityToTimeZone.put("Dubai", "Asia/Dubai");
        cityToTimeZone.put("Mumbai", "Asia/Kolkata");
        // Add more cities as needed
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Timezone Converter!");

        // Loop until user provides valid inputs
        while (true) {
            try {
                // Input time
                String time = getValidTimeInput(scanner);

                // Input date
                String date = getValidDateInput(scanner);

                // Input source and target cities
                String sourceCity = getValidCityInput(scanner, "source");
                String targetCity = getValidCityInput(scanner, "target");

                // Convert city names to time zones
                String sourceZone = cityToTimeZone.get(sourceCity);
                String targetZone = cityToTimeZone.get(targetCity);

                if (sourceZone == null || targetZone == null) {
                    throw new Exception("Invalid city name. Please use a valid city name.");
                }

                // Parse input
                LocalDateTime localDateTime = LocalDateTime.parse(date + "T" + time);
                ZonedDateTime sourceDateTime = ZonedDateTime.of(localDateTime, ZoneId.of(sourceZone));

                // Convert to target time zone
                ZonedDateTime targetDateTime = sourceDateTime.withZoneSameInstant(ZoneId.of(targetZone));

                // Output result
                System.out.println("\nConverted Time:");
                System.out.printf("Source (%s): %s%n", sourceCity, sourceDateTime);
                System.out.printf("Target (%s): %s%n", targetCity, targetDateTime);
                break; // Exit the loop when everything is correct
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please ensure all inputs are correct.");
            }
        }

        scanner.close();
    }

    // Method to get valid time input
    private static String getValidTimeInput(Scanner scanner) {
        while (true) {
            System.out.print("Enter time (HH:MM): ");
            String time = scanner.nextLine();
            // Check if the time matches the expected format
            if (time.matches("^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$")) {
                return time;
            } else {
                System.out.println("Invalid time format. Please use the format HH:MM.");
            }
        }
    }

    // Method to get valid date input
    private static String getValidDateInput(Scanner scanner) {
        while (true) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            // Check if the date matches the expected format
            if (date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                return date;
            } else {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
            }
        }
    }

    // Method to get a valid city input
    private static String getValidCityInput(Scanner scanner, String type) {
        while (true) {
            System.out.print("Enter " + type + " city (e.g., New York, Tokyo): ");
            String city = scanner.nextLine();

            if (cityToTimeZone.containsKey(city)) {
                return city;
            } else {
                System.out.println("Invalid city. Please enter a valid city.");
            }
        }
    }
}

