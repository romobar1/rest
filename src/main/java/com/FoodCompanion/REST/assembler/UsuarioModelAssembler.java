package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.PostResource;
import com.FoodCompanion.REST.RecetaResource;
import com.FoodCompanion.REST.RecetarioResource;
import com.FoodCompanion.REST.UsuarioResource;
import com.FoodCompanion.REST.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User usuario) {
        return  EntityModel.of(usuario,
                linkTo(methodOn(UsuarioResource.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(RecetarioResource.class).getRecetarioFromOfUser(usuario.getId())).withRel("recetarios"),
                linkTo(methodOn(RecetaResource.class).getRecetasFromUser(usuario.getId())).withRel("Recetas"),
                linkTo(methodOn(PostResource.class).getPostFromUser(usuario.getId())).withRel("Posts"),
                linkTo(methodOn(UsuarioResource.class).getAllUsuarios()).withRel("Usuarios")
                );
    }
}
