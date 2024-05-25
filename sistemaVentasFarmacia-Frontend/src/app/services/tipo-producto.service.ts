import { TipoProducto } from '@/models/tipo-producto';
import baseUrl from '@/utils/helpers';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TipoProductoService {

  constructor(
      private httpClient:HttpClient

      ) { }

    
      public getTiposProductos():Observable<TipoProducto[]>{
        return this.httpClient.get<TipoProducto[]>(baseUrl+`tipo-producto/index`);
      }

      public getTipoProducto(id:number):Observable<TipoProducto>{
        return this.httpClient.get<TipoProducto>(baseUrl+`tipo-producto/show/`+id);
      }
}
