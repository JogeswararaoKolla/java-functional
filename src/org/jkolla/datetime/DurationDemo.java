package org.jkolla.datetime;

import java.time.Duration;;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DurationDemo {
    public static void main(String[] args) {
        
        LocalTime time1 = LocalTime.of(10,55,0,3_000_000);
        System.out.println("time1 = " + time1); // time1 = 10:55:00.003
        LocalTime time2 = LocalTime.of(10,58,20,0);
        System.out.println("time2 = " + time2); // time2 = 10:58:20
        LocalTime time3 = LocalTime.now();
        System.out.println("time3 = " + time3); // time3 = 16:33:19.653140

        System.out.println("time3.toNanoOfDay() = " + time3.toNanoOfDay()); // time3.toNanoOfDay() = 59599653140000
        System.out.println("time3.toSecondOfDay() = " + time3.toSecondOfDay()); // time3.toSecondOfDay() = 59599

        // 1 sec = 1_000_000_000 Nanos
        // 1 Millis = 1_000_000 Nanos
        // 1 sec = 1_000 Millis

        Duration d1 = Duration.between(time1,time2);
        System.out.println("d1.get(ChronoUnit.SECONDS) = " + d1.get(ChronoUnit.SECONDS)); // d1.get(ChronoUnit.SECONDS) = 199
        System.out.println("d1.get(ChronoUnit.NANOS) = " + d1.get(ChronoUnit.NANOS)); // d1.get(ChronoUnit.NANOS) = 997000000
        System.out.println("d1.getUnits() = " + d1.getUnits()); // d1.getUnits() = [Seconds, Nanos]

        // Duration can be applied between LocalTime, LocalDateTime and Instant
        LocalDateTime dateTime1 = LocalDateTime.now();
        System.out.println("dateTime1 = " + dateTime1); // dateTime1 = 2020-11-29T16:40:35.614037
        LocalDateTime dateTime2 = LocalDateTime.now();
        System.out.println("dateTime2 = " + dateTime2); // dateTime2 = 2020-11-29T16:40:35.617892
        
        Duration d2 = Duration.between(dateTime1,dateTime2);
        System.out.println("d2.getNano() = " + d2.getNano()); // d2.getNano() = 3855000
        System.out.println("d2.getSeconds() = " + d2.getSeconds()); // d2.getSeconds() = 0

        Duration threeMinutes = Duration.ofMinutes(3);
        System.out.println("threeMinutes.getSeconds() = " + threeMinutes.getSeconds()); // threeMinutes.getSeconds() = 180
        Duration oneDay = Duration.of(1, ChronoUnit.DAYS);
        System.out.println("oneDay.getSeconds() = " + oneDay.getSeconds()); // oneDay.getSeconds() = 86400

    }
}
