package com.FoodCompanion.REST.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Replies implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false,nullable = false)
    private Long id;
    private String body;
    private Long userId;
    private String type;
    public Replies(String body, Long userId, String type){
        this.body = body;
        this.userId = userId;
        this.type = type;
    }

    public Replies(){} // empty constructor

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Replies {" +
                "Id=" + this.id + '\'' +
                ", body=" + this.body + '\'' +
                ", userId=" + this.userId + '\'' +
                ", type=" + this.type + '\'' +
                "}";
    }
}
