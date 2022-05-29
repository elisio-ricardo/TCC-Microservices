package br.com.erudio.proxy;


import br.com.erudio.dtos.BookDTO;
import br.com.erudio.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url="http://localhost:7000", name="book-service")
public interface BookProxy {

    @GetMapping(value = "/book-service/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency);

    @GetMapping(value = "/book-service/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id);

    @GetMapping(value = "/book-service")
    public ResponseEntity<List<BookDTO>> findAll();

    @PostMapping(value = "/book-service")
    public ResponseEntity<BookDTO> create(@Valid @RequestBody Book book);

    @PutMapping(value = "/book-service/{id}")
    public ResponseEntity<BookDTO> update(@Valid @PathVariable Long id, @RequestBody Book book);

    @DeleteMapping(value = "/book-service/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping(value = "/book-service/time")
    public ResponseEntity<List<BookDTO>> findAllTimeProcessed();
}
