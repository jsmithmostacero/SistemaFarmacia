import { Proveedor } from "./proveedor";
import { TipoProducto } from "./tipo-producto";

export class Producto {
    idProducto?:number;
    nombre?:string;
    descripcion?:string;
    precio?:number;
    cantidad?:number;
    productoImagenUrl?:string;
    productoImagenId?:string;
    id_proveedor?:number;
    id_tipo_producto?:number;
    tipoProducto?:TipoProducto;
    proveedor?:Proveedor;

    // private Long idProducto;
    // private String nombre;
    // private String descripcion;
    // private BigDecimal precio;
    // private int cantidad;
    // private String productoImagenUrl;
    // private String productoImagenId;
    // @ManyToOne
    // @JoinColumn(name = "id_proveedor")
    // private Proveedor proveedor;

    // @ManyToOne
    // @JoinColumn(name = "id_tipo_producto")
    // private TipoProducto tipoProducto;
}
