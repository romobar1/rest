package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.RecetarioNotFoundException;
import com.FoodCompanion.REST.model.Receta;
import com.FoodCompanion.REST.model.Recetario;
import com.FoodCompanion.REST.repo.RecetarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetarioService {
    private final RecetarioRepo recetarioRepo;
    @Autowired
    public RecetarioService (RecetarioRepo recetarioRepo){
        this.recetarioRepo = recetarioRepo;
    }

    public Recetario addRecetario(Recetario recetario){
        return recetarioRepo.save(recetario);
    }

    public List<Recetario> findAllRecetarios(){
        return recetarioRepo.findAll();
    }

    public Recetario updateRecetario(Recetario recetario){
        return recetarioRepo.save(recetario);
    }
    public void deleteRecetario(Long id){
        recetarioRepo.deleteRecetarioById(id);
    }

    public Recetario findRecetarioById(Long id){
        return recetarioRepo.findRecetarioById(id).orElseThrow(()->
                new RecetarioNotFoundException("El recetario con el id " + id + " no existe")
                );
    }

    public List<Recetario> findRecetariosOfUser(Long id){
        return recetarioRepo.findRecetarioByUsuarioId(id);
    }

}
