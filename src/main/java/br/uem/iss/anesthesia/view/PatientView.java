package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.PatientModel;
import org.springframework.web.servlet.ModelAndView;

public class PatientView extends ModelAndView {

    public PatientView(PatientModel patient) {
        super("patient", "patient", patient);
        this.addObject("message", null);
    }
}
