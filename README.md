# Timezone Converter

A simple Java program to convert time between different time zones using Java's `java.time` package.

## Features
- Converts time from one timezone to another.
- Handles date and time inputs in ISO-8601 format.
- Supports all timezones recognized by Java's `ZoneId`.

## How to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/<your-username>/TimezoneConverter.git
   cd TimezoneConverter/src
2. Compile the program:
   ```bash
   javac TimezoneConverter.java
3. Run the program:
   ```bash
   java TimezoneConverter
##Example:
Welcome to Timezone Converter!
Enter time (HH:MM): 14:30
Enter date (YYYY-MM-DD): 2024-11-17
Enter source timezone (e.g., America/New_York): America/New_York
Enter target timezone (e.g., Asia/Tokyo): Asia/Tokyo

Converted Time:
Source (America/New_York): 2024-11-17T14:30-05:00[America/New_York]
Target (Asia/Tokyo): 2024-11-18T04:30+09:00[Asia/Tokyo]

