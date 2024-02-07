import {AppState} from '@/store/state';
import {UiState} from '@/store/ui/state';
import {Component, HostBinding, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import {AppService} from '@services/app.service';
import {Observable} from 'rxjs';

const BASE_CLASSES = 'main-sidebar elevation-4';
@Component({
    selector: 'app-menu-sidebar',
    templateUrl: './menu-sidebar.component.html',
    styleUrls: ['./menu-sidebar.component.scss']
})
export class MenuSidebarComponent implements OnInit {
    @HostBinding('class') classes: string = BASE_CLASSES;
    public ui: Observable<UiState>;
    public user;
    public menu = MENU;

    constructor(
        public appService: AppService,
        private store: Store<AppState>
    ) {}

    ngOnInit() {
        this.ui = this.store.select('ui');
        this.ui.subscribe((state: UiState) => {
            this.classes = `${BASE_CLASSES} ${state.sidebarSkin}`;
        });
        this.user = this.appService.user;
        console.log(this.user);
    }
}

export const MENU = [
    {
        name: 'Panel del sistema',
        iconClasses: 'fas fa-tachometer-alt',
        path: ['/']
    },
    {
        name: 'Registrar Ventas',
        iconClasses: 'fa-solid fa-cart-shopping',
        path: ['/blank']
    },
    {
        name: 'Mantenedor Productos',
        iconClasses: 'fa-solid fa-bars',
        children: [
            {
                name: 'Listar Productos',
                iconClasses: 'fa-regular fa-rectangle-list',
                path: ['/producto-lista']
            },
            {
                name: 'Registrar Producto',
                iconClasses: 'fa-solid fa-pills',
                path: ['/producto-nuevo']
            },
            {
                name: 'Actualizar Producto',
                iconClasses: 'fa-solid fa-square-pen',
                path: ['/producto-actualizar']
            }
            
        ]
    }
];
