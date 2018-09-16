package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.CityModel;
import br.uem.iss.anesthesia.model.entity.CivilState;
import br.uem.iss.anesthesia.model.entity.Gender;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import org.springframework.web.servlet.ModelAndView;

public class PatientView extends ModelAndView {

    public PatientView(PatientModel patient, String message, Iterable<CityModel> cityOptions, Iterable<String> ufOptions, Gender[] genderOptions, CivilState[] civilStatesOptions) {
        super("layouts/app", "patient", patient);
        this.addObject("message", message);
        this.addObject("genderOptions", genderOptions);
        this.addObject("civilStateOptions", civilStatesOptions);
        this.addObject("cityOptions", cityOptions);
        this.addObject("ufOptions", ufOptions);
        this.addObject("conteudo", "form_patient");
    }
}
