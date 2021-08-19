package com.FoodCompanion.REST.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "usuarioId")
    private Long id;
    private String userId;
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
    private Date lastLoginDate;
    private Date getLastLoginDateDisplay;
    private Date joinDate;
    private boolean isActive;
    private boolean isNotLocked;
    private String[] roles;
    private String[] authorities;

    public Usuario(Long id, String userId, String email, String name, String password, String imageUrl, List<Receta> recetas, Role role, List<Post> posts, List<Recetario> recetarios, Date lastLoginDate, Date getLastLoginDateDisplay, Date joinDate, boolean isActive, boolean isNotLocked, String[] roles, String[] authorities) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.imageUrl = imageUrl;
        this.recetas = recetas;
        this.role = role;
        this.posts = posts;
        this.recetarios = recetarios;
        this.lastLoginDate = lastLoginDate;
        this.getLastLoginDateDisplay = getLastLoginDateDisplay;
        this.joinDate = joinDate;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
        this.roles = roles;
        this.authorities = authorities;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getGetLastLoginDateDisplay() {
        return getLastLoginDateDisplay;
    }

    public void setGetLastLoginDateDisplay(Date getLastLoginDateDisplay) {
        this.getLastLoginDateDisplay = getLastLoginDateDisplay;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    public String[] getAuthorities() {
        return authorities;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
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


