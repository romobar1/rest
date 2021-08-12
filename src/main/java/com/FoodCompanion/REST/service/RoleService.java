package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.RoleNotFoundException;
import com.FoodCompanion.REST.model.Role;
import com.FoodCompanion.REST.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepo roleRepo;
    @Autowired
    public RoleService(RoleRepo roleRepo){
        this.roleRepo = roleRepo;
    }
    public Role addRole(Role role){
        return roleRepo.save(role);
    }

    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }

    public Role updateRole(Role role){
        return roleRepo.save(role);
    }

    public void deleteRole(Long id){
        roleRepo.deleteRoleById(id);
    }
    public Role getRoleById(Long id){
        return roleRepo.findRoleById(id).orElseThrow(()->
                new RoleNotFoundException("El role con el id " + id + "no existe")
                );
    }
}
