package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.UsuarioNotFoundException;
import com.FoodCompanion.REST.model.User;
import com.FoodCompanion.REST.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService   {
    private final UsuarioRepo usuarioRepo;
    @Autowired
    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public User addUser(User usuario){
       return usuarioRepo.save(usuario);
    }

    public List<User> findAllUsuarios(){
        return usuarioRepo.findAll();
    }

    public User updateUsuario(User usuario){
        return usuarioRepo.save(usuario);
    }
    public void deleteUsuario(Long id){
        usuarioRepo.deleteUserById(id);
    }
    public User findUsuarioById(Long id){
        return usuarioRepo.findUserById(id).orElseThrow(
                ()-> new UsuarioNotFoundException(" Usuario con el id " + id + " no encontrado"));
    }

}
