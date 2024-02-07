package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Venta;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ClienteService;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = {"http://Localhost:4200","http://Localhost:8080"})
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/create")
    public Venta newPersona(@RequestBody Venta nuevo){
        return ventaService.crearRecurso(nuevo);
    }
    @GetMapping("/index")
    public Iterable<Venta> getAll(){
        return ventaService.obtenerTodosRecursos();
    }

    @PostMapping("/update")
    public Venta update(@RequestParam Long id,@RequestBody Venta modificado){
        return ventaService.actualizarRecurso(id,modificado);
    }




    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Venta> delete(@PathVariable(value = "id") Long id){
        return ventaService.eliminarRecurso(id);
    }
}
