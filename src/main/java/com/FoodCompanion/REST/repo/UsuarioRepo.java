package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<User, Long> {

    void deleteUserById(Long Id);

    Optional<User> findUserById(Long Id);

    Optional<User> findByUsername(String username);


}
