package com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
}
