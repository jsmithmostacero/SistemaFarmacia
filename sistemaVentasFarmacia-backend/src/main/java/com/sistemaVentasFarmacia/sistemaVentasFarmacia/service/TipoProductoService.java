package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.TipoProducto;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class TipoProductoService {
    @Autowired
    private TipoProductoRepository tipoProductoRepository;


    public List<TipoProducto> obtenerTodosRecursos() {
        return tipoProductoRepository.findAll();
    }

    public ResponseEntity<TipoProducto> obtenerRecursoPorId(Long id) {
        Optional<TipoProducto> recurso = tipoProductoRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public TipoProducto crearRecurso(TipoProducto recurso) {
        return tipoProductoRepository.save(recurso);
    }

    public TipoProducto actualizarRecurso(Long id,TipoProducto nuevoRecurso) {
        return tipoProductoRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setNombre(nuevoRecurso.getNombre());
                    recurso.setDescripcion(nuevoRecurso.getDescripcion());
                    return tipoProductoRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdTipoProducto(id);
                    return tipoProductoRepository.save(nuevoRecurso);
                });
    }

    public ResponseEntity<TipoProducto> eliminarRecurso(Long id) {
        tipoProductoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
