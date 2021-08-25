package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.RecetarioModelAssembler;
import com.FoodCompanion.REST.model.Receta;
import com.FoodCompanion.REST.model.Recetario;
import com.FoodCompanion.REST.model.User;
import com.FoodCompanion.REST.service.RecetaService;
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
@RequestMapping("/recetario")
public class RecetarioResource  {

    private final RecetarioService recetarioService;
    private final RecetaService recetaService;
    private final RecetarioModelAssembler recetarioModelAssembler;
    private final UsuarioService usuarioService;

    public RecetarioResource (RecetarioService recetarioService, RecetarioModelAssembler recetarioModelAssembler, RecetaService recetaService, UsuarioService usuarioService){
        this.recetarioService = recetarioService;
        this.recetaService = recetaService;
        this.recetarioModelAssembler = recetarioModelAssembler;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Recetario>> getAllRecetarios(){
        List<EntityModel<Recetario>> recetarios =
                recetarioService.findAllRecetarios().stream()
                        .map(recetarioModelAssembler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(recetarios,
                linkTo(methodOn(RecetarioResource.class).getAllRecetarios()).withSelfRel());
    }

    @GetMapping("/find/{id}")
    public EntityModel<Recetario> getRecetarioById(@PathVariable("id")Long id){
        Recetario recetario = recetarioService.findRecetarioById(id);
        return recetarioModelAssembler.toModel(recetario);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Recetario> addRecetario(
            @RequestBody Recetario recetario,
            @PathVariable("id") Long userId ){
        User user = usuarioService.findUsuarioById(userId);
        recetario.addRecetarioToUser(user);
        Recetario recetarioAdd = recetarioService.addRecetario(recetario);
        return new ResponseEntity<>(recetarioAdd, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Recetario> updateRecetario(@RequestBody Recetario recetario) {
        Recetario recetarioUpdate = recetarioService.updateRecetario(recetario);
        return new ResponseEntity<>(recetarioUpdate, HttpStatus.OK);
    }

     @PutMapping("/{recetarioId}/receta/{recetaId}")
     public ResponseEntity<Recetario> addRecetaToRecetario(
             @PathVariable Long recetarioId,
             @PathVariable Long recetaId)
     {
         Receta receta = recetaService.findRecetaById(recetaId);
         Recetario recetario = recetarioService.findRecetarioById(recetarioId);
         recetario.addRecetaToRecetario(receta);
         receta.addRecetarioToReceta(recetario);
         recetaService.addReceta(receta);
         recetarioService.addRecetario(recetario);
         return new ResponseEntity<>(recetario,HttpStatus.OK);
     }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteRecetario(@PathVariable("id") Long id){
        recetarioService.deleteRecetario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public CollectionModel<EntityModel<Recetario>> getRecetarioFromOfUser(
            @PathVariable("id")Long id
    ){
        List<EntityModel<Recetario>> recetarios =
                recetarioService.findRecetariosOfUser(id).stream()
                        .map(recetarioModelAssembler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(recetarios,
                linkTo(methodOn(RecetarioResource.class).getAllRecetarios()).withSelfRel());
    }
}