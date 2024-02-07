package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Usuario;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ClienteService;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"http://Localhost:4200","http://Localhost:8080"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/create")
    public Usuario newPersona(@RequestBody Usuario nuevo){
        return usuarioService.crearRecurso(nuevo);
    }
    @GetMapping("/index")
    public Iterable<Usuario> getAll(){
        return usuarioService.obtenerTodosRecursos();
    }

    @PostMapping("/update")
    public Usuario update(@RequestParam Long id,@RequestBody Usuario modificado){
        return usuarioService.actualizarRecurso(id,modificado);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable(value = "id") Long id){
        return usuarioService.eliminarRecurso(id);
    }
}
