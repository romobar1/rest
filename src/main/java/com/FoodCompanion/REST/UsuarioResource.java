package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.UsuarioModelAssembler;
import com.FoodCompanion.REST.model.Recetario;
import com.FoodCompanion.REST.model.User;
import com.FoodCompanion.REST.service.RecetarioService;
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
    private final UsuarioService usuarioService;;
    private final RecetarioService recetarioService;
    private final UsuarioModelAssembler usuarioModelAssembler;
    public UsuarioResource(
            UsuarioService usuarioService,
            UsuarioModelAssembler usuarioModelAssembler,
            RecetarioService recetarioService
    ) {
        this.usuarioService = usuarioService;
        this.recetarioService = recetarioService;
        this.usuarioModelAssembler = usuarioModelAssembler;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<User>> getAllUsuarios() {
        List<EntityModel<User>> usuarios =
               usuarioService.findAllUsuarios().stream()
                       .map(usuarioModelAssembler::toModel)
                       .collect(Collectors.toList());
        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioResource.class).getAllUsuarios()).withSelfRel());
    }

    @GetMapping("/find/{id}")
    public EntityModel<User> getUsuarioById(@PathVariable("id") Long id) {
        User usuario = usuarioService.findUsuarioById(id);
        return usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUsuario(@RequestBody User usuario1) {
        User usuario = usuarioService.addUser(usuario1);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/recetario/{recetarioId}")
    public ResponseEntity<User> addRecetarioToUser(
            @PathVariable("userID") Long userid,
            @PathVariable("recetarioId") Long recetarioid
    ){
        Recetario recetario = recetarioService.findRecetarioById(recetarioid);
        User usuario = usuarioService.findUsuarioById(userid);

        recetario.setUsuario(usuario);
        usuario.setRecetarioToUser(recetario);

        recetarioService.updateRecetario(recetario);
        User usuario1 = usuarioService.updateUsuario(usuario);
        return new ResponseEntity<>(usuario1, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> upadateUsuario(@RequestBody User usuario1) {
        User usuario = usuarioService.updateUsuario(usuario1);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}