package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.*;
import br.uem.iss.anesthesia.model.business.validator.CpfValidator;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.SurnameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.entity.ConsultModel;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.ConsultRepository;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class SaveConsultBusiness extends SaveModelBusiness<ConsultModel> {

    private ConsultRepository consultRepository;
    private DoctorRepository doctorRepository;
    private ProcessRepository processRepository;
    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveConsultBusiness(ConsultRepository consultRepository) {
        super(consultRepository);


    }

    @Override
    protected void saveDependencies(ConsultModel consult)  { }

    @Override
    protected void validateFields(ConsultModel model)  {


    }



}