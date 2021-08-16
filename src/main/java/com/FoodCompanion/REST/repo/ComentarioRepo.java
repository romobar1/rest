package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepo extends JpaRepository<Comentario, Long> {


    void deleteComentarioById(Long id);

    Optional<Comentario> findComentarioById(Long id);

    List<Comentario> findComentarioByRecetaId(Long id);
}
