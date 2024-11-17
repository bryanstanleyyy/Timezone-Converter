import java.util.*;
import java.text.*;

public class TimezoneConverter {

    // Method to get valid time input
    private static String getValidTimeInput(Scanner scanner) {
        while (true) {
            System.out.print("Enter time (HH:MM): ");
            String time = scanner.nextLine();
            // Allow one or two digits for hour and minute
            if (time.matches("^(\\d{1,2}):(\\d{2})$")) {
                // Ensure hour is between 00-23 and minute is between 00-59
                String[] parts = time.split(":");
                int hour = Integer.parseInt(parts[0]);
                int minute = Integer.parseInt(parts[1]);
                if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
                    return time;
                } else {
                    System.out.println("Invalid time. Hour must be between 00 and 23, and minute must be between 00 and 59.");
                }
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
            // Check if the date is in the correct format
            if (date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                try {
                    // Use SimpleDateFormat to check if the date is valid
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false); // Set lenient to false to prevent invalid dates
                    Date parsedDate = sdf.parse(date);
                    return date;
                } catch (ParseException e) {
                    System.out.println("Invalid date. Please enter a valid date (YYYY-MM-DD).");
                }
            } else {
                System.out.println("Invalid date format. Please use the format YYYY-MM-DD.");
            }
        }
    }

    // Method to check if a time zone is valid
    private static boolean isValidTimeZone(String city) {
        TimeZone timeZone = TimeZone.getTimeZone(city);
        // Check if the time zone ID is valid, it should not be "UTC"
        return !timeZone.getID().equals("UTC");
    }

    // Method to convert time between different time zones
    private static String convertTime(String time, String sourceCity, String targetCity) {
        if (!isValidTimeZone(sourceCity)) {
            return "Error: Invalid source time zone. Please provide a valid city time zone name.";
        }
        if (!isValidTimeZone(targetCity)) {
            return "Error: Invalid target time zone. Please provide a valid city time zone name.";
        }

        try {
            // Parse input time
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date sourceTime = sdf.parse(time);

            // Get time zones based on city names (using standard time zone IDs)
            TimeZone sourceTimeZone = TimeZone.getTimeZone(sourceCity);
            TimeZone targetTimeZone = TimeZone.getTimeZone(targetCity);

            // Get the time in milliseconds
            long sourceTimeInMillis = sourceTime.getTime();
            
            // Get the time difference between source and target time zones
            int sourceOffset = sourceTimeZone.getOffset(sourceTimeInMillis);
            int targetOffset = targetTimeZone.getOffset(sourceTimeInMillis);

            // Convert time to target city time
            long targetTimeInMillis = sourceTimeInMillis + (targetOffset - sourceOffset);
            Date targetTime = new Date(targetTimeInMillis);

            // Format the target time
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            String targetTimeStr = outputFormat.format(targetTime);

            return targetTimeStr;
        } catch (ParseException e) {
            return "Error: Invalid time format.";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display welcome message and reference
        System.out.println("***Welcome to Timezone Converter!***");
        System.out.println("***Please refer to timezones.txt when attempting timezones.***\n");

        // Get time input from user
        String time = getValidTimeInput(scanner);

        // Get date input from user
        String date = getValidDateInput(scanner);

        // Get source and target cities
        String sourceCity, targetCity;
        while (true) {
            System.out.print("Enter source city (e.g., America/New_York, Asia/Kolkata): ");
            sourceCity = scanner.nextLine();
            if (isValidTimeZone(sourceCity)) {
                break;
            } else {
                System.out.println("Invalid source city. Please provide a valid city time zone name.");
            }
        }

        while (true) {
            System.out.print("Enter target city (e.g., America/New_York, Asia/Kolkata): ");
            targetCity = scanner.nextLine();
            if (isValidTimeZone(targetCity)) {
                break;
            } else {
                System.out.println("Invalid target city. Please provide a valid city time zone name.");
            }
        }

        // Convert the time and print results
        String targetTime = convertTime(time, sourceCity, targetCity);

        // Print the converted time
        System.out.println("\nConverted Time:");
        System.out.println("Source City (" + sourceCity + "): " + date + " " + time);
        System.out.println("Target City (" + targetCity + "): " + date + " " + targetTime);

        scanner.close();
    }
}
