package com.FoodCompanion.REST.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String email;
    private String name;
    private String password;
    private String imageUrl;

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

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + this.id + '\'' +
                ", name=" + this.name + '\'' +
                ", email=" + this.email + '\'' +
                ", imageUrl=" + this.imageUrl + '\'' +
                ", password=" + this.password + '\'' +
                "}";
    }
}


