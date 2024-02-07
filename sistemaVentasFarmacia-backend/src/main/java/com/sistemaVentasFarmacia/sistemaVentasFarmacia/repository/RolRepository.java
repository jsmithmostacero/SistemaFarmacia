package com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
}
