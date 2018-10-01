package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.*;
import org.springframework.web.servlet.ModelAndView;

public class PatientView extends ModelAndView {

    public PatientView(PatientModel patient, String message, Iterable<CityModel> cityOptions, Iterable<String> ufOptions, Gender[] genderOptions, CivilState[] civilStatesOptionsz, Iterable<BackgroundModel> backgrounds) {
        super("layouts/app", "patient", patient);
        this.addObject("message", message);
        this.addObject("genderOptions", genderOptions);
        this.addObject("civilStateOptions", civilStatesOptionsz);
        this.addObject("cityOptions", cityOptions);
        this.addObject("ufOptions", ufOptions);
        this.addObject("backgrounds", backgrounds);
        this.addObject("conteudo", "form_patient");
    }
}
