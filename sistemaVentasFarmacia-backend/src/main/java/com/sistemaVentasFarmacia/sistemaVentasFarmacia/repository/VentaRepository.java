package com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {
}
