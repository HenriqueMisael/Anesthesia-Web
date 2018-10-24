package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.*;

public class PatientView extends AbstractModelAndView {

    public PatientView(PatientModel patient, String message, Iterable<CityModel> cityOptions, Iterable<String> ufOptions, Gender[] genderOptions, CivilState[] civilStatesOptionsz, Iterable<BackgroundModel> backgrounds, Iterable<MedicineModel> medicines) {
        super("form_patient", "patient", patient);
        this.addObject("message", message);
        this.addObject("genderOptions", genderOptions);
        this.addObject("civilStateOptions", civilStatesOptionsz);
        this.addObject("cityOptions", cityOptions);
        this.addObject("ufOptions", ufOptions);
        this.addObject("backgrounds", backgrounds);
        this.addObject("medicines", medicines);
    }
}
