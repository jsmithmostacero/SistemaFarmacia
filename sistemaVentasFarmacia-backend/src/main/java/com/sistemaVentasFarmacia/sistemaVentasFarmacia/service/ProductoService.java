package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Producto;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;


    public List<Producto> obtenerTodosRecursos() {
        return productoRepository.findAll();
    }

    public ResponseEntity<Producto> obtenerRecursoPorId(Long id) {
        Optional<Producto> recurso = productoRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Producto crearRecurso(@RequestBody Producto recurso) {
        return productoRepository.save(recurso);
    }

    public Producto actualizarRecurso(@PathVariable Long id, @RequestBody Producto nuevoRecurso) {
        return productoRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setTipoProducto(nuevoRecurso.getTipoProducto());
                    recurso.setCantidad(nuevoRecurso.getCantidad());
                    recurso.setPrecio(nuevoRecurso.getPrecio());
                    recurso.setNombre(nuevoRecurso.getNombre());
                    return productoRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdProducto(id);
                    return productoRepository.save(nuevoRecurso);
                });
    }

    public ResponseEntity<Producto> eliminarRecurso(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
