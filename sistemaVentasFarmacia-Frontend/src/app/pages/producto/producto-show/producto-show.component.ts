import { Producto } from '@/models/producto';
import { Component, OnInit } from '@angular/core';
import { ProductoService } from '@services/producto.service';
import {CommonModule} from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButtonModule} from '@angular/material/button'; 
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-producto-show',
  standalone: true,
  imports: [
    CommonModule,
    MatGridListModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './producto-show.component.html',
  styleUrl: './producto-show.component.scss'
})
export class ProductoShowComponent implements OnInit{
  producto?: Producto

  constructor(
    private productoService:ProductoService,
    private router: Router,
    private route: ActivatedRoute,
    
  ){

  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const id = +params['id']; // Convierte el valor a número si es necesario
      this.getProducto(id);
      // Haz lo que necesites con el valor de id
    });
  }

  getProducto(id:number):void{
    this.productoService.getProducto(id).subscribe(
      data=>{
        this.producto = data
      }
      )
  }

  getPageListaProducto():void{
    this.router.navigate(['producto-lista']);
  }

}
