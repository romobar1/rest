package com.FoodCompanion.REST.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    public String body;
    public Long usuarioId;
    public Boolean isEdited;

    public Comentario(String body, Long usuarioId, boolean isEdited){
        this.body = body;
        this.usuarioId = usuarioId;
        this.isEdited = isEdited;
    }

    public Comentario(){}// Empty constructor

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Boolean getEdited() {
        return isEdited;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setBody(String body){
        this.body = body;
    }

    public void setUsuarioId(Long id){
        this.usuarioId = id;
    }

    public void setIsEdited(Boolean isEdited){
        this.isEdited = isEdited;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + this.id + '\'' +
                ", body=" + this.body + '\'' +
                ", usuarioId=" + this.usuarioId + '\'' +
                ", idEdited=" + this.isEdited + '\'' +
                "}";
    }
}
