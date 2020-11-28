package org.jkolla.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class DateDemo {
    public static void main(String[] args) {
        
        LocalDate currentDate = LocalDate.now();
        System.out.println("currentDate = " + currentDate); // currentDate = 2020-11-28

        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfMonth = " + firstDayOfMonth); // firstDayOfMonth = 2020-11-01
        System.out.println("lastDayOfMonth = " + currentDate.with(TemporalAdjusters.lastDayOfMonth())); // lastDayOfMonth = 2020-11-30

        LocalDate nextSunday = currentDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println("nextSunday = " + nextSunday); // nextSunday = 2020-11-29

        LocalDate birthday = LocalDate.of(2021, Month.JUNE,18);
        System.out.println("birthday = " + birthday); // birthday = 2021-06-18

        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek); // dayOfWeek = SATURDAY

        Period diff = Period.between(LocalDate.now(),birthday); // finding difference between dates
        System.out.println("diff.getYears() = " + diff.getYears() + " diff.getMonths() = " + diff.getMonths() + " diff.getDays() = "  + diff.getDays()); 
        // diff.getYears() = 0 diff.getMonths() = 6 diff.getDays() = 21

        long daysToBirthday = currentDate.until(birthday, ChronoUnit.DAYS);
        System.out.println("daysToBirthday = " + daysToBirthday); // daysToBirthday = 202

        LocalDate birthdayAltered = birthday.withMonth(4).withDayOfMonth(29).withYear(1989);
        System.out.println("birthdayAltered = " + birthdayAltered); // birthdayAltered = 1989-04-29

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/d/YYYY");
        System.out.println(birthdayAltered.format(dateTimeFormatter)); // 04/29/1989

        long daysFromBirthday = birthdayAltered.until(currentDate, ChronoUnit.DAYS);
        System.out.println("daysFromBirthday = " + daysFromBirthday); // daysFromBirthday = 11536

        YearMonth date1 = YearMonth.now();
        System.out.println( date1.withMonth(2) + " = " + date1.withMonth(2).lengthOfMonth()); // 2020-02 = 29
        System.out.println("date1 = " + date1); // date1 = 2020-11

        YearMonth date2 = YearMonth.of(2019, Month.FEBRUARY);
        System.out.println( date2 + " = " + date2.lengthOfMonth()); // 2019-02 = 28

    }
}
