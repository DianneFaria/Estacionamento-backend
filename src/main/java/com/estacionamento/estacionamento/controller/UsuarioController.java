package com.estacionamento.estacionamento.controller;

import com.estacionamento.estacionamento.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estacionamento.estacionamento.repository.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            repository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity criar(@RequestBody Usuario usuario) {
        try {
            repository.save(usuario);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Usuario usuario, @PathVariable Long id) {
        try {
            Usuario usuarioUpdate = repository.getReferenceById(id);
            usuarioUpdate.setNome(usuario.getNome());
            usuarioUpdate.setIdade(usuario.getIdade());
            repository.save(usuarioUpdate);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarById(@PathVariable Long id) {
        Usuario usuario = repository.findById(id).orElse(null);
        if(usuario == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);

    }

}