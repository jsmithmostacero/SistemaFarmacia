package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.DetalleSuministro;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.DetalleSuministroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleSuministroService {
    @Autowired
    private DetalleSuministroRepository detalleSuministroRepository;


    public List<DetalleSuministro> obtenerTodosRecursos() {
        return detalleSuministroRepository.findAll();
    }

    public ResponseEntity<DetalleSuministro> obtenerRecursoPorId(@PathVariable Long id) {
        Optional<DetalleSuministro> recurso = detalleSuministroRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public DetalleSuministro crearRecurso(@RequestBody DetalleSuministro recurso) {
        return detalleSuministroRepository.save(recurso);
    }

    public DetalleSuministro actualizarRecurso(@PathVariable Long id, @RequestBody DetalleSuministro nuevoRecurso) {
        return detalleSuministroRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setProducto(nuevoRecurso.getProducto());
                    recurso.setProveedor(nuevoRecurso.getProveedor());
                     recurso.setMontoTotal(nuevoRecurso.getMontoTotal());
                      recurso.setCantidadSuministrada(nuevoRecurso.getCantidadSuministrada());
                    return detalleSuministroRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdDetalle(id);
                    return detalleSuministroRepository.save(nuevoRecurso);
                });
    }

    public ResponseEntity<DetalleSuministro> eliminarRecurso(@PathVariable Long id) {
        detalleSuministroRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
