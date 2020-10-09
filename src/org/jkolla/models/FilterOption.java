package org.jkolla.models;

import java.util.List;

public class FilterOption {
    private  String  category;
    private List<String> selectValues;

    public FilterOption(String category, List<String> selectValues) {
        this.category = category;
        this.selectValues = selectValues;
    }

    public FilterOption() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSelectValues() {
        return selectValues;
    }

    public void setSelectValues(List<String> selectValues) {
        this.selectValues = selectValues;
    }

    @Override
    public String toString() {
        return "FilterOption{" +
                "category='" + category + '\'' +
                ", selectValues=" + selectValues +
                '}';
    }
}
