package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.entity.Week;
import org.springframework.web.servlet.ModelAndView;

public class ProcessFormView extends ModelAndView {
    public ProcessFormView(ProcessModel process, String message, Iterable<DoctorModel> doctors, Iterable<PatientModel> patients) {
        super("layouts/app", "process", process);
        this.addObject("message", message);
        this.addObject("metodo", "Adicionar ");
        this.addObject("conteudo", "form_process");
        this.addObject("doctors", doctors);
        this.addObject("patients", patients);
    }
}
