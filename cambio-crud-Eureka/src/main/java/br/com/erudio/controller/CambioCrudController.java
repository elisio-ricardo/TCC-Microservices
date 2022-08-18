package br.com.erudio.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import br.com.erudio.dtos.CambioDTO;
import br.com.erudio.model.Cambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.erudio.service.CambioCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins =  "*")
@Tag(name = "Cambio CRUD endPoint")
@RestController
@RequestMapping("/cambio-crud")
public class CambioCrudController {

	@Autowired
	private CambioCrudService crudService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cambio> findById(@PathVariable Long id) {

		Cambio obj = crudService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<Cambio>> findAll() {
		List<Cambio> listDTO = crudService.getListOfCambio();
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/time")
	public ResponseEntity<List<Cambio>> findAllTimeProcessed() {
		Long start = System.nanoTime();
		List<Cambio> listDTO = crudService.getListOfCambio();
		Long end = System.nanoTime();
		System.out.println("Tempo passado dentro no metodo :" + (end-start) + " nanossegundos");
		System.out.println("Tempo passado dentro no metodo segundos :" + (end-start)*(Math.pow(10, -9)) + "s");
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<Cambio> create(@Valid @RequestBody Cambio obj) {
		obj = crudService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cambio> update(@Valid @PathVariable Long id, @RequestBody Cambio objDto) {
		

		Cambio newObj = crudService.update(id, objDto);

		return ResponseEntity.ok().body(newObj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		crudService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/delete-mocks")
	ResponseEntity<Void>  deletemocks(){
		crudService.deleteMocks();
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/get-mocks")
	public ResponseEntity<List<Cambio>>  getMocks(){
		return ResponseEntity.ok().body(crudService.getCambiosMock());
	}

}
