package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Proveedor;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;


    public List<Proveedor> obtenerTodosRecursos() {
        return proveedorRepository.findAll();
    }

    public ResponseEntity<Proveedor> obtenerRecursoPorId(@PathVariable Long id) {
        Optional<Proveedor> recurso = proveedorRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Proveedor crearRecurso(@RequestBody Proveedor recurso) {
        return proveedorRepository.save(recurso);
    }


    public Proveedor actualizarRecurso(@PathVariable Long id, @RequestBody Proveedor nuevoRecurso) {
        return proveedorRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setContacto(nuevoRecurso.getContacto());
                    recurso.setNombre(nuevoRecurso.getNombre());
                    return proveedorRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdProveedor(id);
                    return proveedorRepository.save(nuevoRecurso);
                });
    }


    public ResponseEntity<Proveedor> eliminarRecurso(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
