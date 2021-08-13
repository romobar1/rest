package com.FoodCompanion.REST;

import com.FoodCompanion.REST.assembler.ComentarioModelAssenbler;
import com.FoodCompanion.REST.model.Comentario;
import com.FoodCompanion.REST.service.ComentarioService;
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
@RequestMapping("/comentario")
public class ComentarioResource {
    private final ComentarioService comentarioService;
    private final ComentarioModelAssenbler comentarioModelAssenbler;
    public ComentarioResource(ComentarioService comentarioService,ComentarioModelAssenbler comentarioModelAssenbler){
        this.comentarioService = comentarioService;
        this.comentarioModelAssenbler = comentarioModelAssenbler;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Comentario>> getAllComentarios(){
        List<EntityModel<Comentario>> comentarios =
                comentarioService.findAllComentarios().stream()
                        .map(comentarioModelAssenbler::toModel)
                        .collect(Collectors.toList());
        return CollectionModel.of(comentarios,
                linkTo(methodOn(ComentarioResource.class).getAllComentarios()).withSelfRel());
     }

     @GetMapping("/find/{id}")
    public EntityModel<Comentario> getComentarioById(@PathVariable("id") Long id){
        Comentario comentario = comentarioService.findComentarioById(id);
        return comentarioModelAssenbler.toModel(comentario);
     }

     @PostMapping("/add")
    public ResponseEntity<Comentario> addComentario(@RequestBody Comentario comentario1){
        Comentario comentario = comentarioService.addComentario(comentario1);
        return new ResponseEntity<>(comentario, HttpStatus.CREATED);
     }

     @PutMapping("/update")
    public ResponseEntity<Comentario> updateComentario(@RequestBody Comentario comentario1){
        Comentario comentario = comentarioService.updateComentario(comentario1);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
     }

     @DeleteMapping("/delete/{id}")
     @Transactional
    public ResponseEntity<?> deleteComentario(@PathVariable("id") Long id){
        comentarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
     }

}
