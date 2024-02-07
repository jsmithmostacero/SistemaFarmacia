package com.sistemaVentasFarmacia.sistemaVentasFarmacia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;
    private String nombres;
    private String apellidos;
    private String dni;
    private String cargo;
    private double sueldo;
    @OneToMany(mappedBy = "empleado")
    private List<Venta> ventas;

}
