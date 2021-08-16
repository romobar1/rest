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
    private String topics;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<Replies> replies;
    @ManyToOne
    @JoinColumn(name = "forumId", nullable = false)
    private Forum forum;

    public Post(String name, String topics, Long userId){
    this.name = name;
    this.topics = topics;
    this.userId = userId;
    }

    public Post(){}// Empty constructor

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getTopics() {
        return topics;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
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

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + this.id + '\'' +
                ", name=" + this.name + '\'' +
                ", email=" + this.topics + '\'' +
                ", imageUrl=" + this.userId + '\'' +
                "}";
    }

    public void addReply(Replies reply) {
        this.replies.add(reply);
    }
}
