package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Empleado;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;


    public List<Empleado> obtenerTodosRecursos() {
        return empleadoRepository.findAll();
    }

    public ResponseEntity<Empleado> obtenerRecursoPorId(@PathVariable Long id) {
        Optional<Empleado> recurso = empleadoRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Empleado crearRecurso(@RequestBody Empleado recurso) {
        return empleadoRepository.save(recurso);
    }

    public Empleado actualizarRecurso(@PathVariable Long id, @RequestBody Empleado nuevoRecurso) {
        return empleadoRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setNombres(nuevoRecurso.getNombres());
                    recurso.setApellidos(nuevoRecurso.getApellidos());
                    return empleadoRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdEmpleado(id);
                    return empleadoRepository.save(nuevoRecurso);
                });
    }

    public ResponseEntity<Empleado> eliminarRecurso(@PathVariable Long id) {
        empleadoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
