package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.PostModelAssembler;
import com.FoodCompanion.REST.model.Forum;
import com.FoodCompanion.REST.model.Post;
import com.FoodCompanion.REST.model.User;
import com.FoodCompanion.REST.service.ForumService;
import com.FoodCompanion.REST.service.PostService;
import com.FoodCompanion.REST.service.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/post")
public class PostResource {
    private final PostService postService;
    private final UsuarioService usuarioService;
    private final PostModelAssembler postModelAssembler;
    private final ForumService forumService;
    private final SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    public PostResource(
            PostService postService,
            PostModelAssembler postModelAssembler,
            ForumService forumService,
            UsuarioService usuarioService
            ){
        this.postService = postService;
        this.usuarioService = usuarioService;
        this.postModelAssembler = postModelAssembler;
        this.forumService = forumService;
    }
    @GetMapping("/all")
    public CollectionModel<EntityModel<Post>> getAllPosts(){
        List<EntityModel<Post>> posts =
                postService.getAllPosts().stream()
                        .map(postModelAssembler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(posts,
                linkTo(methodOn(PostResource.class).getAllPosts()).withSelfRel());
    }

    @GetMapping("/find/{id}")
    public EntityModel<Post> getPostById(@PathVariable("id") Long id){
        Post post = postService.findPostById(id);
        return postModelAssembler.toModel(post);
    }

    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@RequestBody Post post1){
        Post post = postService.addPost(post1);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @PostMapping("/add/user/{id}/forum/{forumId}")
    public ResponseEntity<Post> addPost(
            @RequestBody Post post1,
            @PathVariable("id")Long userid,
            @PathVariable("forumId") Long forumid
    ){
        User usuario = usuarioService.findUsuarioById(userid);
        Forum forum = forumService.findForumById(forumid);
        usuario.addPost(post1);
        post1.setUsuario(usuario);
        post1.setForum(forum);
        post1.setUserName(usuario.getUsername());
        Date date = new Date();
        String datePost = format.format(date);
        post1.setDatePost(datePost);
        forum.addPostToForum(post1);
        forumService.updateForum(forum);
        usuarioService.updateUsuario(usuario);
        Post post = postService.addPost(post1);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Post> updatePost(@RequestBody Post post1){
        Post post = postService.updatePost(post1);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deletePost(@PathVariable("id")Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/user/{id}/all")
    public CollectionModel<EntityModel<Post>> getPostFromUser(@PathVariable("id") Long id){

        List<EntityModel<Post>> posts = postService.getPostsFromUser(id).stream()
                .map(postModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(posts,
                linkTo(methodOn(PostResource.class).getAllPosts()).withSelfRel());
    }

    @GetMapping("/forum/{id}/all")
    public CollectionModel<EntityModel<Post>> getPostFromForum(@PathVariable("id")Long id){
        List<EntityModel<Post>> post = postService.getPostsFromForum(id).stream()
                .map(postModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(post,
                linkTo(methodOn(PostResource.class).getAllPosts()).withSelfRel());
    }

    @PutMapping("/setDisabled/{id}")
    public ResponseEntity<?> setPostDisabled(@PathVariable("id") Long postId){
        Post post = postService.findPostById(postId);
        post.setDisabled(true);
        postService.updatePost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
