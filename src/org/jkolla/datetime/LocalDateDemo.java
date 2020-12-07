package org.jkolla.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalDateDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020,Month.APRIL,29);
        System.out.println("date = " + date); // date = 2020-04-29

        int year = date.getYear();
        System.out.println("year = " + year); // year = 2020
        Month month = date.getMonth();
        System.out.println("month = " + month); // month = APRIL
        int dayOfMonth = date.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth); // dayOfMonth = 29
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek); // dayOfWeek = WEDNESDAY
        int dayOfYear = date.getDayOfYear();
        System.out.println("dayOfYear = " + dayOfYear); // dayOfYear = 120
        boolean leapYear = date.isLeapYear();
        System.out.println("leapYear = " + leapYear); // leapYear = true
        int lenOfMonth = date.lengthOfMonth();
        System.out.println("lenOfMonth = " + lenOfMonth); // lenOfMonth = 30
        int lenOfYear = date.lengthOfYear();
        System.out.println("lenOfYear = " + lenOfYear); // lenOfYear = 366

        // Reading LocalDate values by using TemporalField Interface.
        // ChronoField Enumeration implements TemporalField interface
        int yy = date.get(ChronoField.YEAR);
        System.out.println("yy = " + yy); // yy = 2020
        int dd = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println("dd = " + dd); // dd = 29
        int mm = date.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("mm = " + mm); // mm = 4

        LocalDate dt1 = LocalDate.parse("02/29/1989", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("dt1 = " + dt1); // dt1 = 1989-02-28
        LocalDate dt2 = LocalDate.parse("1989-02-29", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("dt2 = " + dt2); // dt2 = 1989-02-28

       try {
           LocalDate birthday = LocalDate.parse("1989-02-29");
           System.out.println("birthday = " + birthday); // birthday = 1989-04-29
       } catch (DateTimeParseException exec) {
           System.out.println(exec);  // java.time.format.DateTimeParseException: Text '1989-02-29' could not be parsed: Invalid date 'February 29' as '1989' is not a leap year
       }

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("dateTime = " + dateTime); // dateTime = 2020-11-29T14:42:53.475523

        LocalDate localDate = dateTime.toLocalDate();
        System.out.println("localDate = " + localDate); // localDate = 2020-11-29

        LocalTime localTime = dateTime.toLocalTime();
        System.out.println("localTime = " + localTime); // localTime = 14:40:47.374501

        System.out.println(System.currentTimeMillis()); // Current time in milliseconds since the UNIX epoch (January 1, 1970 00:00:00 UTC).

    }
}
