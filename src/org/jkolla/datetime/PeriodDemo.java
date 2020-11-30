package org.jkolla.datetime;

import java.time.LocalDate;
import java.time.Period;

public class PeriodDemo {

    public static String dateFormat(Period input) {
        return input.getYears() + ";" + input.getMonths() + ";" + input.getDays();
    }
    public static void main(String[] args) {

        Period diff = Period.between(LocalDate.of(2020,11,29),
                LocalDate.of(2021,04,29));
        System.out.println("diff = " + dateFormat(diff)); // diff = 0;5;0
        
        Period tenDays = Period.ofDays(10);
        System.out.println("dateFormat(tenDays) = " + dateFormat(tenDays)); // dateFormat(tenDays) = 0;0;10
        
        Period oneYearSixMonthsOneDay = Period.of(1,6,1);
        System.out.println("dateFormat(oneYearSixMonthsOneDay) = " + dateFormat(oneYearSixMonthsOneDay)); // dateFormat(oneYearSixMonthsOneDay) = 1;6;1
        
        Period threeWeeks = Period.ofWeeks(3);
        System.out.println("dateFormat(threeWeeks) = " + dateFormat(threeWeeks)); // dateFormat(threeWeeks) = 0;0;21
        
    }
}
