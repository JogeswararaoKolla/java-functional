package org.jkolla.datetime;

import java.time.LocalDate;

public class TemporalAdjusterDemo {
    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2020,11,28);
        System.out.println("date = " + date);
        System.out.println("date.getDayOfWeek() = " + date.getDayOfWeek()); // date.getDayOfWeek() = SATURDAY
        
        LocalDate nextWorkingDay = date.with(new NextWorkingDay());
        System.out.println("nextWorkingDay = " + nextWorkingDay); // nextWorkingDay = 2020-11-30
    }
}
