package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.Comentario;
import com.FoodCompanion.REST.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecetaRepo extends JpaRepository<Receta, Long> {

    void deleteRecetaById(Long id);

    Optional<Receta> findReceteById(Long id);

    List<Receta> findRecetaFromRecetarioById(Long id);

    List<Receta> findRecetaFromUsuarioById(Long id);
}
