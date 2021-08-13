package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.ComentarioResource;
import com.FoodCompanion.REST.model.Comentario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ComentarioModelAssenbler implements RepresentationModelAssembler<Comentario, EntityModel<Comentario>> {
    @Override
    public EntityModel<Comentario> toModel(Comentario comentario) {
        return  EntityModel.of(comentario,
                linkTo(methodOn(ComentarioResource.class).getComentarioById(comentario.getId())).withSelfRel(),
                linkTo(methodOn(ComentarioResource.class).getAllComentarios()).withRel("Comentarios"));
    }
}
