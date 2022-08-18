package com.example.kafkaconsumer.proxy;

import com.example.kafkaconsumer.dto.CambioDTO;
import com.example.kafkaconsumer.model.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url="http://localhost:7080", name="cambio-crud")
public interface CambioProxy {

    @GetMapping(value = "/cambio-crud/{id}")
    Cambio findById(@PathVariable Long id) ;

    @GetMapping("/cambio-crud")
    List<Cambio> findAll() ;

    @GetMapping(value = "/cambio-crud/time")
    List<Cambio> findAllTimeProcessed() ;

    @PostMapping("/cambio-crud")
    Cambio create(@Valid @RequestBody Cambio obj) ;

    @PutMapping(value = "/cambio-crud/{id}")
    Cambio update(@Valid @PathVariable Long id, @RequestBody Cambio objDto);

    @DeleteMapping(value = "/cambio-crud/{id}")
    Void delete(@PathVariable Long id);

    @GetMapping(value = "/cambio-crud/delete-mocks")
    ResponseEntity<Void>  deletemocks();

    @GetMapping(value = "/cambio-crud/get-mocks")
    List<Cambio>  getMocks();

}
