import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {NgxMatFileInputModule} from '@angular-material-components/file-input';
import {CommonModule} from '@angular/common';
import {
    FormBuilder,
    FormGroup,
    Validators,
    FormsModule,
    ReactiveFormsModule
} from '@angular/forms';
//MODELS
import {Proveedor} from '@/models/proveedor';
import {Producto} from '@/models/producto';
import {TipoProducto} from '@/models/tipo-producto';

import {Observable, catchError, forkJoin, map, of} from 'rxjs';

//SERVICES
import {ProveedorService} from '@services/proveedor.service';
import {ProductoService} from '@services/producto.service';
import {TipoProductoService} from '@services/tipo-producto.service';

import Swal from 'sweetalert2';
import {NgxSpinnerModule, NgxSpinnerService} from 'ngx-spinner';
// import { ToastrModule,ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
    selector: 'app-producto-actualizar',
    standalone: true,
    imports: [
        MatFormFieldModule,
        MatInputModule,
        MatSelectModule,
        MatButtonModule,
        NgxMatFileInputModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        NgxSpinnerModule
        // ToastrModule
    ],
    templateUrl: './producto-actualizar.component.html',
    styleUrl: './producto-actualizar.component.scss'
})
export class ProductoActualizarComponent implements OnInit {
    id?: number;
    tiposProductos: TipoProducto[] = [];
    proveedores: Proveedor[] = [];
    producto?: Producto;
    imagen: File | null = null;
    imagenMin: string;
    formularioProducto: FormGroup;
    proveedor?: Proveedor;
    tipoProducto?: TipoProducto;

    @ViewChild('imagenInputFile', {static: false}) imagenInputFile!: ElementRef;

    selectedTipoProducto: number | undefined;
    selectedProveedor: number | undefined;

    constructor(
        private tipoProductoService: TipoProductoService,
        private proveedorService: ProveedorService,
        private productoService: ProductoService,
        private fb: FormBuilder, // Inyecta FormBuilder en el constructo,
        private spinner: NgxSpinnerService,
        private toastr: ToastrService, //Inyección para msj de error,
        private router: Router,
        private route: ActivatedRoute
    ) {
        this.formularioProducto = this.fb.group({
            image: ['', Validators.required],
            nombre: ['', Validators.required],
            descripcion: ['', Validators.required],
            precio: [null, [Validators.required, Validators.min(0.01)]],
            cantidad: [null, [Validators.required, Validators.min(1)]],
            tipo_producto: ['', Validators.required],
            tipo_proveedor: ['', Validators.required]
            // Agrega más campos según sea necesario
        });
    }

    ngOnInit(): void {
        this.route.params.subscribe((params) => {
            this.id = +params['id'];
            this.getProducto(this.id);
        });
        this.getProveedores();
        this.getTiposProductos();
    }

    actualizarFormularioConProducto(): void {
        if (this.producto) {
            this.formularioProducto.patchValue({
                nombre: this.producto.nombre,
                descripcion: this.producto.descripcion,
                precio: this.producto.precio,
                cantidad: this.producto.cantidad
                // tipo_producto: this.producto.tipoProducto.nombre,
                // tipo_proveedor: this.producto.proveedor.nombre
                // Actualiza más campos según sea necesario
            });
            this.formularioProducto
                .get('tipo_producto')
                ?.setValue(this.producto.tipoProducto.idTipoProducto);
            this.formularioProducto
                .get('tipo_proveedor')
                ?.setValue(this.producto.proveedor.idProveedor);
            this.imagenMin = this.producto.productoImagenUrl;

            //Inicializar variables
            this.selectedProveedor = this.producto.proveedor.idProveedor;
            this.selectedTipoProducto =
                this.producto.tipoProducto.idTipoProducto;

            //alert("Proveedor: "+this.selectedProveedor+"\n\nTipo producto: "+this.selectedTipoProducto);
        }
    }

    getProducto(id: number): void {
        this.productoService.getProducto(id).subscribe((data) => {
            this.producto = data;
            this.actualizarFormularioConProducto();
        });
    }

    getTiposProductos(): void {
        this.tipoProductoService.getTiposProductos().subscribe(
            (data) => {
                this.tiposProductos = data;
            },
            (error) => {
                console.error(
                    'Ocurrió un error al obtener los tipo productos:',
                    error
                );
            }
        );
    }

    getProveedores(): void {
        this.proveedorService.getProveedores().subscribe(
            (data) => {
                this.proveedores = data;
            },
            (error) => {
                console.error(
                    'Ocurrió un error al obtener los proveedores:',
                    error
                );
            }
        );
    }

    onFileChange(event: any) {
        this.imagen = event.target.files[0];
        this.imagenMin = null;
        const fr = new FileReader();
        fr.onload = (evento: any) => {
            this.imagenMin = evento.target.result;
        };
        if (this.imagen) {
            fr.readAsDataURL(this.imagen);
        }
    }

    obtenerInformacionEditada(): Observable<Producto> {
        const nombre = this.formularioProducto.get('nombre')?.value;
        const descripcion = this.formularioProducto.get('descripcion')?.value;
        const precio = this.formularioProducto.get('precio')?.value;
        const cantidad = this.formularioProducto.get('cantidad')?.value;
      
        // Combina las llamadas asíncronas para obtener proveedor y tipo de producto
        return forkJoin([
          this.proveedorService.getProveedor(this.selectedProveedor),
          this.tipoProductoService.getTipoProducto(this.selectedTipoProducto)
        ]).pipe(
          map(([proveedor, tipoProducto]) => {
            // Crea un objeto Producto con la información obtenida
            const productoEditado: Producto = {
              nombre: nombre,
              descripcion: descripcion,
              precio: precio,
              cantidad: cantidad,
              proveedor: proveedor,
              tipoProducto: tipoProducto,
              productoImagenId: this.producto.productoImagenId,
              productoImagenUrl: this.producto.productoImagenUrl,
            };
            return productoEditado;
          }),
          catchError(error => {
            console.error('Error al obtener proveedor o tipo de producto:', error);
            return of(null);
          })
        );
      }

      actualizaProducto(): void {
        if (!this.formularioProducto.valid) {
          this.toastr.error('Complete correctamente los campos', 'Error!');
          return;
        }
      
        this.spinner.show();
        this.obtenerInformacionEditada().subscribe(
          (data) => {
            this.producto = data;
            this.productoService.updateNotImage(this.id,this.producto).subscribe(
                (data)=>{
                    Swal.fire({
                        title: 'Producto Actualizado!',
                        text: 'Se actualizó correctamente el producto ' + data.nombre,
                        icon: 'success'
                      });
                      this.spinner.hide();
                      this.imagen = null;
                      this.router.navigate(['producto-lista']);
                },
                (err) => {
                  this.spinner.hide();
                  console.error('Error digitado: ' + err);
                }
            )
            // Manejar la respuesta del observable aquí
          },
          (err) => {
            this.spinner.hide();
            console.error('Error digitado: ' + err);
          }
        );
      }
      
      
}
