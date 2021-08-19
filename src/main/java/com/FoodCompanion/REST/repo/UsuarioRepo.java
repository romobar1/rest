package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

    void deleteUsuarioById(Long Id);

    Optional<Usuario> findUsuarioById(Long Id);

    Optional<Usuario> findUsuarioByName(String name);


}
