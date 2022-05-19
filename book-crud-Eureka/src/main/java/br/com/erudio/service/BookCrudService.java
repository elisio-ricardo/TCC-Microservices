package br.com.erudio.service;

import java.util.List;
import java.util.Optional;

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
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Book.class.getName()));
	}

	public List<Book> findAll() {
		return bookCrudRepository.findAll();
	}

	public Book create(Book obj) {
		obj.setId(null);
		return bookCrudRepository.save(obj);
	}

	public Book update(Long id, BookDTO objDto) {
		Book obj = findById(id);
		obj.setAuthor(objDto.getAuthor());
		obj.setTitle(objDto.getTitle());
		obj.setPrice(objDto.getPrice());
		return bookCrudRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);

		try {
			bookCrudRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) { // esta exceção é do spring
			throw new br.com.erudio.service.exception.DataIntegrityViolationException(// adicionada esta exceção
																						// personalizada
					"Categoria não pode ser deletado! Possui livros associados");
		}
	}

}
