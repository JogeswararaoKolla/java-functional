package org.jkolla.experiments;

import org.jkolla.recipes.HeaderBodyTrailerValidationError;

import java.util.ArrayList;
import java.util.List;

public class EnumTypeDemo {
    public static void main(String[] args)  {

        HeaderBodyTrailerValidationError detailCostCheck = HeaderBodyTrailerValidationError.DETAIL_COST_CHECK;
        System.out.println("detailCostCheck.getErrorDesc() = " + detailCostCheck.getErrorDesc()); // detailCostCheck.getErrorDesc() = Cost attribute has negative value

        List<HeaderBodyTrailerValidationError> errors = new ArrayList<>();
        errors.add(HeaderBodyTrailerValidationError.RECORD_TYPE);
        errors.add(HeaderBodyTrailerValidationError.DETAIL_COST_CHECK);
        errors.forEach(System.out::println);
        // HeaderBodyTrailerValidationError{fieldName='recordType', errorCode='4001', errorDesc='Invalid Record Type Found'}
        // HeaderBodyTrailerValidationError{fieldName='cost', errorCode='4002', errorDesc='Cost attribute has negative value'}

        System.out.println("Ordering : " + HeaderBodyTrailerValidationError.RECORD_TYPE.compareTo(HeaderBodyTrailerValidationError.DETAIL_COST_CHECK)); // Ordering : -1
        System.out.println("Ordering : " + HeaderBodyTrailerValidationError.DETAIL_COST_CHECK.compareTo(HeaderBodyTrailerValidationError.RECORD_TYPE)); // Ordering : 1

        HeaderBodyTrailerValidationError[] values = HeaderBodyTrailerValidationError.values();
        for(HeaderBodyTrailerValidationError e : values){
            System.out.println("errorCode = " + e.getErrorCode()); // errorCode = 4001 errorCode = 4002
        }

        // String toString() Returns the name of this enum constant, as contained in the declaration.
        System.out.println(HeaderBodyTrailerValidationError.valueOf("RECORD_TYPE")); // we need to override toString method to retrieve the record as below instead of RECORD_TYPE
        // HeaderBodyTrailerValidationError{fieldName='recordType', errorCode='4001', errorDesc='Invalid Record Type Found'}

    }
}
