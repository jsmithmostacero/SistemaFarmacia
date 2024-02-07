package com.sistemaVentasFarmacia.sistemaVentasFarmacia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Producto;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.CloudinaryService;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
public class ProductoController
{
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    private ProductoService productoService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> newRecurso(@RequestParam("multipartFile") MultipartFile multipartFile,
                                         @RequestParam("nombre") String nombreProducto,
                                         @RequestParam("descripcion") String descripcionProducto,
                                         @RequestParam("precio") BigDecimal precioProducto,
                                         @RequestParam("cantidad") Integer cantidadProducto,
                                         @RequestParam("id_proveedor") Long idProveedor,
                                         @RequestParam("id_tipo_producto") Long idTipoProducto

    ) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("Image non valide!", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);

        Producto producto = new Producto(
                nombreProducto,
                descripcionProducto,
                precioProducto,
                cantidadProducto,
                (String) result.get("url"),
                (String) result.get("public_id"),
              idProveedor,
                idTipoProducto);
        productoService.crearRecurso(producto);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(Collections.singletonMap("message", "Producto registrado correctamente!"));
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
    @GetMapping("/index")
    public Iterable<Producto> getAll(){
        return productoService.obtenerTodosRecursos();
    }

    @GetMapping(value = "/show/{id}")
    public ResponseEntity<Producto> getReniecData(@PathVariable Long id) {
        return productoService.obtenerRecursoPorId(id);
    }

    @PostMapping("/update")
    public Producto update(@RequestParam Long id,@RequestBody Producto modificado){
        return productoService.actualizarRecurso(id,modificado);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Producto> delete(@PathVariable(value = "id") Long id){
        return productoService.eliminarRecurso(id);
    }
}
