package com.FoodCompanion.REST.repo;

import com.FoodCompanion.REST.model.ERole;
import com.FoodCompanion.REST.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
