package com.FoodCompanion.REST.service;

import com.FoodCompanion.REST.exception.UsuarioNotFoundException;
import com.FoodCompanion.REST.model.Usuario;
import com.FoodCompanion.REST.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService  {
    private final UsuarioRepo usuarioRepo;
    @Autowired
    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario addUser(Usuario usuario){
       return usuarioRepo.save(usuario);
    }

    public List<Usuario> findAllUsuarios(){
        return usuarioRepo.findAll();
    }

    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }
    public void deleteUsuario(Long id){
        usuarioRepo.deleteUsuarioById(id);
    }
    public Usuario findUsuarioById(Long id){
        return usuarioRepo.findUsuarioById(id).orElseThrow(
                ()-> new UsuarioNotFoundException(" Usuario con el id " + id + " no encontrado"));
    }

    public Usuario findUsuarioByUserName(String name) {
        return usuarioRepo.findUsuarioByName(name).orElseThrow(
                ()-> new UsuarioNotFoundException(" Usuario con el id " + name + " no encontrado"));
    }
}
