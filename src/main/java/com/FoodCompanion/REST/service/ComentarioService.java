package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.ComentarioNotFoundException;
import com.FoodCompanion.REST.model.Comentario;
import com.FoodCompanion.REST.repo.ComentarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    private final ComentarioRepo comentarioRepo;
    @Autowired
    public ComentarioService(ComentarioRepo comentarioRepo){
        this.comentarioRepo = comentarioRepo;
    }

    public Comentario addComentario(Comentario comentario){
        return comentarioRepo.save(comentario);
    }
    public List<Comentario> findAllComentarios(){
        return comentarioRepo.findAll();
    }
    public Comentario updateComentario(Comentario comentario){
        return comentarioRepo.save(comentario);
    }

    public void deleteUsuario(Long id){
         comentarioRepo.deleteComentarioById(id);
    }

    public Comentario findComentarioById(Long id){
        return comentarioRepo.findComentarioById(id).orElseThrow(()->
                new ComentarioNotFoundException("Comentario con el id " + id + " no exsiste"));
    }
}
