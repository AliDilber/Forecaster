package edu.eskisehir.model;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private String year;

    private List<Record> records = new ArrayList<Record>();

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Row{" +
                "year='" + year + '\'' +
                ", records=" + records +
                '}';
    }
}
