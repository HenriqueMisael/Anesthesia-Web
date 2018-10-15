package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.PatientModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PatientRepository extends CrudRepository<PatientModel, Long> {

    Set<PatientModel> findByActiveTrue();

    Set<PatientModel> findByCpfContainingAndActiveTrue(String cpf);

    Set<PatientModel> findByNameContainingAndActiveTrue(String name);

    Set<PatientModel> findByCpfContainingAndNameContainingAndActiveTrue(String cpf, String name);
}
