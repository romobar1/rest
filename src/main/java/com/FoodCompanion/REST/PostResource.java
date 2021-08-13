package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.PostModelAssembler;
import com.FoodCompanion.REST.model.Post;
import com.FoodCompanion.REST.service.PostService;
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
    private final PostModelAssembler postModelAssembler;

    public PostResource(PostService postService, PostModelAssembler postModelAssembler){
        this.postService = postService;
        this.postModelAssembler = postModelAssembler;
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
    public ResponseEntity<Post> addPost(Post post1){
        Post post = postService.addPost(post1);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Post> updatePost(Post post1){
        Post post = postService.updatePost(post1);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deletePost(@PathVariable("id")Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
