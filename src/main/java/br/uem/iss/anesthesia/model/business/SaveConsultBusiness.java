package br.uem.iss.anesthesia.model.business;

import br.uem.iss.anesthesia.model.business.validator.NameNotNullValidator;
import br.uem.iss.anesthesia.model.entity.*;
import br.uem.iss.anesthesia.model.repository.AppointmentRepository;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveConsultBusiness extends SaveModelBusiness<AppointmentModel> {

    private AppointmentRepository consultRepository;
    private DoctorRepository doctorRepository;
    private ProcessRepository processRepository;
    private NameNotNullValidator nameNotNullValidator;

    @Autowired
    public SaveConsultBusiness(AppointmentRepository consultRepository) {
        super(consultRepository);


    }

    @Override
    protected void saveDependencies(AppointmentModel consult)  { }

    @Override
    protected void validateFields(AppointmentModel model)  {


    }



}
