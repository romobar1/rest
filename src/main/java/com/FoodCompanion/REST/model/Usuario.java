package com.FoodCompanion.REST.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "usuarioId")
    private Long id;
    private String email;
    private String name;
    private String password;
    private String imageUrl;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Receta> recetas = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "usuario")
    @JsonIgnore
    private List<Recetario> recetarios = new ArrayList<>();
    public Usuario(String email, String name, String password, String imageUrl){
        this.email = email;
        this.name = name;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    public Usuario() {} // Empty constructor

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getEmail(){
        return this.email;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getImageUrl(){
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Recetario> getRecetarios() {
        return recetarios;
    }

    public void setRecetarios(List<Recetario> recetarios) {
        this.recetarios = recetarios;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "Id=" + this.id + '\'' +
                ", name=" + this.name + '\'' +
                ", email=" + this.email + '\'' +
                ", imageUrl=" + this.imageUrl + '\'' +
                ", password=" + this.password + '\'' +
                "}";
    }

    public void addRecetaToUsuario(Receta receta) {
        this.recetas.add(receta);
    }

    public void setRecetarioToUser(Recetario recetario) {
        this.recetarios.add(recetario);
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}


