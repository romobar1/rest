package com.FoodCompanion.REST.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String body;
    private Boolean isEdited;
    @ManyToOne
    @JoinColumn(name = "recetaId", nullable = false)
    @JsonIgnore
    private Receta receta;

    public Comentario(String body, boolean isEdited){
        this.body = body;
        this.isEdited = isEdited;
    }

    public Comentario(){}// Empty constructor

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Boolean getEdited() {
        return isEdited;
    }

    public void setEdited(Boolean edited) {
        isEdited = edited;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setBody(String body){
        this.body = body;
    }

    public void setIsEdited(Boolean isEdited){
        this.isEdited = isEdited;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                " Id=" + this.id + '\'' +
                ",body=" + this.body + '\'' +
                ",idEdited=" + this.isEdited + '\'' +
                "}";
    }
}
