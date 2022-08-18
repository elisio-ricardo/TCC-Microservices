//package br.com.erudio.service;
//
//
//import br.com.erudio.dtos.BookDTO;
//import br.com.erudio.model.Book;
//import br.com.erudio.repository.BookCrudRepository;
//import br.com.erudio.service.exception.ObjectNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@DisplayName("BookCrudServiceTest")
//public class BookCrudServiceTest {
//
//    @MockBean
//    private BookCrudRepository bookCrudRepository;
//
//    @Autowired
//    private BookCrudService bookCrudService;
//
//    Book book = new Book();
//    BookDTO bookDTO = new BookDTO();
//
//    @BeforeEach
//    public void setup() {
//        book = Book.builder()
//                .title("Livro")
//                .build();
//
//        bookDTO = BookDTO.builder()
//                .id(1L)
//                .title("Livro")
//                .build();
//    }
//
//    @Test
//    @DisplayName("Deve retornar um book pelo ID")
//    public void shouldReturnBookWithId() {
//        Mockito.when(bookCrudRepository.findById(1L))
//                .thenReturn(java.util.Optional.ofNullable(book));
//
//        Book bookById = bookCrudService.findById(1L);
//
//        assertEquals(bookById.getClass(), book.getClass());
//        Mockito.verify(bookCrudRepository, Mockito.times(1)).findById(1l);
//    }
//
//    @Test
//    @DisplayName("Retorna objeto não encontrado")
//    void shouldReturnObjNotFoundException() {
//        Mockito.when(bookCrudRepository.findById(1L)).
//                thenThrow(new ObjectNotFoundException("Objeto não encontrado! Id: "
//                        + book.getId()));
//        try {
//            bookCrudService.findById(1L);
//        } catch (Exception ex) {
//            assertEquals(ObjectNotFoundException.class, ex.getClass());
//            assertEquals("Objeto não encontrado! Id: " + book.getId(), ex.getMessage());
//        }
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma lista de Book")
//    public void shouldReturnListOfBook() {
//        List<Book> listaBookFake = new ArrayList<>();
//        Book book1 = new Book();
//        Book book2 = new Book();
//        Book book3 = new Book();
//        listaBookFake.addAll(Arrays.asList(book1, book2, book3));
//
//        Mockito.when(bookCrudRepository.findAll()).thenReturn(listaBookFake);
//        List<BookDTO> listaBookDTOFake = listaBookFake.stream().map(b ->
//                new BookDTO(b)
//        ).collect(Collectors.toList());
//
//        List<BookDTO> listOfBooKDTO = bookCrudService.findAll();
//
//        assertEquals(listOfBooKDTO, listaBookDTOFake);
//        Mockito.verify(bookCrudRepository, Mockito.times(1)).findAll();
//    }
//
//    @Test
//    @DisplayName("Deve criar um book")
//    public void shouldCreatBook() {
//        bookCrudService.create(book);
//        Mockito.verify(bookCrudRepository, Mockito.times(1)).save(book);
//    }
//
//    @Test
//    @DisplayName("Deve atualizar um Book")
//    public void shouldAttBook() {
//        Mockito.when(bookCrudRepository.findById(1L))
//                .thenReturn(java.util.Optional.ofNullable(book));
//
//        bookCrudService.update(1L, bookDTO);
//
//        Mockito.verify(bookCrudRepository, Mockito.times(1)).save(book);
//        Mockito.verify(bookCrudRepository, Mockito.times(1)).findById(1L);
//    }
//
//    @Test
//    @DisplayName("Deve excluir um veiculo")
//    public void shouldDeleteBook() {
//        bookCrudService.delete(1L);
//        Mockito.verify(bookCrudRepository, Mockito.times(1)).deleteById(1L);
//    }
//
//
//
//}
