package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.TipoProducto;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ClienteService;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-producto")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
public class TipoProductoController {
    @Autowired
    private TipoProductoService tipoProductoService;

    @PostMapping("/create")
    public TipoProducto newPersona(@RequestBody TipoProducto nuevo){
        return tipoProductoService.crearRecurso(nuevo);
    }
    @GetMapping("/index")
    public Iterable<TipoProducto> getAll(){
        return tipoProductoService.obtenerTodosRecursos();
    }

    @PostMapping("/update")
    public TipoProducto update(@RequestParam Long id,@RequestBody TipoProducto modificado){
        return tipoProductoService.actualizarRecurso(id,modificado);
    }




    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<TipoProducto> delete(@PathVariable(value = "id") Long id){
        return tipoProductoService.eliminarRecurso(id);
    }
}
