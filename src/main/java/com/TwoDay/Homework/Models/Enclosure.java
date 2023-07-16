package com.TwoDay.Homework.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ENCLOSURES")
public class Enclosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String size;

    @Column
    private String location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "enclosure_id")
    private List<EnclosureObject> objects;

    @OneToMany(mappedBy = "enclosure", cascade = CascadeType.ALL)
    private List<Animal> assignedAnimals = new ArrayList<>();

    public List<Animal> getAssignedAnimals() {
        return assignedAnimals;
    }

    public void setAssignedAnimals(List<Animal> assignedAnimals) {
        this.assignedAnimals = assignedAnimals;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<EnclosureObject> getObjects() {
        return objects;
    }

    public void setObjects(List<EnclosureObject> objects) {
        this.objects = objects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
