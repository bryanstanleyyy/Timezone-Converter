import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class TimezoneConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Timezone Converter!");

        try {
            // Input time and timezone
            System.out.print("Enter time (HH:MM): ");
            String time = scanner.nextLine();
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.print("Enter source timezone (e.g., America/New_York): ");
            String sourceZone = scanner.nextLine();
            System.out.print("Enter target timezone (e.g., Asia/Tokyo): ");
            String targetZone = scanner.nextLine();

            // Parse input
            LocalDateTime localDateTime = LocalDateTime.parse(date + "T" + time);
            ZonedDateTime sourceDateTime = ZonedDateTime.of(localDateTime, ZoneId.of(sourceZone));

            // Convert to target timezone
            ZonedDateTime targetDateTime = sourceDateTime.withZoneSameInstant(ZoneId.of(targetZone));

            // Output result
            System.out.println("\nConverted Time:");
            System.out.printf("Source (%s): %s%n", sourceZone, sourceDateTime);
            System.out.printf("Target (%s): %s%n", targetZone, targetDateTime);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please ensure all inputs are correct.");
        }

        scanner.close();
    }
}
