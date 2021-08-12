package com.FoodCompanion.REST.assembler;

import com.FoodCompanion.REST.RoleResource;
import com.FoodCompanion.REST.model.Role;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleModelAssembler implements RepresentationModelAssembler<Role, EntityModel<Role>> {
    @Override
    public EntityModel<Role> toModel(Role role) {
        return EntityModel.of(role,
                linkTo(methodOn(RoleResource.class).getRoleById(role.getId())).withSelfRel(),
                linkTo(methodOn(RoleResource.class).getAllRoles()).withRel("Roles")
                );
    }
}
