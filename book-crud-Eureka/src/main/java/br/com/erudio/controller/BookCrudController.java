package br.com.erudio.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import br.com.erudio.dtos.BookDTO;
import br.com.erudio.model.Book;
import br.com.erudio.service.BookCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@Tag(name = "Book CRUD endPoint")
@RestController
@RequestMapping("/book-crud")
public class BookCrudController {

    @Autowired
    private BookCrudService crudService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> listDTO = crudService.getListOfBooKDTO();
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {

        Book obj = crudService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/time")
    public ResponseEntity<Void> findAllTimeProcessed() {
        Long start = System.nanoTime();
        List<BookDTO> listDTO = crudService.getListOfBooKDTO();
        Long end = System.nanoTime();
        System.out.println("Tempo passado dentro no metodo :" + (end - start) + " nanossegundos");
        System.out.println("Tempo passado dentro no metodo segundos :" + (end - start) * (Math.pow(10, -9)) + "s");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book obj) {
        obj = crudService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
        //return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDTO> update(@Valid @PathVariable Long id, @RequestBody BookDTO bookDto) {
        Book newBook = crudService.update(id, bookDto);

        return ResponseEntity.ok().body(new BookDTO(newBook));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
