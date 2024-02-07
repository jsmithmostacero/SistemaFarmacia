package com.sistemaVentasFarmacia.sistemaVentasFarmacia.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne
    @JoinColumn(name = "id_empleado") // Agregamos la relación con Empleado
    private Empleado empleado;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente") // Agregamos la relación con Cliente
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detallesVenta;

    private Date fechaVenta;
    private double totalVenta;
    private String metodoPago;
    // Otros atributos relacionados con la venta

    // Constructores, getters y setters
}
