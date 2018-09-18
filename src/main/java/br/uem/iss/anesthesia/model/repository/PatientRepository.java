package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.PatientModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<PatientModel, Long> {

    Iterable<PatientModel> findByActiveTrue();

    Iterable<PatientModel> findByCpfContainingAndActiveTrue(String cpf);

    Iterable<PatientModel> findByNameContainingAndActiveTrue(String name);

    Iterable<PatientModel> findByCpfContainingAndNameContainingAndActiveTrue(String cpf, String name);
}
