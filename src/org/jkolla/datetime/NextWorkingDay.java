package org.jkolla.datetime;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int daysToAdd = 1;
        daysToAdd = dow == DayOfWeek.FRIDAY ? 3  : dow == DayOfWeek.SATURDAY ? 2 : 1;
        return  temporal.plus(daysToAdd, ChronoUnit.DAYS);
    }
}
