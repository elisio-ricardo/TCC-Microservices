package br.com.erudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.erudio.model.Book;

@Repository
public interface BookCrudRepository extends JpaRepository<Book, Long>{

}
