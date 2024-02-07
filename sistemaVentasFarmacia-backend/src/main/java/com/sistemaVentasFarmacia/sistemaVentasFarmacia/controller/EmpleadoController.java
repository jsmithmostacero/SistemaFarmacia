package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Empleado;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ClienteService;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleoado")
@CrossOrigin(origins = {"http://Localhost:4200","http://Localhost:8080"})
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/create")
    public Empleado newRecurso(@RequestBody Empleado nuevo){
        return empleadoService.crearRecurso(nuevo);
    }
    @GetMapping("/index")
    public Iterable<Empleado> getAll(){
        return empleadoService.obtenerTodosRecursos();
    }

    @PostMapping("/update")
    public Empleado update(@RequestParam Long id,@RequestBody Empleado modificado){
        return empleadoService.actualizarRecurso(id,modificado);
    }




    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Empleado> delete(@PathVariable(value = "id") Long id){
        return empleadoService.eliminarRecurso(id);
    }
}
