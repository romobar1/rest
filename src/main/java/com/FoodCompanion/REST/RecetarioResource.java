package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.RecetaModelAssembler;
import com.FoodCompanion.REST.assembler.RecetarioModelAssembler;
import com.FoodCompanion.REST.model.Recetario;
import com.FoodCompanion.REST.service.RecetarioService;
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
    private final RecetarioModelAssembler recetarioModelAssembler;

    public RecetarioResource (RecetarioService recetarioService, RecetarioModelAssembler recetarioModelAssembler){
        this.recetarioService = recetarioService;
        this.recetarioModelAssembler = recetarioModelAssembler;
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

    @PostMapping("/add")
    public ResponseEntity<Recetario> addRecetario(Recetario recetario){
        Recetario recetarioAdd = recetarioService.updateRecetario(recetario);
        return new ResponseEntity<>(recetarioAdd, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Recetario> updateRecetario(Recetario recetario) {
        Recetario recetarioUpdate = recetarioService.updateRecetario(recetario);
        return new ResponseEntity<>(recetarioUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteRecetario(@PathVariable("id") Long id){
        recetarioService.deleteRecetario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}