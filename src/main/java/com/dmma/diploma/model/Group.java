package com.dmma.diploma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    private Long id;

    public Group() {
    }

    public Group(String number) {
        this.number = number;
    }

    private String number;

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
}
