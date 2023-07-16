package com.TwoDay.Homework.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "ENCLOSURE_OBJECTS")
public class EnclosureObject {

    private String object;
    public EnclosureObject(){

    }
    public EnclosureObject(String object){
        this.object = object;
    }

    public String getObject(){
        return object;
    }
    public void setObject(){
        this.object = object;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
