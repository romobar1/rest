package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.PostResource;
import com.FoodCompanion.REST.model.Post;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PostModelAssembler implements RepresentationModelAssembler<Post, EntityModel<Post>> {
    @Override
    public EntityModel<Post> toModel(Post post) {
        return EntityModel.of(post,
                linkTo(methodOn(PostResource.class).getPostById(post.getId())).withSelfRel(),
                linkTo(methodOn(PostResource.class).getAllPosts()).withRel("Posts")
        );
    }
}
