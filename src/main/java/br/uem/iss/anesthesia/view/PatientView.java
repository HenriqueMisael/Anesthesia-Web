package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.CivilState;
import br.uem.iss.anesthesia.model.entity.Gender;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import org.springframework.web.servlet.ModelAndView;

public class PatientView extends ModelAndView {

    public PatientView(PatientModel patient) {
        this(patient, null);
    }

    public PatientView(PatientModel patient, String message) {
        super("patient", "patient", patient);
        this.addObject("message", message);
        this.addObject("genderOptions", Gender.values());
        this.addObject("civilStateOptions", CivilState.values());
        this.addObject("cityOptions", CivilState.values());
    }
}
