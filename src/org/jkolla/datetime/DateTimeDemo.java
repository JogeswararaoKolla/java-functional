package org.jkolla.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class DateTimeDemo {
    public static void main(String[] args) {
        LocalTime currentTime = LocalTime.now();
        System.out.println("currentTime = " + currentTime); // currentTime = 00:30:14.456632

        LocalTime timeToMeet = LocalTime.of(14, 10);
        System.out.println("timeToMeet = " + timeToMeet); // timeToMeet = 14:10

        // Meeting after 8 hours
        System.out.println(timeToMeet.plus(8, ChronoUnit.HOURS)); // 22:10
        System.out.println(timeToMeet.plusHours(8)); // 22:10

        LocalDateTime currentDateTime1 = LocalDateTime.now();
        System.out.println("currentDateTime1 = " + currentDateTime1);

        Instant currentDateTime2 = Instant.now(); // GMT
        System.out.println("currentDateTime2 = " + currentDateTime2); // currentDateTime2 = 2020-11-28T07:46:59.077296Z

        Instant epoch = Instant.EPOCH; // Instant is point on the Time line , January 1st, 1970 at Midnight GMT
        System.out.println("epoch = " + epoch); // epoch = 1970-01-01T00:00:00Z

        Duration currentTimeDiff = Duration.between(epoch, currentDateTime2);
        System.out.println("currentTimeDiffInMillis = " + currentTimeDiff.toMillis()); // currentTimeDiffInMillis = 1606550365201
        System.out.println("currentTimeDiffInSeconds = " + currentTimeDiff.toSeconds()); // currentTimeDiffInSeconds = 1606550365

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println("availableZoneIds = " + availableZoneIds);

        availableZoneIds.forEach(System.out::println);

        ZonedDateTime currentTimeWithZone = ZonedDateTime.now();
        System.out.println("currentTimeWithZone = " + currentTimeWithZone); // currentTimeWithZone = 2020-11-28T01:50:09.813455-07:00[America/Phoenix]
        System.out.println("currentTimeWithZone = " + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(currentTimeWithZone)); // currentTimeWithZone = 2020-11-28T01:45:11.900053

        ZonedDateTime zonedDateTimeIndia = currentTimeWithZone.withZoneSameInstant(ZoneId.of("Asia/Calcutta"));
        System.out.println("zonedDateTimeIndia = " + zonedDateTimeIndia); // zonedDateTimeIndia = 2020-11-28T14:20:09.813455+05:30[Asia/Calcutta]

    }
}
