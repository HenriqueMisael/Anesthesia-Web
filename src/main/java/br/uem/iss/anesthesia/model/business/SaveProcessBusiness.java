package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.business.exception.NullContentNotAllowedException;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveProcessBusiness extends SaveModelBusiness<ProcessModel> {

    private SaveDoctorBusiness saveDoctorBusiness;
    private SaveMedicalProcedureBusiness saveMedicalProcedureBusiness;
    private SavePatientBusiness savePatientBusiness;
    private SaveProcessExamBusiness saveProcessExamBusiness;
    @Autowired
    public SaveProcessBusiness(ProcessRepository processRepository) {
        super(processRepository);

    }

    @Override
    protected void saveDependencies(ProcessModel process) throws BusinessRuleException {
        if(process.getDoctor() != null && (process.getDoctor().getId() == null || process.getDoctor().getId() == 0)){
            System.out.println("Vai Slavar Doctor");
            saveDoctorBusiness.save(process.getDoctor());
        }
        if(process.getMedicalProcedure() != null && (process.getMedicalProcedure().getId() == null || process.getMedicalProcedure().getId() == 0)){
            System.out.println("Vai Slavar Medical Procedure");
            saveMedicalProcedureBusiness.save(process.getMedicalProcedure());
        }
        if(process.getPatient() != null && (process.getPatient().getId() == null || process.getPatient().getId() == 0)){
            System.out.println("Vai Slavar Paciente");
            savePatientBusiness.save(process.getPatient());
        }
    }

    @Override
    protected void validateFields(ProcessModel model) throws NullContentNotAllowedException {
    }
}
