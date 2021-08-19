package com.FoodCompanion.REST.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.loader.collection.OneToManyJoinWalker;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Receta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recetaId")
    private Long id;
    private String title;
    private String description;
    private int numComensales;
    private int timepo;
    private int dificultad;
    @ElementCollection
    private Set<String> tags = new HashSet<>();
    private String ingredientes;
    private String body;
    private int rate;
    @JsonIgnore
    @OneToMany(mappedBy = "receta", fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="recetarios",
            joinColumns = @JoinColumn(name="recetaId"),
            inverseJoinColumns = @JoinColumn(name = "recetarioId")
    )
    private List<Recetario> recetario = new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuarioID", nullable = false)
    private Usuario usuario;

    public Receta(String title, String description, int numComensales, int timepo, int dificultad, Set<String> tags, String ingredientes, String body, int rate, List<Comentario> comentarios, List<Recetario> recetario, Usuario usuario) {
        this.title = title;
        this.description = description;
        this.numComensales = numComensales;
        this.timepo = timepo;
        this.dificultad = dificultad;
        this.tags = tags;
        this.ingredientes = ingredientes;
        this.body = body;
        this.rate = rate;
        this.comentarios = comentarios;
        this.recetario = recetario;
        this.usuario = usuario;
    }

    public Receta() {} // Empty constructor

    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getNumComensales() {
        return numComensales;
    }

    public void setNumComensales(int numComensales) {
        this.numComensales = numComensales;
    }

    public int getTimepo() {
        return timepo;
    }

    public void setTimepo(int timepo) {
        this.timepo = timepo;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public List<Recetario> getRecetario() {
        return recetario;
    }

    public void setRecetario(List<Recetario> recetario) {
        this.recetario = recetario;
    }

    public String getBody(){
        return this.body;
    }
    public int getRate(){
        return this.rate;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
         return "Receta{" +
                "  Id=" + this.id + '\'' +
                ", title=" + this.title + '\'' +
                ", description=" + this.description + '\'' +
                ", body=" + this.body + '\'' +
                ", rate=" + this.rate + '\'' +
                "}";
    }

    public void addRecetarioToReceta(Recetario recetario) {
    this.recetario.add(recetario);
    }

    public void addComentarioToReceta(Comentario comentario){
        this.comentarios.add(comentario);
    }
}
