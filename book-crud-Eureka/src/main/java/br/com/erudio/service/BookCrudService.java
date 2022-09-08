package br.com.erudio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.erudio.dtos.BookDTO;
import br.com.erudio.model.Book;
import br.com.erudio.repository.BookCrudRepository;

import br.com.erudio.service.exception.ObjectNotFoundException;

@Service
public class BookCrudService {

	@Autowired
	private BookCrudRepository bookCrudRepository;

	public Book findById(Long id) {
		Optional<Book> obj = bookCrudRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id ));
	}

	public List<BookDTO> findAll(){
		List<Book> books = bookCrudRepository.findAll();
		List<BookDTO> collect = books.stream().map(b ->
				new BookDTO(b)
		).collect(Collectors.toList());
		return collect;
	}

	public Book create(Book obj) {
		obj.setId(null);
		return bookCrudRepository.save(obj);
	}

	public Book update(Long id, Book objDto) {
		Book obj = findById(id);
		objDto.setId(obj.getId());
		obj = objDto;
		return bookCrudRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			bookCrudRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) { // esta exceção é do spring
			throw new RuntimeException(// adicionada esta exceção personalizada
					"Categoria não pode ser deletado! Possui livros associados");
		}
	}

    public void deleteMocks() {
		List<Book> books = bookCrudRepository.getBooksMocks();
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i));
			bookCrudRepository.deleteById(books.get(i).getId());
		}
	}

	public List<Book> getBooksMocks(){
		return bookCrudRepository.getBooksMocks();
	}
}
