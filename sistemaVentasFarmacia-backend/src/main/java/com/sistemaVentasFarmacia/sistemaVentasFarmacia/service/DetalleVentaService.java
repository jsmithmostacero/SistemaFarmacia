package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.DetalleVenta;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;


    public List<DetalleVenta> obtenerTodosRecursos() {
        return detalleVentaRepository.findAll();
    }

    public ResponseEntity<DetalleVenta> obtenerRecursoPorId(@PathVariable Long id) {
        Optional<DetalleVenta> recurso = detalleVentaRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public DetalleVenta crearRecurso(@RequestBody DetalleVenta recurso) {
        return detalleVentaRepository.save(recurso);
    }

    public DetalleVenta actualizarRecurso(@PathVariable Long id, @RequestBody DetalleVenta nuevoRecurso) {
        return detalleVentaRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setCantidadVendida(nuevoRecurso.getCantidadVendida());
                    recurso.setProducto(nuevoRecurso.getProducto());
                    recurso.setPrecioUnitario(nuevoRecurso.getPrecioUnitario());
                    recurso.setSubtotal(nuevoRecurso.getSubtotal());
                    return detalleVentaRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdDetalleVenta(id);
                    return detalleVentaRepository.save(nuevoRecurso);
                });
    }

    public ResponseEntity<DetalleVenta> eliminarRecurso(@PathVariable Long id) {
        detalleVentaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
