package com.dmma.diploma.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UnsuccessfulLesson {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Lesson lesson;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "unsuccessful_lesson_image", joinColumns = @JoinColumn(name = "unsuccessful_lesson_id"))
    private List<String> image;

    public UnsuccessfulLesson() {
    }

    public UnsuccessfulLesson(Lesson lesson, String image) {
        this.lesson = lesson;
        this.addImage(image);
    }

    public UnsuccessfulLesson(Lesson lesson) {
        this.lesson = lesson;
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

    public List<String> getImage() {
        return image;
    }

    public void addImage(String image) {
        if (this.image == null) {
            this.image = new ArrayList<>();
        }
        this.image.add(image);
    }

    public void setImage(List<String> image) {
        this.image = image;
    }
}