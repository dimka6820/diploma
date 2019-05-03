package com.dmma.diploma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private ClassRoom classRoom;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Discipline discipline;
    @ManyToOne
    private Group group;
    private Integer weekNumber;
    private Integer weekDay;
    private Integer lessonNumber;
    private Boolean done;
    private Boolean canceled;

    public Lesson() {
        done = false;
        canceled = false;
    }

    public Lesson(ClassRoom classRoom, Teacher teacher, Discipline discipline, Group group, Integer weekNumber, Integer weekDay, Integer lessonNumber, Boolean done, Boolean canceled) {
        this.id = id;
        this.classRoom = classRoom;
        this.teacher = teacher;
        this.discipline = discipline;
        this.group = group;
        this.weekNumber = weekNumber;
        this.weekDay = weekDay;
        this.lessonNumber = lessonNumber;
        this.done = done;
        this.canceled = canceled;
    }

    public Lesson(ClassRoom classRoom, Integer weekNumber, Integer weekDay, Integer lessonNumber) {
        this.classRoom = classRoom;
        this.weekNumber = weekNumber;
        this.weekDay = weekDay;
        this.lessonNumber = lessonNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public String toString() {
        return id
                + " "
                + classRoom.getBody()
                + "/"
                + classRoom.getNumber()
                + "<br/>";
    }
}