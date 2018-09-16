package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<DoctorModel, Long> {

    Iterable<DoctorModel> findByActiveTrue();
}
