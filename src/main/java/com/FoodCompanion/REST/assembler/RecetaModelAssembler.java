package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.RecetaResource;
import com.FoodCompanion.REST.UsuarioResource;
import com.FoodCompanion.REST.model.Receta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecetaModelAssembler implements RepresentationModelAssembler<Receta, EntityModel<Receta>> {
    @Override
    public EntityModel<Receta> toModel(Receta receta) {
        return EntityModel.of(receta,
                linkTo(methodOn(RecetaResource.class).getRecetaById(receta.getId())).withSelfRel(),
                linkTo(methodOn(RecetaResource.class).getAllRecetas()).withRel("Recetas")
                );
    }
}
