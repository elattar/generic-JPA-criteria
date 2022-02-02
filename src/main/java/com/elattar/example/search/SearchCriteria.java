package com.elattar.example.search;

public class SearchCriteria {

    private String fieldName;
    private String operation;
    private Object fieldValue;

    public SearchCriteria() {
    }

    public SearchCriteria(String fieldName, String operation, Object fieldValue) {
        this.fieldName = fieldName;
        this.operation = operation;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "fieldName='" + fieldName + '\'' +
                ", operation='" + operation + '\'' +
                ", fieldValue=" + fieldValue +
                '}';
    }
}