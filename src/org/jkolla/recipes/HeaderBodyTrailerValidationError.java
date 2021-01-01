package org.jkolla.recipes;

public enum HeaderBodyTrailerValidationError {

    // Enum Types are Classes, Implicitly inherit from Java Enum Class
    // Enum Types can have fields ,methods,constructors, Each value is an instance of enum type
    // Defines a fine list of values separated with commas, At end of the list with ";"
    // Enum values are ordered, first value is lowest and last value is highest ordering.

    RECORD_TYPE("recordType","4001","Invalid Record Type Found"),
    DETAIL_COST_CHECK("cost","4002","Cost attribute has negative value");

    String fieldName;
    String errorCode;
    String errorDesc;

    HeaderBodyTrailerValidationError(String fieldName, String errorCode, String errorDesc) {
        this.fieldName = fieldName;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    @Override
    public String toString() {
        return "HeaderBodyTrailerValidationError{" +
                "fieldName='" + fieldName + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorDesc='" + errorDesc + '\'' +
                '}';
    }
}
