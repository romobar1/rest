package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.RecetarioResource;
import com.FoodCompanion.REST.model.Recetario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecetarioModelAssembler implements RepresentationModelAssembler<Recetario, EntityModel<Recetario>> {

    @Override
    public EntityModel<Recetario> toModel(Recetario recetario) {
        return EntityModel.of(recetario,
                linkTo(methodOn(RecetarioResource.class).getRecetarioById(recetario.getId())).withSelfRel(),
                linkTo(methodOn(RecetarioResource.class).getAllRecetarios()).withRel("Recetarios")
        );
    }
}
