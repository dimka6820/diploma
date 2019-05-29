package com.dmma.diploma.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

public enum TimeLesson {
    FIRST(LocalTime.of(8, 0), LocalTime.of(9, 30), 1),
    SECOND(LocalTime.of(9, 45), LocalTime.of(11, 15), 2),
    THIRD(LocalTime.of(11, 30), LocalTime.of(13, 00), 3),
    FOURTH(LocalTime.of(13, 40), LocalTime.of(15, 10), 4),
    FIFTH(LocalTime.of(15, 20), LocalTime.of(16, 50), 5),
    SIXTH(LocalTime.of(17, 00), LocalTime.of(18, 30), 6),
    SEVENTH(LocalTime.of(18, 40), LocalTime.of(20, 10), 7),
    EIGHTH(LocalTime.of(20, 20), LocalTime.of(21, 50), 8);

    LocalTime startTime;
    LocalTime endTime;
    int lessonNumber;

    TimeLesson(LocalTime startTime, LocalTime endTime, int lessonNumber) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.lessonNumber = lessonNumber;
    }

    public static int getCurentLessonNumber() {
        LocalTime curentTime = getLocalTime();
        for (TimeLesson timeLesson : values()) {
            LocalTime startTime = timeLesson.getStartTime();
            LocalTime endTime = timeLesson.getEndTime();
            if (curentTime.isAfter(startTime) && curentTime.isBefore(endTime)) {
                return timeLesson.getLessonNumber();
            }
        }

        return 0;
    }

    public static LocalTime getLocalTime() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();

        return LocalTime.of(hour, minute);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

}
