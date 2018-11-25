package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;

public class ProcessIndexView extends AbstractModelAndView {

    public ProcessIndexView(Iterable<ProcessModel> process, Iterable<DoctorModel> doctors, Iterable<PatientModel> patients, DoctorModel doctor, PatientModel patient) {
        super("index_process", "process", process);
        this.addObject("doctors", doctors);
        this.addObject("patients", patients);
        this.addObject("filtro_paciente", patient);
        this.addObject("filtro_medico", doctor);
    }
}
