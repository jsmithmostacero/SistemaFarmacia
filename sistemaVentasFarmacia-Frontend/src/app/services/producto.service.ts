import { Producto } from '@/models/producto';
import baseUrl from '@/utils/helpers';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(
    private httpClient:HttpClient
  ) { 

  }

  public getProducto(id:number):Observable<Producto>{
    return this.httpClient.get<Producto>(baseUrl+`producto/show/`+id);
  }

  public newProducto(
    imagen: File, 
    nombre: string,
    descripcion: string,
    precio: string,
    cantidad: number,
    id_proveedor: number,
    id_tipo_producto:number
    ): Observable<any> {
    const formData = new FormData();
    formData.append('multipartFile', imagen);
    formData.append('nombre', nombre);
    formData.append('descripcion', descripcion);
    formData.append('precio', precio);
    formData.append('cantidad',cantidad.toString());
    formData.append('id_proveedor', id_proveedor.toString());
    formData.append('id_tipo_producto', id_tipo_producto.toString());
    return this.httpClient.post<any>(baseUrl + 'producto/create', formData, { responseType: 'json' });
 }

 public getProductos():Observable<Producto[]>{
  return this.httpClient.get<Producto[]>(baseUrl+`producto/index`);
}

}
