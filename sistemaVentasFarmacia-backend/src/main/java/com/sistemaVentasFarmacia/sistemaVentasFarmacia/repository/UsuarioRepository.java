package com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
