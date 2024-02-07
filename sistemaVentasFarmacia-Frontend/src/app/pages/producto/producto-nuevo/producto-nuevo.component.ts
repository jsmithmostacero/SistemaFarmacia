import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {NgxMatFileInputModule} from '@angular-material-components/file-input';
import {TipoProducto} from '@/models/tipo-producto';
import {TipoProductoService} from '@services/tipo-producto.service';
import {CommonModule} from '@angular/common';
import {Proveedor} from '@/models/proveedor';
import {ProveedorService} from '@services/proveedor.service';
import {
    FormBuilder,
    FormGroup,
    Validators,
    FormsModule,
    ReactiveFormsModule,
    AbstractControl,
    FormGroupDirective,
    NgForm,
    FormControl
} from '@angular/forms';
import {ProductoService} from '@services/producto.service';
import {Producto} from '@/models/producto';
import Swal from 'sweetalert2';
import {NgxSpinnerModule, NgxSpinnerService} from 'ngx-spinner';
import { ToastrModule,ToastrService} from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
    selector: 'app-producto-nuevo',
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
        NgxSpinnerModule,
        ToastrModule
    ],
    templateUrl: './producto-nuevo.component.html',
    styleUrl: './producto-nuevo.component.scss'
})
export class ProductoNuevoComponent implements OnInit {
    tiposProductos: TipoProducto[] = [];
    proveedores: Proveedor[] = [];
    productos: Producto[] = [];

    selectedTipoProducto: number | undefined;
    selectedProveedor: number | undefined;
    imagen: File | null = null;
    imagenMin: File | null = null;
    formularioProducto: FormGroup;
    @ViewChild('imagenInputFile', {static: false}) imagenInputFile!: ElementRef;

    constructor(
        private tipoProductoService: TipoProductoService,
        private proveedorService: ProveedorService,
        private productoService: ProductoService,
        private fb: FormBuilder, // Inyecta FormBuilder en el constructo,
        private spinner: NgxSpinnerService,
        private toastr: ToastrService,//Inyección para msj de error,
        private router: Router,
            ) {
        this.formularioProducto = this.fb.group({
          image: [null, Validators.required],
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
        this.getTiposProductos();
        this.getProveedores();
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

    onUpload(): void {
        if(!this.imagen){
          this.toastr.error('Seleccione una imagen', 'Error!');
        }
        // if(!this.formularioProducto.valid){
        //   this.toastr.error('Complete correctamente los campos', 'Error!');
        // }
        if (this.imagen && this.formularioProducto.valid) {
          this.spinner.show();
            this.productoService
                .newProducto(
                    this.imagen,
                    this.formularioProducto.get('nombre')?.value,
                    this.formularioProducto.get('descripcion')?.value,
                    this.formularioProducto.get('precio')?.value,
                    this.formularioProducto.get('cantidad')?.value,
                    this.selectedTipoProducto,
                    this.selectedProveedor
                )
                .subscribe(
                    (data) => {
                        Swal.fire({
                            title: 'Producto Registrado!',
                            text:
                                'Se registró correctamente el producto ' +
                                this.formularioProducto.get('nombre')?.value,
                            icon: 'success'
                        });
                        this.productos = data;
                        this.spinner.hide();
                        this.imagen=null;
                        this.router.navigate(['producto-lista']);
                    },
                    (err) => {
                        this.spinner.hide();
                        console.error('Error digitado: ' + err); // Puedes imprimir el error en la consola
                    }
                );
        }
    }
    
}