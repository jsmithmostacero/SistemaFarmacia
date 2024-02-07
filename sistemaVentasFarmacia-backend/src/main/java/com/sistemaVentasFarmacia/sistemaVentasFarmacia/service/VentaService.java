package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Venta;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;


    public List<Venta> obtenerTodosRecursos() {
        return ventaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerRecursoPorId(@PathVariable Long id) {
        Optional<Venta> recurso = ventaRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venta crearRecurso(@RequestBody Venta recurso) {
        return ventaRepository.save(recurso);
    }

    @PutMapping("/{id}")
    public Venta actualizarRecurso(@PathVariable Long id, @RequestBody Venta nuevoRecurso) {
        return ventaRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setCliente(nuevoRecurso.getCliente());
                    recurso.setMetodoPago(nuevoRecurso.getMetodoPago());
                    recurso.setDetallesVenta(nuevoRecurso.getDetallesVenta());
                    recurso.setTotalVenta(nuevoRecurso.getTotalVenta());
                    return ventaRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdVenta(id);
                    return ventaRepository.save(nuevoRecurso);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Venta> eliminarRecurso(@PathVariable Long id) {
        ventaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
