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

    @GetMapping(value = "/{id}")
    Cambio findById(@PathVariable Long id) ;

    @GetMapping("/cambio-crud")
    List<Cambio> findAll() ;

    @GetMapping(value = "/time")
    List<Cambio> findAllTimeProcessed() ;

    @PostMapping("/cambio-crud")
    Cambio create(@Valid @RequestBody Cambio obj) ;

    @PutMapping(value = "/{id}")
    CambioDTO update(@Valid @PathVariable Long id, @RequestBody CambioDTO objDto);

    @DeleteMapping(value = "/{id}")
    Void delete(@PathVariable Long id);

}
