package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Usuario;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> obtenerTodosRecursos() {
        return usuarioRepository.findAll();
    }

    public ResponseEntity<Usuario> obtenerRecursoPorId(@PathVariable Long id) {
        Optional<Usuario> recurso = usuarioRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Usuario crearRecurso(@RequestBody Usuario recurso) {
        return usuarioRepository.save(recurso);
    }

    public Usuario actualizarRecurso(@PathVariable Long id, @RequestBody Usuario nuevoRecurso) {
        return usuarioRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setUsername(nuevoRecurso.getUsername());
                    recurso.setContrasenia(nuevoRecurso.getContrasenia());
                    return usuarioRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdUsuario(id);
                    return usuarioRepository.save(nuevoRecurso);
                });
    }

    public ResponseEntity<Usuario> eliminarRecurso(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
