package br.com.erudio.service;

import java.util.List;
import java.util.Optional;


import br.com.erudio.model.Cambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.erudio.repository.CambioCrudRepository;

import br.com.erudio.service.exception.ObjectNotFoundException;

@Service
public class CambioCrudService {

	@Autowired
	private CambioCrudRepository cambioCrudRepository;

	public Cambio findById(Long id) {

		Optional<Cambio> obj = cambioCrudRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cambio.class.getName()));
	}

	public List<Cambio> findAll() {
		return cambioCrudRepository.findAll();
	}

	public List<Cambio> getListOfCambio(){
		List<Cambio> cambios = cambioCrudRepository.findAll();
		return cambios;
	}

	public Cambio create(Cambio obj) {
		obj.setId(null);
		return cambioCrudRepository.save(obj);
	}

	public Cambio update(Long id, Cambio objDto) {
		Cambio obj = findById(id);
		objDto.setId(obj.getId());
		obj = objDto;
		return cambioCrudRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);

		try {
			cambioCrudRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) { // esta exceção é do spring
			throw new DataIntegrityViolationException(// adicionada esta exceção
																						// personalizada
					"Categoria não pode ser deletado! Possui livros associados");
		}
	}

    public void deleteMocks() {
		List<Cambio> cambios = cambioCrudRepository.getCambioMocks();
		for (int i = 0; i < cambios.size(); i++) {
			cambioCrudRepository.deleteById(cambios.get(i).getId());
		}
    }

	public List<Cambio> getCambiosMock() {
		return cambioCrudRepository.getCambioMocks();
	}
}
