package br.com.erudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.Book;

import java.util.List;

@Repository
public interface BookCrudRepository extends JpaRepository<Book, Long>{

    @Query(value = "SELECT c.* FROM book AS c WHERE c.author= 'Autor mock'", nativeQuery = true)
    List<Book> getBooksMocks();

}
