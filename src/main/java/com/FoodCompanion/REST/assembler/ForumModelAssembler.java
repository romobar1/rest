package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.ForumResource;
import com.FoodCompanion.REST.PostResource;
import com.FoodCompanion.REST.model.Forum;
import com.FoodCompanion.REST.repo.ForumRepo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ForumModelAssembler implements RepresentationModelAssembler<Forum, EntityModel<Forum>> {
    @Override
    public EntityModel<Forum> toModel(Forum entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(ForumResource.class).findForumById(entity.getId())).withSelfRel(),
                linkTo(methodOn(PostResource.class).getPostFromForum(entity.getId())).withRel("Posts"),
                linkTo(methodOn(ForumResource.class).getAllForums()).withRel("Forums")
                );
    }
}
