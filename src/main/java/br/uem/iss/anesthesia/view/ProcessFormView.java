package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;

public class ProcessFormView extends AbstractModelAndView {

    public ProcessFormView(ProcessModel process, String message, Iterable<DoctorModel> doctors, Iterable<PatientModel> patients) {
        super("form_process", "process", process);
        this.addObject("message", message);
        this.addObject("metodo", "Adicionar ");
        this.addObject("doctors", doctors);
        this.addObject("patients", patients);
    }
}
