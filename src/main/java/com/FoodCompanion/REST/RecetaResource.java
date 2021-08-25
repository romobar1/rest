package com.FoodCompanion.REST;

import com.FoodCompanion.REST.Uitlis.ResponseMessage;
import com.FoodCompanion.REST.assembler.RecetaModelAssembler;
import com.FoodCompanion.REST.model.Receta;
import com.FoodCompanion.REST.model.User;
import com.FoodCompanion.REST.service.FilesStorageServiceImpl;
import com.FoodCompanion.REST.service.RecetaService;
import com.FoodCompanion.REST.service.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;



import javax.transaction.Transactional;
import java.io.IOException;
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
    private final FilesStorageServiceImpl filesStorageService;
    public RecetaResource (
            RecetaService recetaService,
            RecetaModelAssembler recetaModelAssembler,
            UsuarioService usuarioService,
            FilesStorageServiceImpl filesStorageService){
        this.recetaService = recetaService;
        this.recetaModelAssembler = recetaModelAssembler;
        this.usuarioService = usuarioService;
        this.filesStorageService = filesStorageService;
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
    public ResponseEntity<Receta> addReceta(@RequestBody Receta receta1) throws IOException {
        String imageName = receta1.getImageURl();
        String imageUrl = filesStorageService.load(imageName).getURL().toString();
        receta1.setImageURl(imageUrl.substring(5));
        Receta receta = recetaService.addReceta(receta1);
        return new ResponseEntity<>(receta, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Receta> updateReceta(@RequestBody Receta receta1){
        Receta receta = recetaService.updateReceta(receta1);
        return new ResponseEntity<>(receta, HttpStatus.OK);
    }

    @PostMapping("/add/user/{name}")
     public ResponseEntity<Receta> addRecetaToUser(
             @PathVariable("name")String name,
             @RequestBody Receta receta
    ){
            String imageName = receta.getImageURl();
            String imageUrl = "http://localhost:8080/media/" + imageName;
            receta.setImageURl(imageUrl.substring(5));
            User usuario = usuarioService.findUsuarioByName(name);
            usuario.addRecetaToUsuario(receta);
            receta.setUsuario(usuario);
            usuarioService.updateUsuario(usuario);
            recetaService.addReceta(receta);
            return new ResponseEntity<>(receta, HttpStatus.CREATED);
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
    @GetMapping("/user/{id}")
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
