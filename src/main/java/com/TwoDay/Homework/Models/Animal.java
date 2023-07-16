package com.TwoDay.Homework.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "ANIMALS")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String species;

    @Column
    private String food;

    @Column
    private int amount;

    @ManyToOne // Many animals can belong to one enclosure
    @JoinColumn(name = "enclosure_id")
    private Enclosure enclosure;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
