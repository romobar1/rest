package com.FoodCompanion.REST.model;


import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Recetario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    private String description;

    public Recetario(String title, String description){
        this.title = title;
        this.description = description;
    }
    public Recetario(){}// empty constructor

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "recetario{" +
                "Id=" + this.id + '\'' +
                ", title=" + this.title + '\'' +
                ", description=" + this.description + '\'' +
                "}";
    }
}
