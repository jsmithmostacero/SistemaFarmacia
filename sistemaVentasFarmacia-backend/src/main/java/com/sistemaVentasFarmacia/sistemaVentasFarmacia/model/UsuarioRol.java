package com.sistemaVentasFarmacia.sistemaVentasFarmacia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="usuarioRoles")
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuarioRol;

    @ManyToOne(fetch = FetchType.EAGER )
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER )
    private Rol rol;
}
