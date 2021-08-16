package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.PostModelAssembler;
import com.FoodCompanion.REST.model.Forum;
import com.FoodCompanion.REST.model.Post;
import com.FoodCompanion.REST.model.Usuario;
import com.FoodCompanion.REST.service.ForumService;
import com.FoodCompanion.REST.service.PostService;
import com.FoodCompanion.REST.service.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    @PostMapping("/add/user/{id}")
    public ResponseEntity<Post> addPost(
            @RequestBody Post post1,
            @PathVariable("id")Long id
    ){
        Usuario usuario = usuarioService.findUsuarioById(id);
        usuario.addPost(post1);
        post1.setUsuario(usuario);
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

    @PutMapping("/{forumId}/forum/post/{postId}")
    public ResponseEntity<Post> addPostToForum(
            @PathVariable("forumId")Long forumid,
            @PathVariable("postId")Long postid
    ){
        Forum forum = forumService.findForumById(forumid);
        Post post = postService.findPostById(postid);

        forum.addPostToForum(post);
        post.setForum(forum);

        forumService.updateForum(forum);

        Post postAdded = postService.updatePost(post);

        return new ResponseEntity<>(postAdded, HttpStatus.OK);
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
}
