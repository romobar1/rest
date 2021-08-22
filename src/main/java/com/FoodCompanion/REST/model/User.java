package com.FoodCompanion.REST.model;

import com.FoodCompanion.REST.model.Post;
import com.FoodCompanion.REST.model.Receta;
import com.FoodCompanion.REST.model.Recetario;
import com.FoodCompanion.REST.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, name = "usuarioId")
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	private String imageUrl;
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Receta> recetas = new ArrayList<>();
	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<Post> posts = new ArrayList<>();
	@OneToMany
	@JoinColumn(name = "usuario")
	@JsonIgnore
	private List<Recetario> recetarios = new ArrayList<>();
	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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
