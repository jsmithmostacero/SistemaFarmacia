package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.DetalleSuministro;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ClienteService;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.DetalleSuministroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalle-suminsitro")
@CrossOrigin(origins = {"http://Localhost:4200","http://Localhost:8080"})
public class DetalleSuministroController {
    @Autowired
    private DetalleSuministroService detalleSuministroService;

    @PostMapping("/create")
    public DetalleSuministro newRecruso(@RequestBody DetalleSuministro nuevo){
        return detalleSuministroService.crearRecurso(nuevo);
    }
    @GetMapping("/index")
    public Iterable<DetalleSuministro> getAll(){
        return detalleSuministroService.obtenerTodosRecursos();
    }

    @PostMapping("/update")
    public DetalleSuministro update(@RequestParam Long id,@RequestBody DetalleSuministro modificado){
        return detalleSuministroService.actualizarRecurso(id,modificado);
    }




    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<DetalleSuministro> delete(@PathVariable(value = "id") Long id){
        return detalleSuministroService.eliminarRecurso(id);
    }
}
