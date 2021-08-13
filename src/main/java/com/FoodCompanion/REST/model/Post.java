package com.FoodCompanion.REST.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String topics;
    private Long userId;

    public Post(String name, String topics, Long userId){
    this.name = name;
    this.topics = topics;
    this.userId = userId;
    }

    public Post(){}// Empty constructor

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getTopics() {
        return topics;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + this.id + '\'' +
                ", name=" + this.name + '\'' +
                ", email=" + this.topics + '\'' +
                ", imageUrl=" + this.userId + '\'' +
                "}";
    }
}
