# Timezone Converter

A simple Java program to convert time between different time zones using Java's `java.time` package.

## Features
- Converts time from one timezone to another.
- Handles date and time inputs in ISO-8601 format.
- Supports all timezones recognized by Java's `ZoneId`.

## How to Run
1. Clone this repository:
   ```bash
   git clone https://github.com/bryanstanleyyy/Timezone-Converter.git
   cd Timezone-Converter/src
2. Compile the program:
   ```bash
   javac TimezoneConverter.java
3. Run the program:
   ```bash
   java TimezoneConverter
4. Example:
   ```bash
   ***Welcome to Timezone Converter!***
   ***Please refer to timezones.txt when attempting timezones.***

   Enter time (HH:MM): 15:46
   Enter date (YYYY-MM-DD): 2024-11-17
   Enter source city (e.g., America/New_York, Asia/Kolkata): America/New_York
   Enter target city (e.g., America/New_York, Asia/Kolkata): Asia/Dubai

   Converted Time:
   Source City (America/New_York): 2024-11-17 15:46
   Target City (Asia/Dubai): 2024-11-18 00:46

