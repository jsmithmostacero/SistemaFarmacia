package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = {"http://Localhost:4200","http://Localhost:8080"})

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/reniec/{dni}")
    public Cliente getReniecData(@PathVariable String dni) {
        return clienteService.getReniecData(dni);
    }
    @PostMapping("/create")
    public Cliente newRecurso(@RequestBody Cliente nuevo){
        return clienteService.crearRecurso(nuevo);
    }
    @GetMapping("/index")
    public Iterable<Cliente> getAll(){
        return clienteService.obtenerTodosRecursos();
    }

    @PostMapping("/update")
    public Cliente update(@RequestParam Long id,@RequestBody Cliente modificado){
        return clienteService.actualizarRecurso(id,modificado);
    }




    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable(value = "id") Long id){
        return clienteService.eliminarRecurso(id);
    }

}
