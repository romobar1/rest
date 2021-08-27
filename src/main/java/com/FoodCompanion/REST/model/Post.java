package com.FoodCompanion.REST.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String body;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private User usuario;
    @OneToMany(mappedBy = "post")
    private List<Replies> replies;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "forumId")
    private Forum forum;
    private String userName;
    private String datePost;

    public Post(String name, String body){
    this.name = name;
    this.body = body;
    }

    public Post(){}// Empty constructor

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public User getUsuario() {
        return usuario;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public List<Replies> getReplies() {
        return replies;
    }

    public void setReplies(List<Replies> replies) {
        this.replies = replies;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + this.id + '\'' +
                ", name=" + this.name + '\'' +
                ", email=" + this.body + '\'' +
                "}";
    }

    public void addReply(Replies reply) {
        this.replies.add(reply);
    }
}
