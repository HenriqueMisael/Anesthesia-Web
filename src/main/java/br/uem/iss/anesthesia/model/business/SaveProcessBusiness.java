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
    public SaveProcessBusiness(ProcessRepository processRepository, SaveDoctorBusiness saveDoctorBusiness, SaveMedicalProcedureBusiness saveMedicalProcedureBusiness, SavePatientBusiness savePatientBusiness, SaveProcessExamBusiness saveProcessExamBusiness) {
        super(processRepository);
        this.saveDoctorBusiness = saveDoctorBusiness;
        this.saveMedicalProcedureBusiness = saveMedicalProcedureBusiness;
        this.savePatientBusiness = savePatientBusiness;
        this.saveProcessExamBusiness = saveProcessExamBusiness;
    }

    @Override
    protected void saveDependencies(ProcessModel process) throws BusinessRuleException {
        if(process.getDoctor() != null && (process.getDoctor().getId() == null || process.getDoctor().getId() == 0)){
            saveDoctorBusiness.save(process.getDoctor());
        }
        if(process.getMedicalProcedure() != null && (process.getMedicalProcedure().getId() == null || process.getMedicalProcedure().getId() == 0)){
            saveMedicalProcedureBusiness.save(process.getMedicalProcedure());
        }
        if(process.getPatient() != null && (process.getPatient().getId() == null || process.getPatient().getId() == 0)){
            savePatientBusiness.save(process.getPatient());
        }
        int size = process.getProcesexams().size();
        for(int i = 0; i < size; i++){
            saveProcessExamBusiness.save(process.getProcesexams().get(i));
        }
    }

    @Override
    protected void validateFields(ProcessModel model) {
    }
}
