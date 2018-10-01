package br.uem.iss.anesthesia.model.repository;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends CrudRepository<ExamModel, Long> {

    Iterable<ExamModel> findByActiveTrue();
    Iterable<ExamModel> findByNameContainingAndActiveTrue(String name);
}
