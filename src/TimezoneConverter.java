import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.Set;

public class TimezoneConverter {
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

                // Input source and target time zones
                String sourceZone = getValidTimeZoneInput(scanner, "source");
                String targetZone = getValidTimeZoneInput(scanner, "target");

                // Parse input
                LocalDateTime localDateTime = LocalDateTime.parse(date + "T" + time);
                ZonedDateTime sourceDateTime = ZonedDateTime.of(localDateTime, ZoneId.of(sourceZone));

                // Convert to target timezone
                ZonedDateTime targetDateTime = sourceDateTime.withZoneSameInstant(ZoneId.of(targetZone));

                // Output result
                System.out.println("\nConverted Time:");
                System.out.printf("Source (%s): %s%n", sourceZone, sourceDateTime);
                System.out.printf("Target (%s): %s%n", targetZone, targetDateTime);
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

    // Method to get a valid time zone input
    private static String getValidTimeZoneInput(Scanner scanner, String type) {
        while (true) {
            System.out.print("Enter " + type + " time zone (e.g., America/New_York): ");
            String timeZone = scanner.nextLine();
            // Validate if the time zone is available
            Set<String> availableZones = ZoneId.getAvailableZoneIds();
            if (availableZones.contains(timeZone)) {
                return timeZone;
            } else {
                System.out.println("Invalid time zone. Please make sure it's a valid time zone.");
            }
        }
    }
}
