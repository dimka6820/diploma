package com.dmma.diploma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClassRoom {
    @Id
    @GeneratedValue
    private Long id;
    private Integer number;
    private String camera;

    public ClassRoom() {
    }

    public ClassRoom(Integer number, String camera) {
        this.number = number;
        this.camera = camera;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }
}
