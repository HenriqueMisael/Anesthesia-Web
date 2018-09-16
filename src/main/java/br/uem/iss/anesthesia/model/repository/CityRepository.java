package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.CityModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<CityModel, Long> {

    Optional<CityModel> findByName(String name);
}
