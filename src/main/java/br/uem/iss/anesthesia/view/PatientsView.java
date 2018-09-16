package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.PatientModel;
import org.springframework.web.servlet.ModelAndView;

public class PatientsView extends ModelAndView {

    public PatientsView(Iterable<PatientModel> patients) {
        super("layouts/app", "patients", patients);
        this.addObject("conteudo", "index_patient");
    }
}
