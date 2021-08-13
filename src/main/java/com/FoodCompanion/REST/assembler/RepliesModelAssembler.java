package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.RepliesResource;
import com.FoodCompanion.REST.model.Replies;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RepliesModelAssembler implements RepresentationModelAssembler<Replies, EntityModel<Replies>> {
    @Override
    public EntityModel<Replies> toModel(Replies replies) {
        return EntityModel.of(replies,
                linkTo(methodOn(RepliesResource.class).getReplyById(replies.getId())).withSelfRel(),
                linkTo(methodOn(RepliesResource.class).findAllReplies()).withRel("Replies")
        );
    }
}
