package com.FoodCompanion.REST.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Receta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
    private String title;
    private String description;
    private String body;
    private int rate;

    public Receta (String title, String description, String body, int rate){
        this.title = title;
        this.description = description;
        this.body = body;
        this.rate = rate;
    }

    public Receta() {} // Empty constructor

    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public String getBody(){
        return this.body;
    }
    public int getRate(){
        return this.rate;
    }

    @Override
    public String toString() {
         return "Receta{" +
                "  Id=" + this.id + '\'' +
                ", title=" + this.title + '\'' +
                ", description=" + this.description + '\'' +
                ", body=" + this.body + '\'' +
                ", rate=" + this.rate + '\'' +
                "}";
    }

}
