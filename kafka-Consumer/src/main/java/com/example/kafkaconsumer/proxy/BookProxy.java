package com.example.kafkaconsumer.proxy;


import com.example.kafkaconsumer.dto.BookDTO;
import com.example.kafkaconsumer.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url="http://localhost:7000", name="book-crud")
public interface BookProxy {

//    @GetMapping(value = "/book-crud/{id}/{currency}")
//    public Book getBook(@PathVariable("id") Long id, @PathVariable("currency") String currency);

    @GetMapping(value = "/book-crud/{id}")
    BookDTO findById(@PathVariable Long id);

    @GetMapping(value = "/book-crud")
    List<BookDTO> findAll();

    @PostMapping(value = "/book-crud")
    BookDTO create(@Valid @RequestBody Book book);

    @PutMapping(value = "/book-crud/{id}")
    BookDTO update(@Valid @PathVariable Long id, @RequestBody Book book);

    @DeleteMapping(value = "/book-crud/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping(value = "/book-crud/time")
    List<BookDTO> findAllTimeProcessed();

    @GetMapping(value = "/book-crud/delete-mocks")
    ResponseEntity<Void>  deletemocks();

    @GetMapping(value = "/book-crud/get-mocks")
    List<Book> getMocks();
}
