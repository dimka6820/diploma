package com.dmma.diploma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class UnsuccessfulLesson {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Lesson lesson;

    private String dateTime;
    private String image;

    public UnsuccessfulLesson() {
    }

    public UnsuccessfulLesson(Lesson lesson, LocalDateTime dateTime, String image) {
        this.lesson = lesson;
        this.dateTime = dateTime.toString();
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.parse(dateTime);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}