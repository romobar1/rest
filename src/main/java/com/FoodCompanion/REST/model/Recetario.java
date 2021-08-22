package com.FoodCompanion.REST.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recetario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recetarioId")
    private Long id;
    private String title;
    private String description;
    @ManyToMany(mappedBy = "recetario")
    @JsonIgnore
    private List<Receta> recetas = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private User usuario;

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

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "recetario{" +
                "Id=" + this.id + '\'' +
                ", title=" + this.title + '\'' +
                ", description=" + this.description + '\'' +
                "}";
    }

    public void addRecetaToRecetario(Receta receta) {
        recetas.add(receta);
    }
}
