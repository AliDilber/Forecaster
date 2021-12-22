package edu.eskisehir.model;

public class Record {
    private String month;
    private int value;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return month + ":" + value;
    }
}