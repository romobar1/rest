package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.RoleModelAssembler;
import com.FoodCompanion.REST.model.Role;
import com.FoodCompanion.REST.service.RoleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/role")
public class RoleResource {
    private final RoleService roleService;
    private final RoleModelAssembler roleModelAssembler;

    public RoleResource (RoleService roleService, RoleModelAssembler roleModelAssembler){
        this.roleService = roleService;
        this.roleModelAssembler = roleModelAssembler;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Role>> getAllRoles(){
        List<EntityModel<Role>> roles = roleService.getAllRoles().stream()
                .map(roleModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(roles,
                linkTo(methodOn(RoleResource.class).getAllRoles()).withSelfRel());
    }

    @GetMapping("/find/{id}")
    public EntityModel<Role> getRoleById(@PathVariable("id") Long id){
        Role role = roleService.getRoleById(id);
        return roleModelAssembler.toModel(role);
    }
    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role1){
        Role role = roleService.addRole(role1);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestBody Role role1){
        Role role = roleService.updateRole(role1);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteRole(@PathVariable("id")Long id){
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
