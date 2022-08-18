package br.com.erudio.repository;

import br.com.erudio.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CambioCrudRepository extends JpaRepository<Cambio, Long>{

    @Query(value = "SELECT c.* FROM cambio AS c WHERE c.to_currency = 'MCK'", nativeQuery = true)
    List<Cambio> getCambioMocks();
}
