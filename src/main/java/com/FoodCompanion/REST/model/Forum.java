package com.FoodCompanion.REST.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "forumId")
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "forum")
    private List<Post> posts;

    public Forum(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Forum(){}// empty constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "Id=" + this.id + '\'' +
                ", name=" + this.name + '\'' +
                ", description=" + this.description + '\'' +
                "}";
    }

    public void addPostToForum(Post post) {

        this.posts.add(post);
    }
}
