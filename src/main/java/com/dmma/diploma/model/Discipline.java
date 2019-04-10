package com.dmma.diploma.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Discipline {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "discipline_teacher",
            //foreign key for CarsEntity in employee_car table
            joinColumns = @JoinColumn(name = "discipline_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

    public Discipline() {
    }

    public Discipline(String name, List<Teacher> teachers) {
        this.name = name;
        this.teachers = teachers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
