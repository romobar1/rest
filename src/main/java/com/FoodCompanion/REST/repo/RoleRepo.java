package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {

    void deleteRoleById(Long id);

    Optional<Role> findRoleById(Long id);
}
