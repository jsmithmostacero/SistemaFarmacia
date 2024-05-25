import { Proveedor } from '@/models/proveedor';
import baseUrl from '@/utils/helpers';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  constructor(
    private httpClient:HttpClient
  ) { }

  public getProveedores():Observable<Proveedor[]>{
    return this.httpClient.get<Proveedor[]>(baseUrl+`proveedor/index`);
  }

  public getProveedor(id:number):Observable<Proveedor>{
    return this.httpClient.get<Proveedor>(baseUrl+`proveedor/show/`+id);
  }

}
