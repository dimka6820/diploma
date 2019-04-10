package com.dmma.diploma.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;

    private String number;
    private String name;
    private String surname;
    private String lastname;

    public Teacher() {
    }

    public Teacher(String number, String name, String surname, String lastname, List<Discipline> disciplines) {
        this.number = number;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.disciplines = disciplines;
    }

    @ManyToMany
    @JoinTable(name = "discipline_teacher",
            //foreign key for CarsEntity in employee_car table
            joinColumns = @JoinColumn(name = "teacher_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))
    private List<Discipline> disciplines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}
