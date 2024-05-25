import { Producto } from '@/models/producto';
import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { ProductoService } from '@services/producto.service';
import { ToastrModule,ToastrService} from 'ngx-toastr';
import {CommonModule} from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { Router } from '@angular/router';
import {NgxSpinnerModule, NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-producto-lista',
  standalone: true,
  imports: [
    MatPaginatorModule,
    MatTableModule,
    ToastrModule,
    CommonModule,
    MatIconModule,
    MatButtonModule,
    NgxSpinnerModule
  ],
  templateUrl: './producto-lista.component.html',
  styleUrl: './producto-lista.component.scss'
})
export class ProductoListaComponent implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['idProducto', 'nombre', 'imagen', 'precio','cantidad','acciones'];
  dataSource = new MatTableDataSource<Producto>();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  productos: Producto[]=[];
  // clickedRows = new Set<Producto>();
  constructor(
    private productoService:ProductoService,
    private toastr: ToastrService,//Inyección para msj de error
    private router: Router,
    private spinner: NgxSpinnerService,
  ){

  }
  ngOnInit(): void {

  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.getProductos();
    // this.productos.forEach(producto => this.clickedRows.add(producto));

    }
  

  getProductos():void{
    this.productoService.getProductos().subscribe(
      data=>{
        this.productos = data;
        this.dataSource.data = this.productos;
      },
      error=>{
        this.toastr.error('Ocurrió un error al mostrar los productos', 'Error!');
      }
    );
  }

  getProductoShow(id:number):void{
    this.spinner.show();
    this.router.navigate(['producto-show',id]);
    this.spinner.hide();
  }

  getProductoEdit(id:number):void{
    this.spinner.show();
    this.router.navigate(['producto-actualizar',id]);
    this.spinner.hide();
  }
}

