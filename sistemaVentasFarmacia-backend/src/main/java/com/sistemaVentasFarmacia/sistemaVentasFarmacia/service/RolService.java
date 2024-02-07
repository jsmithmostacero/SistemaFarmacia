package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Rol;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private RolRepository repository;


    public List<Rol> obtenerTodosRecursos() {
        return repository.findAll();
    }

    public ResponseEntity<Rol> obtenerRecursoPorId(@PathVariable Long id) {
        Optional<Rol> recurso = repository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    public Rol crearRecurso(@RequestBody Rol recurso) {
        return repository.save(recurso);
    }

    public Rol actualizarRecurso(@PathVariable Long id, @RequestBody Rol nuevoRecurso) {
        return repository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setNombre(nuevoRecurso.getNombre());
                    return repository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdRol(id);
                    return repository.save(nuevoRecurso);
                });
    }

    public ResponseEntity<Rol> eliminarRecurso(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
