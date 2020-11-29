package org.jkolla.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class DateTimeDemo {
    public static void main(String[] args) {
        LocalTime currentTime = LocalTime.now();
        System.out.println("currentTime = " + currentTime); // currentTime = 16:32:06.094460

        LocalTime timeToMeet = LocalTime.of(14, 10);
        System.out.println("timeToMeet = " + timeToMeet); // timeToMeet = 14:10

        // Meeting after 8 hours
        System.out.println(timeToMeet.plus(8, ChronoUnit.HOURS)); // 22:10
        System.out.println(timeToMeet.plusHours(8)); // 22:10

        LocalDateTime currentDateTime1 = LocalDateTime.now();
        System.out.println("currentDateTime1 = " + currentDateTime1); // currentDateTime1 = 2020-11-28T16:32:06.101646
        System.out.println("currentDateTime1 formatted = " + currentDateTime1.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm a"))); // currentDateTime1 formatted = Nov 28 2020 16:32 PM

        Instant currentDateTime2 = Instant.now();
        System.out.println("currentDateTime2 = " + currentDateTime2); // currentDateTime2 = 2020-11-28T23:32:06.131634Z

        LocalDateTime ldt = LocalDateTime.ofInstant(currentDateTime2, ZoneId.systemDefault());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm a");
        System.out.println("ldt = " + ldt.format(format)); // ldt = 2020-11-28 16:32 PM

        long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(),
                ChronoUnit.SECONDS);
        System.out.println("secondsFromEpoch = " + secondsFromEpoch); // secondsFromEpoch = 1606606326

        Instant epoch = Instant.EPOCH; // Instant is point on the Time line , January 1st, 1970 at Midnight GMT
        System.out.println("epoch = " + epoch); // epoch = 1970-01-01T00:00:00Z

        Duration currentTimeDiff = Duration.between(epoch, currentDateTime2);
        System.out.println("currentTimeDiffInMillis = " + currentTimeDiff.toMillis()); // currentTimeDiffInMillis = 1606606326131
        System.out.println("currentTimeDiffInSeconds = " + currentTimeDiff.toSeconds()); // currentTimeDiffInSeconds = 1606606326

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println("availableZoneIds = " + availableZoneIds);

        ZonedDateTime currentTimeWithZone = ZonedDateTime.now();
        System.out.println("currentTimeWithZone = " + currentTimeWithZone); // currentTimeWithZone = 2020-11-28T16:32:06.143039-07:00[America/Phoenix]
        System.out.println("currentTimeWithZone = " + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(currentTimeWithZone)); // 2020-11-28T16:32:06.143039

        ZonedDateTime zonedDateTimeIndia = currentTimeWithZone.withZoneSameInstant(ZoneId.of("Asia/Calcutta"));
        System.out.println("zonedDateTimeIndia = " + zonedDateTimeIndia); // zonedDateTimeIndia = 2020-11-29T05:02:06.143039+05:30[Asia/Calcutta]

    }
}
