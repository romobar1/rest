package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.Recetario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecetarioRepo extends JpaRepository<Recetario, Long> {

    void deleteRecetarioById(Long id);

    Optional<Recetario> findRecetarioById(Long id);
}
