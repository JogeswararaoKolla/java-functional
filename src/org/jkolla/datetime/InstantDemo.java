package org.jkolla.datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class InstantDemo {
    public static void main(String[] args) {

        Instant instant = Instant.now();
        System.out.println("instant = " + instant); // instant = 2020-11-29T22:36:11.036949Z

        System.out.println(instant.toEpochMilli()); // 1606689371036
        System.out.println(instant.getEpochSecond());  // 1606689371
        System.out.println(instant.plus(2, ChronoUnit.DAYS)); // 2020-12-01T22:36:11.036949Z

        System.out.println("instant = " + instant); // instant = 2020-11-29T22:36:11.036949Z
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Kolkata"));
        System.out.println("zonedDateTime = " + zonedDateTime); // zonedDateTime = 2020-11-30T04:06:11.036949+05:30[Asia/Kolkata]

        Instant epoch = Instant.EPOCH;
        System.out.println("epoch = " + epoch); // epoch = 1970-01-01T00:00:00Z

        System.out.println(Instant.ofEpochSecond(1)); // 1970-01-01T00:00:01Z
        System.out.println(Instant.ofEpochSecond(3,0)); // 1970-01-01T00:00:03Z
        System.out.println(Instant.ofEpochSecond(2,1_000_000_000)); // 1970-01-01T00:00:03Z
        System.out.println(Instant.ofEpochSecond(2,-1_000_000_000)); // 1970-01-01T00:00:01Z
        System.out.println(Instant.ofEpochSecond(5,520_000_999)); // 1970-01-01T00:00:05.520000999Z
        
    }
}
