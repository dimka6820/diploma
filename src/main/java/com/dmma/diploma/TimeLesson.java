package com.dmma.diploma;

import java.math.BigInteger;

public enum  TimeLesson {
    FIRST(1, 8, 0),
    SECOND(2, 9, 45),
    EMPTY(3, 11, 30);

    int number;
    int hours;
    int minutes;

    TimeLesson(int number, int hours, int minutes) {
        this.number = number;
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getHours() {
        return hours;
    }

}
