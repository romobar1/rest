package com.FoodCompanion.REST.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "roleId")
    private Long id;
    private String title;
    private String description;
    @OneToMany
    @JoinColumn(name="role")
    private List<Usuario> usuarios = new ArrayList<>();
    public Role (String title, String description){
        this.title = title;
        this.description = description;
    }

    public Role(){} // Empty constructor

    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id=" + this.id + '\'' +
                ", title=" + this.title + '\'' +
                ", description=" + this.description + '\'' +
                "}";
    }

    public void addToRole(Usuario usuario) {
        this.usuarios.add(usuario);
    }
}
