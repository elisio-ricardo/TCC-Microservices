package br.com.erudio.controller;


import br.com.erudio.dtos.BookDTO;
import br.com.erudio.model.Book;
import br.com.erudio.service.BookCrudService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@DisplayName("BookCrudControllerTest")
public class BookCrudControllerTeste {

    @MockBean
    private BookCrudService bookCrudService;

    @Autowired
    private BookCrudController bookCrudController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    BookDTO bookDTO = new BookDTO();
    Book book = new Book();


    @BeforeEach
    public void setup() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        book = Book.builder()
                .id(1L)
                .author("Pedro")
                .title("AS aventuras")
                .build();

        bookDTO = BookDTO.builder()
                .id(1L)
                .author("Pedro")
                .title("As aventuras")
                .build();
    }


    @Test
    @DisplayName("Deve Retornar uma Lista de Books")
    public void shouldReturnListOfBooks() throws Exception {
        mockMvc.perform(get("/book-crud")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk());

        Mockito.verify(bookCrudService, Mockito.times(1))
                .findAll();
    }

    @Test
    @DisplayName("Deve retornar um book pelo ID")
    public void shouldReturnBookWithId() throws Exception {
        mockMvc.perform(get("/book-crud/{id}", 1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk());

        Mockito.verify(bookCrudService, Mockito.times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Deve criar um book")
    public void ShouldCreatBook() throws Exception {
        Mockito.when(bookCrudService.create(book)).thenReturn(book);

        mockMvc.perform(post("/book-crud")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());

        Mockito.verify(bookCrudService, Mockito.times(1)).create(book);
    }

    @Test
    @DisplayName("Deve atualizar um livro")
    public void ShouldAttBook() throws Exception {
        Mockito.when(bookCrudService.update(1L, bookDTO)).thenReturn(book);

        mockMvc.perform(put("/book-crud/" + 1L, bookDTO, 1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk());

        Mockito.verify(bookCrudService, Mockito.times(1))
                .update(1L, bookDTO);
    }

    @Test
    @DisplayName("Deve deletar um Livro")
    public void ShouldDeleteBook() throws Exception {
        Mockito.when(bookCrudService.findById(1L)).thenReturn(book);

        mockMvc.perform(delete("/book-crud/" + 1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isNoContent());

        Mockito.verify(bookCrudService, Mockito.times(1))
                .delete(1L);

    }


}
