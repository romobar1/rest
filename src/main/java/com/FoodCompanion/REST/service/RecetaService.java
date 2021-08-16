package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.RecetaNotFoundException;
import com.FoodCompanion.REST.model.Comentario;
import com.FoodCompanion.REST.model.Receta;
import com.FoodCompanion.REST.repo.RecetaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {
    private final RecetaRepo recetaRepo;
    @Autowired
    public RecetaService(RecetaRepo recetaRepo){
        this.recetaRepo = recetaRepo;
    }
    public Receta addReceta(Receta receta){
        return recetaRepo.save(receta);
    }

    public List<Receta> findAllRecetas(){
        return recetaRepo.findAll();
    }

    public Receta updateReceta(Receta receta){
        return recetaRepo.save(receta);
    }

    public void deleteReceta(Long id){
        recetaRepo.deleteRecetaById(id);
    }
    public Receta findRecetaById(Long id){
        return recetaRepo.findReceteById(id).orElseThrow(() ->
                new RecetaNotFoundException("La receta con el id " + id + "no existe"));
    }

    public List<Receta> findRecetasFromRecetario(Long id){
        return recetaRepo.findRecetaFromRecetarioById(id);
    }

    public List<Receta> findRecetasFromUser(Long id) {
        return recetaRepo.findRecetaFromUsuarioById(id);
    }
}
