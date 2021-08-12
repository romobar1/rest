package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.UsuarioModelAssembler;
import com.FoodCompanion.REST.model.Usuario;
import com.FoodCompanion.REST.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioResource {
    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler usuarioModelAssembler;
    public UsuarioResource(UsuarioService usuarioService, UsuarioModelAssembler usuarioModelAssembler) {
        this.usuarioService = usuarioService;
        this.usuarioModelAssembler = usuarioModelAssembler;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios() {
        List<EntityModel<Usuario>> usuarios =
               usuarioService.findAllUsuarios().stream()
                       .map(usuarioModelAssembler::toModel)
                       .collect(Collectors.toList());
        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioResource.class).getAllUsuarios()).withSelfRel());
    }

    @GetMapping("/find/{id}")
    public EntityModel<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.findUsuarioById(id);
        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario1) {
        Usuario usuario = usuarioService.addUser(usuario1);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> upadateUsuario(@RequestBody Usuario usuario1) {
        Usuario usuario = usuarioService.updateUsuario(usuario1);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}