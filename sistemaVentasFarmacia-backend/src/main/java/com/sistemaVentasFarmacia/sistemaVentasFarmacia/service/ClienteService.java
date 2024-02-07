package com.sistemaVentasFarmacia.sistemaVentasFarmacia.service;

import com.sistemaVentasFarmacia.sistemaVentasFarmacia.model.Cliente;
import com.sistemaVentasFarmacia.sistemaVentasFarmacia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodosRecursos() {
        return clienteRepository.findAll();
    }
    public Cliente getReniecData(String dni) {
        String apiUrl = "https://dniruc.apisperu.com/api/v1/dni/";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImpzbWl0aG1vc3RhY2Vyb0BnbWFpbC5jb20ifQ.PBqiBfwtyPC1GQb3T-5wbNbrN8iYPWaXuO4xC_Lkb78";
        String url = apiUrl + dni + "?token=" + token;
        try {
            RestTemplate restTemplate = new RestTemplate();
            Cliente response = restTemplate.getForObject(url, Cliente.class);
            return response;
        } catch (HttpClientErrorException e) {
            // Manejar el error, por ejemplo, loggearlo o devolver un objeto con información de error
            // e.getStatusCode() proporciona el código de estado HTTP
            // e.getResponseBodyAsString() proporciona la respuesta del servidor en caso de un error
            // También puedes lanzar tu propia excepción personalizada si lo prefieres
            return null; // o lanzar una excepción
        }
    }

    public ResponseEntity<Cliente> obtenerRecursoPorId(Long id) {
        Optional<Cliente> recurso = clienteRepository.findById(id);

        return recurso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public Cliente crearRecurso(@RequestBody Cliente recurso) {
        return clienteRepository.save(recurso);
    }

    public Cliente actualizarRecurso(@PathVariable Long id, @RequestBody Cliente nuevoRecurso) {
        return clienteRepository.findById(id)
                .map(recurso -> {
                    // Actualizar propiedades según las necesidades
                    recurso.setNombres(nuevoRecurso.getNombres());
                    recurso.setApellidoPaterno(nuevoRecurso.getApellidoPaterno());
                    recurso.setApellidoMaterno(nuevoRecurso.getApellidoMaterno());
                    return clienteRepository.save(recurso);
                })
                .orElseGet(() -> {
                    nuevoRecurso.setIdCliente(id);
                    return clienteRepository.save(nuevoRecurso);
                });
    }

    public ResponseEntity<Cliente> eliminarRecurso(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
