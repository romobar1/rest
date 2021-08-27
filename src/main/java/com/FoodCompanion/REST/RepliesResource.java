package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.RepliesModelAssembler;
import com.FoodCompanion.REST.model.Post;
import com.FoodCompanion.REST.model.Replies;
import com.FoodCompanion.REST.repo.RepliesRepo;
import com.FoodCompanion.REST.service.PostService;
import com.FoodCompanion.REST.service.RepliesService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/replies")
public class RepliesResource {
    private final RepliesService repliesService;
    private final RepliesModelAssembler repliesModelAssembler;
    private final PostService postService;
    private final SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    public RepliesResource (
            RepliesService repliesService,
            RepliesModelAssembler repliesModelAssembler,
            PostService postService
    ){
        this.repliesService = repliesService;
        this.repliesModelAssembler = repliesModelAssembler;
        this.postService = postService;
    }
    @GetMapping("/all")
    public CollectionModel<EntityModel<Replies>> findAllReplies(){
        List<EntityModel<Replies>> replies =
                repliesService.getAllReplies().stream()
                        .map(repliesModelAssembler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(replies,
                linkTo(methodOn(RepliesResource.class).findAllReplies()).withSelfRel());
    }

    @GetMapping("/find/{id}")
    public EntityModel<Replies> getReplyById(@PathVariable("id") Long id){
        Replies replies = repliesService.findReplyById(id);
        return repliesModelAssembler.toModel(replies);
    }

    @PostMapping("/add")
    public ResponseEntity<Replies> addReply(@RequestBody Replies replies1){
        Replies replies = repliesService.addReplies(replies1);
        return new ResponseEntity<>(replies, HttpStatus.CREATED);
    }
    @PostMapping("/add/{id}")
    public ResponseEntity<Replies> addReply(
            @RequestBody Replies replies1,
            @PathVariable("id")Long id
    ){
        Post post = postService.findPostById(id);
        post.addReply(replies1);
        replies1.setPost(post);
        Date date = new Date();
        String dateReply = format.format(date);
        replies1.setDate(dateReply);
        postService.updatePost(post);
        Replies replies = repliesService.addReplies(replies1);
        return new ResponseEntity<>(replies, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Replies> updateReply (@RequestBody Replies replies1){
        Replies replies = repliesService.updateResply(replies1);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteReply(@PathVariable("id") Long id){
        repliesService.deleteReply(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post/{id}/all")
    public CollectionModel<EntityModel<Replies>> getRepliesFromPost(@PathVariable("id")Long id){
        List<EntityModel<Replies>> replies = repliesService.getRepliesFromPost(id).stream()
                .map(repliesModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(replies,
                linkTo(methodOn(RepliesResource.class).findAllReplies()).withSelfRel());

    }
}
