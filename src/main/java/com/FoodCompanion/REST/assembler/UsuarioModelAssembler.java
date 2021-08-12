package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.UsuarioResource;
import com.FoodCompanion.REST.model.Usuario;
import com.FoodCompanion.REST.service.UsuarioService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return  EntityModel.of(usuario,
                linkTo(methodOn(UsuarioResource.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioResource.class).getAllUsuarios()).withRel("Usuarios")
                );
    }
}
