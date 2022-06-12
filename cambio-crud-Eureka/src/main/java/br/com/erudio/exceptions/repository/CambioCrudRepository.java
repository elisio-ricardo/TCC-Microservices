package br.com.erudio.exceptions.repository;

import br.com.erudio.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioCrudRepository extends JpaRepository<Cambio, Long>{

}
