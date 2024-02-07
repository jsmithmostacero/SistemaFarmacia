package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Rol;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ClienteService;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rol")
@CrossOrigin(origins = {"http://Localhost:4200","http://Localhost:8080"})
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping("/create")
    public Rol newRecurso(@RequestBody Rol nuevo){
        return rolService.crearRecurso(nuevo);
    }
    @GetMapping("/index")
    public Iterable<Rol> getAll(){
        return rolService.obtenerTodosRecursos();
    }

    @PostMapping("/update")
    public Rol update(@RequestParam Long id,@RequestBody Rol modificado){
        return rolService.actualizarRecurso(id,modificado);
    }




    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Rol> delete(@PathVariable(value = "id") Long id){
        return rolService.eliminarRecurso(id);
    }
}
