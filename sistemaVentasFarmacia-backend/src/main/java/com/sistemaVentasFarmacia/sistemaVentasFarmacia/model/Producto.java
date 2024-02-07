package com.sistemaVentasFarmacia.sistemaVentasFarmacia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int cantidad;
    private String productoImagenUrl;
    private String productoImagenId;
    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto")
    private TipoProducto tipoProducto;

    public Producto(String nombre,String descripcion,BigDecimal precio,
                    int cantidad, String productoImagenUrl,
                    String productoImagenId,Long idProveedor,Long idTipoProducto
                    ){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.productoImagenUrl = productoImagenUrl;
        this.productoImagenId = productoImagenId;
        this.proveedor = new Proveedor();
        proveedor.setIdProveedor(idProveedor);
        this.tipoProducto = new TipoProducto();
        tipoProducto.setIdTipoProducto(idTipoProducto);
    }

}
