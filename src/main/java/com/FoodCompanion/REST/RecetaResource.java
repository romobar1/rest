package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.RecetaModelAssembler;
import com.FoodCompanion.REST.model.Comentario;
import com.FoodCompanion.REST.model.Receta;
import com.FoodCompanion.REST.model.Usuario;
import com.FoodCompanion.REST.service.ComentarioService;
import com.FoodCompanion.REST.service.RecetaService;
import com.FoodCompanion.REST.service.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/receta")
public class RecetaResource {
    private final RecetaService recetaService;
    private final UsuarioService usuarioService;
    private final RecetaModelAssembler recetaModelAssembler;
    public RecetaResource (
            RecetaService recetaService,
            RecetaModelAssembler recetaModelAssembler,
            UsuarioService usuarioService){
        this.recetaService = recetaService;
        this.recetaModelAssembler = recetaModelAssembler;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Receta>> getAllRecetas(){
        List<EntityModel<Receta>> recetas =
                recetaService.findAllRecetas().stream()
                        .map(recetaModelAssembler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(recetas,
                linkTo(methodOn(RecetaResource.class).getAllRecetas()).withSelfRel());
    }

    @GetMapping("/find/{id}")
    public EntityModel<Receta> getRecetaById(@PathVariable("id") Long id){
        Receta receta = recetaService.findRecetaById(id);
        return recetaModelAssembler.toModel(receta);
    }

    @PostMapping("/add")
    public ResponseEntity<Receta> addReceta(@RequestBody Receta receta1){
        Receta receta = recetaService.addReceta(receta1);
        return new ResponseEntity<>(receta, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Receta> updateReceta(@RequestBody Receta receta1){
        Receta receta = recetaService.updateReceta(receta1);
        return new ResponseEntity<>(receta, HttpStatus.OK);
    }

    @PostMapping("/add/user/{id}")
     public ResponseEntity<Receta> addRecetaToUser(
             @PathVariable("id")Long id,
             @RequestBody Receta receta
    ){
        Usuario usuario = usuarioService.findUsuarioById(id);
        usuario.addRecetaToUsuario(receta);
        receta.setUsuario(usuario);
        usuarioService.updateUsuario(usuario);
        Receta receta1 = recetaService.updateReceta(receta);
        return new ResponseEntity<>(receta1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteRecetaById(@PathVariable("id")Long id){
        recetaService.deleteReceta(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/recetario/{id}/all")
    public CollectionModel<EntityModel<Receta>> getRecetasFromRecetario(
            @PathVariable("id")Long id){
        List<EntityModel<Receta>> recetas =
                recetaService.findRecetasFromRecetario(id).stream()
                        .map(recetaModelAssembler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(recetas,
                linkTo(methodOn(RecetaResource.class).getAllRecetas()).withSelfRel());
    }

    @GetMapping("/user/{id}/all")
    public CollectionModel<EntityModel<Receta>> getRecetasFromUser(
            @PathVariable("id")Long id
    ){
        List<EntityModel<Receta>> recetas = recetaService.findRecetasFromUser(id).stream()
                .map(recetaModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(recetas,
                linkTo(methodOn(RecetaResource.class).getAllRecetas()).withSelfRel());
    }

}
