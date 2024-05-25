package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Producto;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Proveedor;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ClienteService;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = {"http://Localhost:4200","http://Localhost:8090"})
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/create")
    public Proveedor newRecurso(@RequestBody Proveedor nuevo){
        return proveedorService.crearRecurso(nuevo);
    }
    @GetMapping("/index")
    public Iterable<Proveedor> getAll(){
        return proveedorService.obtenerTodosRecursos();
    }

    @GetMapping(value = "/show/{id}")
    public ResponseEntity<Proveedor> getShowRecurso(@PathVariable Long id) {
        return proveedorService.obtenerRecursoPorId(id);
    }

    @PostMapping("/update")
    public Proveedor update(@RequestParam Long id,@RequestBody Proveedor modificado){
        return proveedorService.actualizarRecurso(id,modificado);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Proveedor> delete(@PathVariable(value = "id") Long id){
        return proveedorService.eliminarRecurso(id);
    }
}
