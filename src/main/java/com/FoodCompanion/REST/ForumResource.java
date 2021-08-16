package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.ForumModelAssembler;
import com.FoodCompanion.REST.model.Forum;
import com.FoodCompanion.REST.service.ForumService;
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
@RequestMapping("/forum")
public class ForumResource {
    private final ForumService forumService;
    private final ForumModelAssembler forumModelAssembler;

    public ForumResource(ForumService forumService, ForumModelAssembler forumModelAssembler){
        this.forumService = forumService;
        this.forumModelAssembler = forumModelAssembler;
    }

     @GetMapping("/all")
    public CollectionModel<EntityModel<Forum>> getAllForums(){
         List<EntityModel<Forum>> forum =
                 forumService.getAllForums().stream()
                         .map(forumModelAssembler::toModel)
                         .collect(Collectors.toList());
        return CollectionModel.of(forum,
                linkTo(methodOn(ForumResource.class).getAllForums()).withSelfRel());
    }

    @GetMapping("/find/{id}")
    public EntityModel<Forum> findForumById(@PathVariable("id") Long id){
        Forum forum = forumService.findForumById(id);
        return forumModelAssembler.toModel(forum);
    }

    @PostMapping("/add")
    public ResponseEntity<Forum> addForum(@RequestBody Forum forum1){
        Forum forum = forumService.addForum(forum1);
        return new ResponseEntity<>(forum, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Forum> updateForum(@RequestBody Forum forum1){
        Forum forum = forumService.updateForum(forum1);
        return new ResponseEntity<>(forum, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteForum(@PathVariable("id") Long id){
        forumService.deleteForum(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
