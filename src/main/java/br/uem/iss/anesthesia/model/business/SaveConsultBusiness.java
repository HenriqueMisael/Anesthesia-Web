package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.exception.*;
import br.uem.iss.anesthesia.model.business.validator.CpfValidator;
import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.business.validator.SurnameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.*;
import br.uem.iss.anesthesia.model.repository.ConsultRepository;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class SaveConsultBusiness extends SaveModelBusiness<AppointmentModel> {

    private ConsultRepository consultRepository;
    private DoctorRepository doctorRepository;
    private ProcessRepository processRepository;
    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveConsultBusiness(ConsultRepository consultRepository) {
        super(consultRepository);


    }

    @Override
    protected void saveDependencies(AppointmentModel consult)  { }

    @Override
    protected void validateFields(AppointmentModel model)  {


    }



}
