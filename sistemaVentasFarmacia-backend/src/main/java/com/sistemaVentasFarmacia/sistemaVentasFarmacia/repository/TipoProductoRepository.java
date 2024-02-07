package com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto,Long> {

}
