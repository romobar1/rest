package com.FoodCompanion.REST.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String description;

    public Forum(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Forum(){}// empty constructor

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "Id=" + this.id + '\'' +
                ", name=" + this.name + '\'' +
                ", description=" + this.description + '\'' +
                "}";
    }
}
